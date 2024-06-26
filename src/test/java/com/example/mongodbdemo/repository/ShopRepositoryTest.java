package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.model.Shop;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShopRepositoryTest {

    @Autowired
    ShopRepository shopRepository;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        shopRepository.deleteAll();
    }

    @Test
    void testFindTop10ByLocationNearOrderByLocationDesc() {
        shopRepository.insert(constructShop(0, 0));
        shopRepository.insert(constructShop(0, 0.0001));
        shopRepository.insert(constructShop(10, 10));
        List<Shop> shops = shopRepository.findTop10ByLocationNearOrderByLocationDesc(
                new Point(0, 0),
                new Distance(1, Metrics.KILOMETERS)
        );
        Assertions.assertEquals(2, shops.size());
    }

    private Shop constructShop(double longitude, double latitude) {
        Shop shop = new Shop();
        shop.setLocation(new GeoJsonPoint(longitude, latitude));
        return shop;
    }

    private String constructShopJson(double longitude, double latitude) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(constructShop(longitude, latitude));
    }

    @Test
    public void testAddShop() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(constructShopJson(0, 0))
                )
                .andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(constructShopJson(1, 1))
                )
                .andDo(print()).andExpect(status().isOk());
        List<Shop> shops = shopRepository.findAll();
        Assertions.assertEquals(2, shops.size());
    }
}