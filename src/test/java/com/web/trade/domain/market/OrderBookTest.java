package com.web.trade.domain.market;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * OrderBookTest
 */
public class OrderBookTest {

	@Test
	@DisplayName("宣言時のソートの確認")
	void test_sort() {
		// setup
		final BidQuote large = new BidQuote(new BigDecimal("99"), null);
		final BidQuote small = new BidQuote(new BigDecimal("100"), null);
		final BidQuote marketPrice = new BidQuote(null, null);
		final List<BidQuote> quotes = List.of(large, small, marketPrice);

		// exercise
		final OrderBook book = OrderBook.builder().bidFull(quotes).build();

		// verify
		assertThat(book.getBidFull()).isEqualTo(List.of(marketPrice, small, large));
	}
}
