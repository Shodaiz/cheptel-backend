package com.hbtech.cheptel.service;

import com.hbtech.cheptel.dto.request.ConstatRequest;
import com.hbtech.cheptel.dto.response.ConstatResponse;
import com.hbtech.cheptel.entity.*;
import com.hbtech.cheptel.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ConstatService {

    private final ConstatRepository constatRepository;
    private final ConstatImageRepository constatImageRepository;
    private final FarmRepository farmRepository;
    private final ControlSessionRepository controlSessionRepository;
    private final CurrentUserService currentUserService;

    public ConstatService(
            ConstatRepository constatRepository,
            ConstatImageRepository constatImageRepository,
            FarmRepository farmRepository,
            ControlSessionRepository controlSessionRepository,
            CurrentUserService currentUserService
    ) {
        this.constatRepository = constatRepository;
        this.constatImageRepository = constatImageRepository;
        this.farmRepository = farmRepository;
        this.controlSessionRepository = controlSessionRepository;
        this.currentUserService = currentUserService;
    }

    @Transactional
    public ConstatResponse createConstat(ConstatRequest request) {
        User controleur = currentUserService.getCurrentUserOrThrow();

        Farm farm = null;
        if (request.getFarmId() != null) {
            farm = farmRepository.findById(request.getFarmId()).orElse(null);
        }

        ControlSession session = null;
        if (request.getControlSessionId() != null) {
            session = controlSessionRepository.findById(request.getControlSessionId()).orElse(null);
        }

        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new RuntimeException("La description est obligatoire");
        }

        String type = request.getType() != null ? request.getType().name() : "AUTRE";

        Constat constat = new Constat();
        constat.setControleur(controleur);
        constat.setFarm(farm);
        constat.setControlSession(session);
        constat.setType(type);
        constat.setDescription(request.getDescription().trim());
        constat.setLatitude(request.getLatitude());
        constat.setLongitude(request.getLongitude());
        constat.setLocalisationText(request.getLocalisationText());
        constat.setStatus("PENDING");
        constat.setCreatedAt(LocalDateTime.now());

        Constat saved = constatRepository.save(constat);

        if (request.getImageUrls() != null) {
            for (String url : request.getImageUrls()) {
                if (url != null && !url.isBlank()) {
                    ConstatImage img = ConstatImage.builder()
                            .constat(saved)
                            .imageUrl(url.trim())
                            .build();
                    constatImageRepository.save(img);
                }
            }
        }

        return buildResponse(saved);
    }

    public List<ConstatResponse> listAll() {
        return constatRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::buildResponse)
                .toList();
    }

    private ConstatResponse buildResponse(Constat c) {
        List<String> images = constatImageRepository.findByConstatId(c.getId())
                .stream()
                .map(ConstatImage::getImageUrl)
                .toList();

        return ConstatResponse.builder()
                .id(c.getId())
                .controleurUsername(c.getControleur() != null ? c.getControleur().getUsername() : null)
                .farmId(c.getFarm() != null ? c.getFarm().getId() : null)
                .farmName(c.getFarm() != null ? c.getFarm().getName() : null)
                .controlSessionId(c.getControlSession() != null ? c.getControlSession().getId() : null)
                .type(c.getType())
                .description(c.getDescription())
                .latitude(c.getLatitude())
                .longitude(c.getLongitude())
                .localisationText(c.getLocalisationText())
                .imageUrls(images.isEmpty() ? Collections.emptyList() : images)
                .status(c.getStatus())
                .createdAt(c.getCreatedAt())
                .build();
    }
}
