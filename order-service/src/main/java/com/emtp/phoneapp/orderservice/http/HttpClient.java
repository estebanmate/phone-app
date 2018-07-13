package com.emtp.phoneapp.orderservice.http;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.emtp.phoneapp.orderservice.model.Phone;

import reactor.core.publisher.Flux;

@Component
public class HttpClient {

	private final static Logger LOGGER = Logger.getLogger(HttpClient.class.getName());

	private final WebClient webClient;
	
	public HttpClient(@Value("${catalog.service.url}") final String catalogServiceUrl) {
		this.webClient = WebClient.builder()
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.baseUrl(catalogServiceUrl)
				.build();
	}
	
	public Flux<Phone> retrievePhones(final List<String> phoneId) {
		return this.webClient
				.get()
				.uri(queryBuilder -> queryBuilder
						.path("/catalog_api/phone")
						.queryParams(new LinkedMultiValueMap<>(Map.of("phoneId", phoneId)))
						.build())
				.retrieve()
				.bodyToFlux(Phone.class)
				.doOnError(error -> LOGGER.severe("Phone(s) id(s) not found --> (" + phoneId + ")"));
	}
	
}
