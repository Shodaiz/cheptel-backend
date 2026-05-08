package com.hbtech.cheptel.service;

import com.hbtech.cheptel.entity.*;
import com.hbtech.cheptel.repository.AlertRepository;
import com.hbtech.cheptel.repository.HealthRecordRepository;
import com.hbtech.cheptel.repository.VaccinationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final VaccinationRepository vaccinationRepository;
    private final HealthRecordRepository healthRecordRepository;

    public AlertService(AlertRepository alertRepository,
                        VaccinationRepository vaccinationRepository,
                        HealthRecordRepository healthRecordRepository) {
        this.alertRepository = alertRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.healthRecordRepository = healthRecordRepository;
    }

    @Transactional
    public List<Alert> generateAlerts(Farm farm) {
        LocalDate today = LocalDate.now();
        LocalDate horizon = today.plusDays(30);
        List<Alert> created = new ArrayList<>();

        for (Vaccination v : vaccinationRepository.findUpcomingForFarm(farm.getId(), today, horizon)) {
            Animal animal = v.getAnimal();
            if (animal == null || v.getNextVaccinationDate() == null) continue;
            if (alertRepository.findDuplicate(farm.getId(), AlertType.VACCINATION_DUE,
                    animal.getId(), v.getNextVaccinationDate()).isEmpty()) {
                created.add(alertRepository.save(Alert.builder()
                        .alertType(AlertType.VACCINATION_DUE)
                        .message("Rappel vaccin " + v.getVaccineName() + " — " + animal.getRfidTag())
                        .dueDate(v.getNextVaccinationDate())
                        .severity(AlertSeverity.WARNING)
                        .farm(farm)
                        .animal(animal)
                        .build()));
            }
        }

        for (HealthRecord hr : healthRecordRepository.findUpcomingVisitsForFarm(farm.getId(), today, horizon)) {
            Animal animal = hr.getAnimal();
            LocalDate due = hr.getNextVisitDate() != null ? hr.getNextVisitDate().toLocalDate() : null;
            if (animal == null || due == null) continue;
            if (alertRepository.findDuplicate(farm.getId(), AlertType.CHECKUP_DUE,
                    animal.getId(), due).isEmpty()) {
                created.add(alertRepository.save(Alert.builder()
                        .alertType(AlertType.CHECKUP_DUE)
                        .message("Visite prévue — " + animal.getRfidTag())
                        .dueDate(due)
                        .severity(AlertSeverity.INFO)
                        .farm(farm)
                        .animal(animal)
                        .build()));
            }
        }

        return created;
    }

    public List<Alert> getAlertsForFarm(Long farmId) {
        return alertRepository.findByFarmIdOrderByDueDateAsc(farmId);
    }

    @Transactional
    public void resolveAlert(Long alertId) {
        alertRepository.findById(alertId).ifPresent(a -> {
            a.setIsResolved(Boolean.TRUE);
            alertRepository.save(a);
        });
    }
}
