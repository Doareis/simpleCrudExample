package com.dreis.app.controller;

import com.dreis.app.dao.PlaceDAO;
import com.dreis.app.dto.PlaceDTO;
import com.dreis.app.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceDAO placeDAO;

    @GetMapping("/all")
    public List<Place> getPlaces() {
        return this.placeDAO.findAll();
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Place> getPlaces(@PathVariable("id") Long id) {
        Optional<Place> placeOptional = this.placeDAO.findById(id);
        if (placeOptional.isPresent()) {
            return ResponseEntity.ok().body(placeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addPlace(@RequestBody PlaceDTO placeDto) {
        Place place = Place.of(placeDto);
        place.setCreatedAt(new Date());
        placeDAO.save(place);
        return new ResponseEntity<>(place.getId(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Place> updatePlace(@PathVariable Long id, @RequestBody PlaceDTO placeDTO) {
        Optional<Place> placeOptional = this.placeDAO.findById(id);
        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
            place.copy(placeDTO);
            place.setUpdatedAt(new Date());
            placeDAO.save(place);
            return ResponseEntity.ok().body(place);
        }
        return ResponseEntity.notFound().build();
    }
}
