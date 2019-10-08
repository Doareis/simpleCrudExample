package com.dreis.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceDTO {

    private String name;

    private String slug;

    private String city;

    private String state;

    private Date createdAt;

    private Date updatedAt;
}
