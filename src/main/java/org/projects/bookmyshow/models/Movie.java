package org.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Movie extends  BaseModel {
    private String name;
    private Date releaseDate;
    private List<Feature> features;
}
