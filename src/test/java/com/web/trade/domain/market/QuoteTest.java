package com.web.trade.domain.market;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Quoteのテストクラス
 */
public class QuoteTest {

	private static BigDecimal MARKET_PRICE = Quote.MARKET_PRICE;

	private static BigDecimal ONE = BigDecimal.ONE;

	private static BigDecimal TEN = BigDecimal.TEN;

	@Test
	@DisplayName("売り気配のisBetterOrEqualテスト")
	void test_isBetterOrEqual() {

		// verify
		assertThat(new AskQuote(MARKET_PRICE, null).isBetterOrEqual(MARKET_PRICE)).isTrue();
		assertThat(new AskQuote(MARKET_PRICE, null).isBetterOrEqual(ONE)).isTrue();
		assertThat(new AskQuote(ONE, null).isBetterOrEqual(MARKET_PRICE)).isFalse();
		assertThat(new AskQuote(ONE, null).isBetterOrEqual(ONE)).isTrue();
		assertThat(new AskQuote(ONE, null).isBetterOrEqual(TEN)).isTrue();
	}
}
