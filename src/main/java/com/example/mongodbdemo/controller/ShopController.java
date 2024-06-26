package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.model.Shop;
import com.example.mongodbdemo.repository.ShopRepository;
import jakarta.annotation.Resource;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private ShopRepository shopRepository;

    @Resource
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @GetMapping("")
    public List<Shop> getShops(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude,
                           @RequestParam("distanceInKm") int distanceInKm) {
        return shopRepository.findTop10ByLocationNearOrderByLocationDesc(new Point(longitude, latitude),
                new Distance(distanceInKm, Metrics.KILOMETERS));
    }

    @PostMapping("")
    public Shop addShop(@RequestBody Shop shop) {
        return shopRepository.insert(shop);
    }
}
