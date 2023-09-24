package com.web.trade.view.order.type;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.web.trade.domain.order.Order;

/**
 * CurrentOrderListTypeTest
 */
public class CurrentOrderListTypeTest {

	@Test
	void test_isDisplayUnfilledOrder1() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.build();
		final OrderListViewType type = CurrentOrderListType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isTrue();
	}

	@Test
	void test_isDisplayUnfilledOrder2() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.execQty(BigDecimal.ONE)
				.build();
		final OrderListViewType type = CurrentOrderListType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isTrue();
	}

	@Test
	void test_isDisplayUnfilledOrder3() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.execQty(BigDecimal.TEN)
				.build();
		final OrderListViewType type = CurrentOrderListType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isFalse();
	}
}
