package com.web.trade.view.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.web.trade.domain.order.Execution;
import com.web.trade.domain.order.Order;

public class OrderViewTest {

	@Test
	void test_getDetails1() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.build();
		final OrderView view = new OrderView(order);

		// exercise
		final List<OrderDetailView> result = view.getDetails();

		// verify
		assertThat(result).hasSize(1);
		assertThat(result.get(0)).isInstanceOf(UnfilledOrderDetailView.class);
	}

	@Test
	void test_getDetails2() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.execQty(BigDecimal.ONE)
				.executions(List.of(Execution.builder().build()))
				.build();
		final OrderView view = new OrderView(order);

		// exercise
		final List<OrderDetailView> result = view.getDetails();

		// verify
		assertThat(result).hasSize(2);
		assertThat(result).isInstanceOf(UnfilledOrderDetailView.class);
		assertThat(result).isInstanceOf(FilledOrderDetailView.class);
	}

	void test_getDetails3() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.execQty(BigDecimal.TEN)
				.executions(List.of(Execution.builder().build()))
				.build();
		final OrderView view = new OrderView(order);

		// exercise
		final List<OrderDetailView> result = view.getDetails();

		// verify
		assertThat(result).hasSize(1);
		assertThat(result).isInstanceOf(FilledOrderDetailView.class);
	}
}
