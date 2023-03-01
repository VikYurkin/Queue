package ru.Yurkin.Queue.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;

public class SeriesDTO {
    @Pattern(regexp ="([a-z]\\d)([a-z]\\d)+", message = "Серия должна быть записана в виде: a0a0...d4f7h9b12...")
    @Column(name="series")
    private String series;

    public SeriesDTO() {
    }
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
