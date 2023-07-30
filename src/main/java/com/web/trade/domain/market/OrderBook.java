package com.web.trade.domain.market;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class OrderBook {

	/** 予想約定価格 */
	private final BigDecimal expectedExecutionPrice;

	/** 参考価格　*/
	private final BigDecimal referencePrice;

	/** フル板情報(売) */
	private final List<Quote> askFull;

	/** フル板情報(買) */
	private final List<Quote> bidFull;

	/**
	 * コンストラクタ
	 * @param expectedExcutionPrice
	 * @param referencePrice
	 * @param askFull
	 * @param bidFull
	 */
	public OrderBook(final BigDecimal expectedExecutionPrice, final BigDecimal referencePrice,
			final List<Quote> askFull, final List<Quote> bidFull) {
		this.expectedExecutionPrice = expectedExecutionPrice;
		this.referencePrice = referencePrice;
		this.askFull = askFull.stream().sorted().collect(Collectors.toList());
		this.bidFull = bidFull.stream().sorted().collect(Collectors.toList());
	}
}
