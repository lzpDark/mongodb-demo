package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.model.Shop;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {

    public List<Shop> findTop10ByLocationNearOrderByLocationDesc(Point point, Distance distance);
}
