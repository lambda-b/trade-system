package com.web.trade.domain.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class OrderTest {

	@Test
	void test1() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.build();

		// exercise
		final BigDecimal unfilledQty = order.getUnfilledQty();
		final boolean isFullyFilled = order.isFullyFilled();

		// verify
		assertThat(unfilledQty).isEqualTo(BigDecimal.TEN);
		assertThat(isFullyFilled).isFalse();
	}

	@Test
	void test2() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.execQty(BigDecimal.ONE)
				.build();

		// exercise
		final BigDecimal unfilledQty = order.getUnfilledQty();
		final boolean isFullyFilled = order.isFullyFilled();

		// verify
		assertThat(unfilledQty).isEqualTo(new BigDecimal(9));
		assertThat(isFullyFilled).isFalse();
	}

	@Test
	void test3() {
		// setup
		final Order order = Order.builder()
				.qty(BigDecimal.TEN)
				.execQty(BigDecimal.TEN)
				.build();

		// exercise
		final BigDecimal unfilledQty = order.getUnfilledQty();
		final boolean isFullyFilled = order.isFullyFilled();

		// verify
		assertThat(unfilledQty).isEqualTo(BigDecimal.ZERO);
		assertThat(isFullyFilled).isTrue();
	}
}
