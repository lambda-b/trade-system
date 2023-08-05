package com.web.trade.domain.market;

import java.math.BigDecimal;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 売気配
 */
@Getter
@AllArgsConstructor
public class AskQuote implements Quote, Comparable<AskQuote> {

	/** COMPARATOR */
	private static Comparator<BigDecimal> COMPARATOR = Comparator.nullsFirst(Comparator.naturalOrder());

	/** price */
	private final BigDecimal price;

	/** qty */
	private final BigDecimal qty;

	@Override
	public boolean isBetterOrEqual(final BigDecimal price) {
		final BigDecimal self = COMPARATOR.compare(this.price, Quote.MARKET_PRICE) == 0 ? null : price;
		final BigDecimal param = COMPARATOR.compare(price, Quote.MARKET_PRICE) == 0 ? null : price;
		return COMPARATOR.compare(self, param) <= 0;
	}

	@Override
	public int compareTo(final AskQuote other) {
		final BigDecimal self = COMPARATOR.compare(this.price, Quote.MARKET_PRICE) == 0 ? null : price;
		final BigDecimal param = COMPARATOR.compare(price, Quote.MARKET_PRICE) == 0 ? null : price;
		return COMPARATOR.compare(self, param);
	}

}
