package com.web.trade.view.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.web.trade.domain.order.Order;
import com.web.trade.domain.order.OrderEstimate;
import com.web.trade.enums.OrdStatus;

public class UnfilledOrderDetailViewTest {

	@Test
	void test_getStatus1() {
		// setup
		final Order order = Order.builder()
				.status(OrdStatus.NEW)
				.build();
		final UnfilledOrderDetailView view = new UnfilledOrderDetailView(order);

		// exercise
		final OrdStatus result = view.getStatus();

		// verify
		assertThat(result).isEqualTo(OrdStatus.NEW);
	}

	@Test
	void test_getStatus2() {
		// setup
		final Order order = Order.builder()
				.status(OrdStatus.PARTIALLY_FILLED)
				.build();
		final UnfilledOrderDetailView view = new UnfilledOrderDetailView(order);

		// exercise
		final OrdStatus result = view.getStatus();

		// verify
		assertThat(result).isEqualTo(OrdStatus.PENDING_CANCEL);
	}

	@Test
	void test_getStatus3() {
		// setup
		final Order order = Order.builder()
				.status(OrdStatus.PARTIALLY_FILLED)
				.expiredQty(BigDecimal.TEN)
				.build();
		final UnfilledOrderDetailView view = new UnfilledOrderDetailView(order);

		// exercise
		final OrdStatus result = view.getStatus();

		// verify
		assertThat(result).isEqualTo(OrdStatus.EXPIRED);
	}

	@Test
	void test_getSettlement1() {
		// setup
		final Order order = Order.builder()
				.status(OrdStatus.NEW)
				.estimate(createEstimate(BigDecimal.TEN, LocalDate.of(2000, 1, 1)))
				.build();
		final UnfilledOrderDetailView view = new UnfilledOrderDetailView(order);

		// exercise
		final BigDecimal amount = view.getSettlementAmount();
		final LocalDate date = view.getSettlementDate();

		// verify
		assertThat(amount).isEqualTo(BigDecimal.TEN);
		assertThat(date).isEqualTo(LocalDate.of(2000, 1, 1));
	}

	@Test
	void test_getSettlement2() {
		// setup
		final Order order = Order.builder()
				.status(OrdStatus.PENDING_CANCEL)
				.estimate(createEstimate(BigDecimal.TEN, LocalDate.of(2000, 1, 1)))
				.build();
		final UnfilledOrderDetailView view = new UnfilledOrderDetailView(order);

		// exercise
		final BigDecimal amount = view.getSettlementAmount();
		final LocalDate date = view.getSettlementDate();

		// verify
		assertThat(amount).isNull();
		assertThat(date).isNull();
	}

	/**
	 * 概算値作成
	 * @param settlementAmount
	 * @param settlementDate
	 * @return estimate
	 */
	private OrderEstimate createEstimate(final BigDecimal settlementAmount, final LocalDate settlementDate) {
		return OrderEstimate.builder()
				.settlementAmount(settlementAmount)
				.settlementDate(settlementDate)
				.build();
	}
}
