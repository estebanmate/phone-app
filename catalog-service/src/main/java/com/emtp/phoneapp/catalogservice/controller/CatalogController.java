package com.emtp.phoneapp.catalogservice.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emtp.phoneapp.catalogservice.model.Phone;
import com.emtp.phoneapp.catalogservice.service.CatalogService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/catalog_api/phone")
public class CatalogController {

	private final CatalogService catalogService;

	public CatalogController(final CatalogService catalogService) {
		this.catalogService = catalogService;
	} 
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Phone> all() {
		return this.catalogService.find();
	}

	@GetMapping(params = "phoneId")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Phone> allIn(@RequestParam(value = "phoneId") final List<String> phoneIdList) {
		return this.catalogService.find(phoneIdList);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Phone> create(@Valid @RequestBody final Phone phone) {
		return this.catalogService.save(phone);
	}

}
