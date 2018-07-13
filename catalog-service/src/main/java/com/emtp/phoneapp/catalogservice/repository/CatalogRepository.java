package com.emtp.phoneapp.catalogservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.emtp.phoneapp.catalogservice.model.Phone;

public interface CatalogRepository extends ReactiveMongoRepository<Phone, String> {

}
