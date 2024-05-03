package org.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends  BaseModel{
    private String name;
    private Region region;
    private List<Screen> screens;
}
