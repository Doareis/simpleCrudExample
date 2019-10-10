package com.dreis.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceDTO implements Serializable {

    private String name;

    private String slug;

    private String city;

    private String state;
}
