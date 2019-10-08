package com.dreis.app.model;

import com.dreis.app.dto.PlaceDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public static Place of(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setName(placeDTO.getName());
        place.setSlug(placeDTO.getSlug());
        place.setState(placeDTO.getState());
        place.setCity(placeDTO.getCity());

        return place;
    }

    public void copy(PlaceDTO placeDTO) {
        this.setName(placeDTO.getName());
        this.setSlug(placeDTO.getSlug());
        this.setState(placeDTO.getState());
        this.setCity(placeDTO.getCity());
    }
}
