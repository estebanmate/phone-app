package com.emtp.phoneapp.orderservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.emtp.phoneapp.orderservice.http.HttpClient;
import com.emtp.phoneapp.orderservice.model.Order;
import com.emtp.phoneapp.orderservice.model.OrderDetails;
import com.emtp.phoneapp.orderservice.repository.OrderRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

	private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());

	private final OrderRepository orderRepository;
	private final HttpClient httpClient;

	public OrderService(final OrderRepository orderRepository, final HttpClient httpClient) {
		this.orderRepository = orderRepository;
		this.httpClient = httpClient;
	}

	public Mono<Order> save(final Order order) {
		final List<OrderDetails> orderDetails = order.getOrderDetails().stream()
				.filter(distinctByProperty(OrderDetails::getId))
				.collect(Collectors.toList());

		final Map<String, Integer> phoneToAmount = orderDetails.stream()
				.collect(Collectors.toMap(OrderDetails::getId, OrderDetails::getAmount));

		return this.httpClient.retrievePhones(new ArrayList<>(phoneToAmount.keySet()))
				.map(phone -> {
					final Integer amount = phoneToAmount.get(phone.getId());
					final BigDecimal multiplicand = BigDecimal.valueOf(amount);
					return phone.getPrice().multiply(multiplicand);
				})
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.flatMap(sum -> {
					order.setTotalPrice(sum);
					order.setOrderDetails(orderDetails);
					return this.orderRepository.save(order);
				}).doOnSuccess(savedOrder -> LOGGER.info(savedOrder.toString()))
					.doOnError(error -> LOGGER.severe("Failed to save order, msg=" + error.getMessage()));
	}

	private static <T> Predicate<T> distinctByProperty(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	public Flux<Order> find() {
		return this.orderRepository.findAll();
	}

}
