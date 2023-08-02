package com.web.trade.domain.market;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderBook {

	/** 予想約定価格 */
	private final BigDecimal expectedExecutionPrice;

	/** 参考価格　*/
	private final BigDecimal referencePrice;

	/** フル板情報(売) */
	private final List<AskQuote> askFull;

	/** フル板情報(買) */
	private final List<BidQuote> bidFull;

	/** 呼値 */
	private final BigDecimal tick;

	public BigDecimal getBasePrice() {
		return expectedExecutionPrice != null ? expectedExecutionPrice : referencePrice;
	}

	public BigDecimal getBaseAskQty() {
		final BigDecimal basePrice = getBasePrice();
		return getBetterQty(basePrice, askFull);
	}

	public BigDecimal getBaseBidQty() {
		final BigDecimal basePrice = getBasePrice();
		return getBetterQty(basePrice, bidFull);
	}

	public BigDecimal getBestAskPrice() {
		final BigDecimal basePrice = getBasePrice();
		final BigDecimal baseAskQty = getBaseAskQty();
		final BigDecimal baseBidQty = getBaseBidQty();
		if (baseAskQty.compareTo(baseBidQty) < 0) {
			return basePrice.add(tick);
		}
		return basePrice;
	}

	public BigDecimal getBestBidPrice() {
		final BigDecimal basePrice = getBasePrice();
		final BigDecimal baseAskQty = getBaseAskQty();
		final BigDecimal baseBidQty = getBaseBidQty();
		if (baseAskQty.compareTo(baseBidQty) > 0) {
			return basePrice.subtract(tick);
		}
		return basePrice;
	}

	public BigDecimal getBestAskQty() {
		final BigDecimal bestPrice = getBestAskPrice();
		return getBetterQty(bestPrice, askFull);
	}

	public BigDecimal getBestBidQty() {
		final BigDecimal bestPrice = getBestAskPrice();
		return getBetterQty(bestPrice, bidFull);
	}

	private static <T extends Quote> BigDecimal getBetterQty(final BigDecimal price, final Collection<T> collection) {
		return collection.stream()
				.filter(e -> e.isBetterOrEqual(price))
				.map(Quote::getQty)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}