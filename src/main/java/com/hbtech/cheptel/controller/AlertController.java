package com.hbtech.cheptel.controller;

import com.hbtech.cheptel.entity.Alert;
import com.hbtech.cheptel.entity.User;
import com.hbtech.cheptel.service.AlertService;
import com.hbtech.cheptel.service.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;
    private final CurrentUserService currentUserService;

    public AlertController(AlertService alertService, CurrentUserService currentUserService) {
        this.alertService = alertService;
        this.currentUserService = currentUserService;
    }

    @PostMapping("/generate")
    @PreAuthorize("hasRole('FERMIER')")
    public ResponseEntity<?> generateAlerts() {
        User current = currentUserService.getCurrentUserOrThrow();
        if (current.getFarm() == null) {
            return ResponseEntity.status(403).body(Map.of("message", "Aucune ferme associée"));
        }
        List<Alert> generated = alertService.generateAlerts(current.getFarm());
        return ResponseEntity.ok(Map.of(
                "generated", generated.size(),
                "message", "Alertes générées avec succès"
        ));
    }

    @PutMapping("/{id}/resolve")
    @PreAuthorize("hasRole('FERMIER')")
    public ResponseEntity<?> resolveAlert(@PathVariable Long id) {
        alertService.resolveAlert(id);
        return ResponseEntity.ok(Map.of("message", "Alerte résolue"));
    }
}
