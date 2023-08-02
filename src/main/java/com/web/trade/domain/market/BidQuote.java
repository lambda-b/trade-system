package com.web.trade.domain.market;

import java.math.BigDecimal;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 買気配
 */
@Getter
@AllArgsConstructor
public class BidQuote implements Quote<BidQuote> {

	/** COMPARATOR */
	private static Comparator<BigDecimal> COMPARATOR = Comparator.nullsFirst(Comparator.reverseOrder());

	/** price */
	private final BigDecimal price;

	/** qty */
	private final BigDecimal qty;

	@Override
	public boolean isBetterOrEqual(final BigDecimal price) {
		return COMPARATOR.compare(this.price, price) <= 0;
	}

	@Override
	public int compareTo(final BidQuote other) {
		return COMPARATOR.compare(this.price, other.price);
	}
}
