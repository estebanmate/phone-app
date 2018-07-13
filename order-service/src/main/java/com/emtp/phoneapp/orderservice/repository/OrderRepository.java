package com.emtp.phoneapp.orderservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.emtp.phoneapp.orderservice.model.Order;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}
