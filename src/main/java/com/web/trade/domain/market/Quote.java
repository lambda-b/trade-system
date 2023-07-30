package com.web.trade.domain.market;

import java.math.BigDecimal;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 気配クラス
 */
@Getter
@AllArgsConstructor
public class Quote implements Comparable<Quote> {

	/** COMPARATOR */
	private static Comparator<Integer> COMPARATOR = Comparator.nullsFirst(Comparator.naturalOrder());

	/** index */
	private final Integer index;

	/** price */
	private final BigDecimal price;

	/** quantity */
	private final BigDecimal quantity;

	@Override
	public int compareTo(final Quote other) {
		return COMPARATOR.compare(this.index, other.index);
	}

}
