package com.emtp.phoneapp.catalogservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emtp.phoneapp.catalogservice.exceptions.NotFoundException;
import com.emtp.phoneapp.catalogservice.model.Phone;
import com.emtp.phoneapp.catalogservice.repository.CatalogRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	public Flux<Phone> find() {
		return this.catalogRepository.findAll()
				.switchIfEmpty(Mono.error(new NotFoundException(
				"Phone Database is empty")));
	}

	public Flux<Phone> find(final List<String> phoneIdList) {
		return this.catalogRepository.findAllById(phoneIdList)
				.switchIfEmpty(Mono.error(new NotFoundException(
				"Phone(s) id(s) not found --> (" + phoneIdList + ")")));
	}

	public Mono<Phone> save(final Phone phone) {
		return this.catalogRepository.save(phone);
	}

}
