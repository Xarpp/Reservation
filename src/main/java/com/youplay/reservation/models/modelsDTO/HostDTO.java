package com.youplay.reservation.models.modelsDTO;

public class HostDTO {
    private Long hostId;

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public HostDTO(Long hostId) {
        this.hostId = hostId;
    }
}