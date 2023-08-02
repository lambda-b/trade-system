package com.web.trade.domain.market;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder")
public class OrderBook {

	/** 予想約定価格 */
	private final BigDecimal expectedExecutionPrice;

	/** 参考価格　*/
	private final BigDecimal referencePrice;

	/** フル板情報(売) - Builder処理でソートされているものとして取り扱う */
	private final List<AskQuote> askFull;

	/** フル板情報(買) - Builder処理でソートされているものとして取り扱う */
	private final List<BidQuote> bidFull;

	/** 呼値 */
	private final BigDecimal tick;

	/** 表示件数 */
	private final static int DISPLAY_NUMBER = 10;

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

	public List<AskQuote> getDisplayAskBoard() {
		final BigDecimal bestPrice = getBestAskPrice();
		final BigDecimal bestQty = getBetterQty(bestPrice, askFull);
		final AskQuote bestQuote = new AskQuote(bestPrice, bestQty);
		return Stream.concat(Stream.of(bestQuote), askFull.stream()
				.filter(e -> !e.isBetterOrEqual(bestPrice))
				.limit(DISPLAY_NUMBER - 1))
				.collect(Collectors.toList());
	}

	public List<BidQuote> getDisplayBidBoard() {
		final BigDecimal bestPrice = getBestBidPrice();
		final BigDecimal bestQty = getBetterQty(bestPrice, bidFull);
		final BidQuote bestQuote = new BidQuote(bestPrice, bestQty);
		return Stream.concat(Stream.of(bestQuote), bidFull.stream()
				.filter(e -> !e.isBetterOrEqual(bestPrice))
				.limit(DISPLAY_NUMBER - 1))
				.collect(Collectors.toList());
	}

	public BigDecimal getOverAskQty() {
		final BigDecimal lastDisplayPrice = getDisplayAskBoard().stream()
				.reduce((first, second) -> second)
				.map(Quote::getPrice)
				.orElse(null);
		return getWorseQty(lastDisplayPrice, askFull);
	}

	public BigDecimal getUnderBidQty() {
		final BigDecimal lastDisplayPrice = getDisplayBidBoard().stream()
				.reduce((first, second) -> second)
				.map(Quote::getPrice)
				.orElse(null);
		return getWorseQty(lastDisplayPrice, bidFull);
	}

	private static <T extends Quote<T>> BigDecimal getBetterQty(final BigDecimal price,
			final Collection<T> collection) {
		if (price == null) {
			return BigDecimal.ZERO;
		}
		return collection.stream()
				.filter(e -> e.isBetterOrEqual(price))
				.map(Quote::getQty)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private static <T extends Quote<T>> BigDecimal getWorseQty(final BigDecimal price, final Collection<T> collection) {
		if (price == null) {
			return BigDecimal.ZERO;
		}
		return collection.stream()
				.filter(e -> !e.isBetterOrEqual(price))
				.map(Quote::getQty)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static class Builder {

		/** askFull */
		private List<AskQuote> askFull;

		/** bidFull */
		private List<BidQuote> bidFull;

		/**
		 * askFull設定
		 * @param askFull
		 * @return builder
		 */
		public Builder askFull(final List<AskQuote> askFull) {
			this.askFull = askFull.stream().sorted().collect(Collectors.toList());
			return this;
		}

		/**
		 * bidFull設定
		 * @param bidFull
		 * @return builder
		 */
		public Builder bidFull(final List<BidQuote> bidFull) {
			this.bidFull = bidFull.stream().sorted().collect(Collectors.toList());
			return this;
		}
	}
}
