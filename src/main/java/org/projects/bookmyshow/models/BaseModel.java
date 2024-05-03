package org.projects.bookmyshow.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID will be generated in Auto Increment way
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
}

// We don't want the table of Base Model Class
// Rather we want all the attrs of Base Model Class to be present in the model tables
// Thus we use @MappedSuperclass annotation