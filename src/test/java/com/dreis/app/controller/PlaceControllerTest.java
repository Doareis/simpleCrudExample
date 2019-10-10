package com.dreis.app.controller;

import com.dreis.app.dto.PlaceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlaceControllerTest {

    private static final String URI = "http://localhost:8080";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test1_findAllPlaces() throws Exception {
        String endPoint = "/places/all";
        this.mockMvc.perform(get(URI + endPoint))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(9)));
    }

    @Test
    public void test2_findOnePlaceSuccesfully() throws Exception {
        String endPoint = "/places/find/1";
        this.mockMvc.perform(get(URI + endPoint))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", Matchers.is(1)))
                .andExpect(jsonPath("city", Matchers.is("São Paulo")))
                .andExpect(jsonPath("state", Matchers.is("SP")))
                .andExpect(jsonPath("name", Matchers.is("Pq Ibirapuera")));
    }

    @Test
    public void test3_tryToFindAPlaceThatDoesNotExist() throws Exception {
        String endPoint = "/places/find/1000";
        this.mockMvc.perform(get(URI + endPoint))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test4_addPlace() throws Exception {
        String endPoint = "/places/add";
        PlaceDTO dto = PlaceDTO.of("Praça da Sé", "", "São Paulo", "SP");

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post(URI + endPoint)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void test5_tryToUpdateAPlaceThatDoesNotExist() throws Exception {
        String endPoint = "/places/update/100";
        PlaceDTO dto = PlaceDTO.of("Praça da Sé", "", "São Paulo", "SP");
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post(URI + endPoint)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }
}