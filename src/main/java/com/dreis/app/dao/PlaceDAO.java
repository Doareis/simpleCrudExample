package com.dreis.app.dao;


import com.dreis.app.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PlaceDAO extends JpaRepository<Place, Long> {
}
