package com.web.trade.view.order;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.web.trade.domain.order.Order;
import com.web.trade.enums.OrdStatus;

public class FilledOrderDetailViewTest {

	@Test
	void test_getStatus1() {
		// setup
		final Order order = Order.builder()
				.status(OrdStatus.FILLED)
				.build();
		final FilledOrderDetailView view = new FilledOrderDetailView(order, null);

		// exercise
		final OrdStatus result = view.getStatus();

		// verify
		assertThat(result).isEqualTo(OrdStatus.FILLED);
	}

	@Test
	void test_getStatus2() {
		// setup
		final Order order = Order.builder()
				.status(OrdStatus.NEW)
				.build();
		final FilledOrderDetailView view = new FilledOrderDetailView(order, null);

		// exercise
		final OrdStatus result = view.getStatus();

		// verify
		assertThat(result).isEqualTo(OrdStatus.PARTIALLY_FILLED);
	}
}
