package com.emtp.phoneapp.orderservice.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.emtp.phoneapp.orderservice.controller.OrderController;
import com.emtp.phoneapp.orderservice.model.Order;
import com.emtp.phoneapp.orderservice.model.OrderDetails;
import com.emtp.phoneapp.orderservice.service.OrderService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RunWith(SpringRunner.class)
@WebFluxTest(OrderController.class)
public class OrderServiceApplicationTest {

	@Autowired
	private WebTestClient webClient;
	
	@MockBean
	private OrderService orderService;

	@Test
	public void create() {
		final Order order = createOrdersList().get(0);
	
		given(this.orderService.save(any(Order.class))).willReturn(Mono.just(order));
	
		webClient
			.post()
			.uri("/order_api/order")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(order))
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Order.class)
			.isEqualTo(order);
	}

	@Test
	public void all() {
		final List<Order> phonesList = createOrdersList();
		final Order[] ordersArray = phonesList.toArray(new Order[phonesList.size()]);

		given(this.orderService.find()).willReturn(Flux.just(ordersArray));

		webClient
				.get()
				.uri("/order_api/order")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Order.class)
				.hasSize(2).contains(ordersArray);
	}

	private List<Order> createOrdersList() {
		return List.of(
				createOrder("Juan", "Martin", "juan@martin.com", List.of(new OrderDetails("1", 2), new OrderDetails("2", 1))),
				createOrder("Pilar", "Dato", "pilar@dato.com", List.of(new OrderDetails("3", 1), new OrderDetails("4", 3)))
		);
	}

	private Order createOrder(String customerName, String customerSurname, String email, List<OrderDetails> phoneIds) {
		return new Order(String.valueOf(System.currentTimeMillis()), customerName, customerSurname, email, phoneIds, BigDecimal.ZERO);
	}
}
