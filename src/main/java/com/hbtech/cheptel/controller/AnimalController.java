package com.hbtech.cheptel.controller;

import com.hbtech.cheptel.dto.request.UpdateAnimalRequest;
import com.hbtech.cheptel.dto.request.CreateAnimalRequest;
import com.hbtech.cheptel.dto.response.AnimalResponse;
import com.hbtech.cheptel.entity.*;
import com.hbtech.cheptel.repository.AnimalRepository;
import com.hbtech.cheptel.repository.RfidTagRepository;
import com.hbtech.cheptel.service.AnimalService;
import com.hbtech.cheptel.service.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService animalService;
    private final CurrentUserService currentUserService;
    private final AnimalRepository animalRepository;
    private final RfidTagRepository rfidTagRepository;

    public AnimalController(
            AnimalService animalService,
            CurrentUserService currentUserService,
            AnimalRepository animalRepository,
            RfidTagRepository rfidTagRepository
    ) {
        this.animalService = animalService;
        this.currentUserService = currentUserService;
        this.animalRepository = animalRepository;
        this.rfidTagRepository = rfidTagRepository;
    }

    @GetMapping("/rfid/{rfidTag}")
    @PreAuthorize("hasAnyRole('FERMIER','VETERINAIRE','CONTROLEUR','ADMIN')")
    public ResponseEntity<?> getByRfid(@PathVariable String rfidTag) {
        User current = currentUserService.getCurrentUserOrThrow();

        Animal animal;
        try {
            animal = animalService.getByRfidOrThrow(rfidTag);
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Animal introuvable pour ce tag RFID: " + rfidTag));
        }

        if (current.getRole() == Role.FERMIER) {
            Long userFarmId = current.getFarm() != null ? current.getFarm().getId() : null;
            Long animalFarmId = animal.getFarm().getId();
            if (userFarmId == null || !userFarmId.equals(animalFarmId)) {
                return ResponseEntity.status(403)
                        .body(Map.of("message", "Cet animal n'appartient pas a votre exploitation"));
            }
        }

        return ResponseEntity.ok(toResponse(animal));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('FERMIER','ADMIN')")
    public ResponseEntity<?> createAnimal(@RequestBody CreateAnimalRequest request) {
        User current = currentUserService.getCurrentUserOrThrow();

        if (current.getFarm() == null) {
            return ResponseEntity.status(403).body(Map.of("message", "Vous n'etes associe a aucune ferme"));
        }

        if (animalService.existsByRfidTag(request.getRfidTag())) {
            return ResponseEntity.status(400).body(Map.of("message", "Ce tag RFID est deja utilise"));
        }

        try {
            RfidTag tag = rfidTagRepository.findByRfidCode(request.getRfidTag())
                    .orElseGet(() -> rfidTagRepository.save(RfidTag.builder()
                            .rfidCode(request.getRfidTag())
                            .tagStatus(TagStatus.InStock)
                            .build()));

            Animal animal = Animal.builder()
                    .rfidTagEntity(tag)
                    .species(Species.fromInput(request.getSpecies()))
                    .breed(request.getBreed())
                    .gender(Gender.fromInput(request.getGender()))
                    .weight(request.getWeight() == null ? null : BigDecimal.valueOf(request.getWeight()))
                    .color(request.getColor())
                    .birthDate(request.getBirthDate() != null ? LocalDate.parse(request.getBirthDate()) : null)
                    .status(AnimalStatus.ACTIVE)
                    .farm(current.getFarm())
                    .owner(current)
                    .build();

            return ResponseEntity.ok(toResponse(animalRepository.save(animal)));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", "Erreur: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('FERMIER','ADMIN')")
    public ResponseEntity<?> updateAnimal(@PathVariable Long id, @RequestBody UpdateAnimalRequest request) {
        User current = currentUserService.getCurrentUserOrThrow();
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal introuvable"));

        if (current.getRole() == Role.FERMIER) {
            Long userFarmId = current.getFarm() != null ? current.getFarm().getId() : null;
            if (userFarmId == null || !userFarmId.equals(animal.getFarm().getId())) {
                return ResponseEntity.status(403)
                        .body(Map.of("message", "Cet animal n'appartient pas a votre exploitation"));
            }
        }

        try {
            if (request.getBreed() != null) animal.setBreed(request.getBreed());
            if (request.getGender() != null) animal.setGender(Gender.fromInput(request.getGender()));
            if (request.getWeight() != null) animal.setWeight(request.getWeight());
            if (request.getColor() != null) animal.setColor(request.getColor());
            if (request.getStatus() != null) animal.setStatus(AnimalStatus.fromInput(request.getStatus()));
            if (request.getBirthDate() != null && !request.getBirthDate().isEmpty()) {
                animal.setBirthDate(LocalDate.parse(request.getBirthDate()));
            }

            return ResponseEntity.ok(toResponse(animalRepository.save(animal)));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", "Erreur: " + e.getMessage()));
        }
    }

    private AnimalResponse toResponse(Animal animal) {
        return AnimalResponse.builder()
                .id(animal.getId())
                .rfidTag(animal.getRfidTag())
                .species(animal.getSpecies())
                .breed(animal.getBreed())
                .gender(animal.getGender())
                .weight(animal.getWeight())
                .status(animal.getStatus())
                .color(animal.getColor())
                .birthDate(animal.getBirthDate())
                .farmId(animal.getFarm() != null ? animal.getFarm().getId() : null)
                .farmName(animal.getFarm() != null ? animal.getFarm().getName() : null)
                .build();
    }
}
