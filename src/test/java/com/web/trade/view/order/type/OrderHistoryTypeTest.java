package com.web.trade.view.order.type;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.web.trade.domain.order.Order;
import com.web.trade.enums.OrdStatus;

/**
 * OrderHistoryTypeTest
 */
public class OrderHistoryTypeTest {

	@Test
	void test_isDisplayUnfilledOrder1() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.status(OrdStatus.PENDING_NEW)
				.build();
		final OrderListViewType type = OrderHistoryType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isFalse();
	}

	@Test
	void test_isDisplayUnfilledOrder2() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.status(OrdStatus.NEW)
				.build();
		final OrderListViewType type = OrderHistoryType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isFalse();
	}

	@Test
	void test_isDisplayUnfilledOrder3() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.status(OrdStatus.PENDING_CANCEL)
				.build();
		final OrderListViewType type = OrderHistoryType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isFalse();
	}

	@Test
	void test_isDisplayUnfilledOrder4() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.status(OrdStatus.REJECT)
				.build();
		final OrderListViewType type = OrderHistoryType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isFalse();
	}

	@Test
	void test_isDisplayUnfilledOrder5() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.execQty(BigDecimal.TEN)
				.build();
		final OrderListViewType type = OrderHistoryType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isFalse();
	}

	@Test
	void test_isDisplayUnfilledOrder6() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.status(OrdStatus.EXPIRED)
				.build();
		final OrderListViewType type = OrderHistoryType.getInstance();

		// exercise
		final boolean result = type.isDisplayUnfilledOrder(order);

		// verify
		assertThat(result).isTrue();
	}
}
