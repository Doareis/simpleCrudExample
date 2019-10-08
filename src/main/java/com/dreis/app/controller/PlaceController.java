package com.dreis.app.controller;

import com.dreis.app.dao.PlaceDAO;
import com.dreis.app.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceDAO placeDAO;

    @GetMapping("/all")
    public List<Place> getPlaces() {
        return this.placeDAO.findAll();
    }

    @GetMapping("/all")
    public Response<Place> getPlaces(@RequestParam("id") Long id) {
        Optional<Place> placeOptional = this.placeDAO.findById(id);
        if (placeOptional.isPresent()) {
            return new Response<Place>() {
                @Override
                public Map<String, Object> getContext() {
                    return null;
                }

                @Override
                public boolean cancel(boolean b) {
                    return false;
                }

                @Override
                public boolean isCancelled() {
                    return false;
                }

                @Override
                public boolean isDone() {
                    return false;
                }

                @Override
                public Place get() throws InterruptedException, ExecutionException {
                    return null;
                }

                @Override
                public Place get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                    return null;
                }
            }placeOptional.get();
        }

    }
}
