package com.web.trade.domain.market;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		final OrderBook book = OrderBook.builder().bidQuotes(quotes).build();

		// verify
		assertThat(book.getBidQuotes()).isEqualTo(List.of(marketPrice, small, large));
	}

	@Test
	@DisplayName("ケース1")
	void test_case1() {
		// setup
		final List<AskQuote> askQuotes = createAskQuotes(5, 100, 101, 103);
		final List<BidQuote> bidQuotes = createBidQuotes(6, 93, 99, 100);
		final OrderBook book = createOrderBook(new BigDecimal("100"), askQuotes, bidQuotes);

		// verify
		assertThat(book.getExpectedExecPrice()).isEqualTo(new BigDecimal("100"));
		assertThat(book.getMarketAskQty()).isEqualTo(new BigDecimal("5"));
		assertThat(book.getMarketBidQty()).isEqualTo(new BigDecimal("6"));
		assertThat(book.getBestAskPrice()).isEqualTo(new BigDecimal("101"));
		assertThat(book.getBestBidPrice()).isEqualTo(new BigDecimal("100"));
		assertThat(book.getAskBoard().get(0).getQty()).isEqualTo(new BigDecimal("7"));
		assertThat(book.getBidBoard().get(0).getQty()).isEqualTo(new BigDecimal("7"));
		assertThat(getPrices(book.getAskBoard())).isEqualTo(List.of(101, 103));
		assertThat(getPrices(book.getBidBoard())).isEqualTo(List.of(100, 99, 93));
		assertThat(book.getOverAskQty()).isEqualTo(new BigDecimal("0"));
		assertThat(book.getUnderBidQty()).isEqualTo(new BigDecimal("0"));
	}

	private OrderBook createOrderBook(final BigDecimal expectedExecPrice, final List<AskQuote> askQuotes,
			final List<BidQuote> bidQuotes) {
		return OrderBook.builder()
				.expectedExecPrice(expectedExecPrice)
				.askQuotes(askQuotes)
				.bidQuotes(bidQuotes)
				.tick(BigDecimal.ONE)
				.build();
	}

	private List<AskQuote> createAskQuotes(final Integer marketQty, final Integer... values) {
		final List<AskQuote> quotes = Stream.of(values)
				.map(e -> new AskQuote(BigDecimal.valueOf(e), BigDecimal.ONE))
				.collect(Collectors.toList());

		if (marketQty == null) {
			return quotes;
		}
		quotes.add(new AskQuote(Quote.MARKET_PRICE, BigDecimal.valueOf(marketQty)));
		return quotes;
	}

	private List<BidQuote> createBidQuotes(final Integer marketQty, final Integer... values) {
		final List<BidQuote> quotes = Stream.of(values)
				.map(e -> new BidQuote(BigDecimal.valueOf(e), BigDecimal.ONE))
				.collect(Collectors.toList());

		if (marketQty == null) {
			return quotes;
		}
		quotes.add(new BidQuote(Quote.MARKET_PRICE, BigDecimal.valueOf(marketQty)));
		return quotes;
	}

	private <T extends Quote> List<Integer> getPrices(final List<T> quotes) {
		return quotes.stream()
				.map(Quote::getPrice)
				.map(BigDecimal::intValue)
				.collect(Collectors.toList());
	}
}
