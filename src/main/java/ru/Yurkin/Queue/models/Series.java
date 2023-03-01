package ru.Yurkin.Queue.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Entity
@Table(name="table_series")
@EqualsAndHashCode(exclude = {"seriesList"})
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Transient
    private List<PartOfSeries> seriesList;
    @Pattern(regexp ="([a-z]\\d)([a-z]\\d)+", message = "Серия должна быть записана в виде: a0a0...d4f7h9b12...")
    @Column(name="series")
    private String series;

    public Series() {
    }
    public Series(String series) throws IllegalArgumentException {
        this.setSeries(series);
    }

    public void setSeries(String series) throws IllegalArgumentException {
        Matcher matcher=PartOfSeries.splitSeriesOnParts(series);
        if(seriesList==null) seriesList= new ArrayList<>();
        else seriesList.clear();
        while (matcher.find()) {
            seriesList.add(new PartOfSeries(matcher.group()));
        }
        this.series=this.toString();
    }

    public String getSeries() {
        return series;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        this.seriesList.forEach(part -> result.append(part.toString()));
        return result.toString();
    }

    public Series next(){
        int i=seriesList.size()-1;
        boolean count;
        do{
            if(i==-1)
            {seriesList.add(new PartOfSeries());
            break;
            }
            count=seriesList.get(i).hasNext();
            seriesList.get(i--).next();
        }
        while(!count);
        this.series=this.toString();
        return this;
        }



}
