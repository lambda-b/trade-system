package com.web.trade.domain.market;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * OrderBookTest
 */
public class OrderBookTest {

	@Test
	@DisplayName("コンストラクタでソートの確認")
	void test_sort() {
		// setup
		final Quote large = new Quote(1, null, null);
		final Quote small = new Quote(-1, null, null);
		final Quote marketPrice = new Quote(null, null, null);
		final List<Quote> quotes = List.of(large, small, marketPrice);

		// exercise
		final OrderBook book = new OrderBook(null, null, quotes, quotes);

		// verify
		assertThat(book.getAskFull()).isEqualTo(List.of(marketPrice, small, large));
		assertThat(book.getBidFull()).isEqualTo(List.of(marketPrice, small, large));
	}
}
