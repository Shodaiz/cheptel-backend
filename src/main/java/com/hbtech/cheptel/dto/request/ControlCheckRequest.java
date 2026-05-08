package com.hbtech.cheptel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ControlCheckRequest {

    @Schema(example = "1")
    private Long farmId;

    @Schema(example = "[\"DZA-2024-00123\", \"DZA-2024-00124\", \"DZA-2024-00125\"]")
    private List<String> scannedRfidTags;

    public ControlCheckRequest() {}

    public ControlCheckRequest(Long farmId, List<String> scannedRfidTags) {
        this.farmId = farmId;
        this.scannedRfidTags = scannedRfidTags;
    }

    public Long getFarmId() { return farmId; }
    public void setFarmId(Long farmId) { this.farmId = farmId; }

    public List<String> getScannedRfidTags() { return scannedRfidTags; }
    public void setScannedRfidTags(List<String> scannedRfidTags) { this.scannedRfidTags = scannedRfidTags; }
}
