package com.example.exam_web.model.binding;

public class ShipIdModel {
    private Long idCurrent;
    private Long idOther;

    public Long getIdCurrent() {
        return idCurrent;
    }

    public ShipIdModel setIdCurrent(Long idCurrent) {
        this.idCurrent = idCurrent;
        return this;
    }

    public Long getIdOther() {
        return idOther;
    }

    public ShipIdModel setIdOther(Long idOther) {
        this.idOther = idOther;
        return this;
    }
}
