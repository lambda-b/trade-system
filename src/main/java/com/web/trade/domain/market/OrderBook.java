package com.web.trade.domain.market;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class OrderBook {

	/** 予想約定価格 */
	private final BigDecimal expectedExecutionPrice;

	/** 参考価格　*/
	private final BigDecimal referencePrice;

	/** フル板情報(売) */
	private final Map<BigDecimal, AskQuote> askFull;

	/** フル板情報(買) */
	private final Map<BigDecimal, BidQuote> bidFull;

	/** 成行売気配 */
	private final AskQuote askMarketQuote;

	/** 成行買気配 */
	private final BidQuote bidMarketQuote;

	/** 呼値 */
	private final BigDecimal tick;

	/**
	 * コンストラクタ
	 * @param expectedExcutionPrice
	 * @param referencePrice
	 * @param askFull
	 * @param bidFull
	 */
	public OrderBook(final BigDecimal expectedExecutionPrice, final BigDecimal referencePrice,
			final List<AskQuote> askFull, final List<BidQuote> bidFull, final BigDecimal tick) {
		this.expectedExecutionPrice = expectedExecutionPrice;
		this.referencePrice = referencePrice;
		this.askFull = askFull.stream().collect(Collectors.toMap(Quote::getPrice, UnaryOperator.identity()));
		this.bidFull = bidFull.stream().collect(Collectors.toMap(Quote::getPrice, UnaryOperator.identity()));
		this.askMarketQuote = getMarketQuote(askFull, AskQuote::new);
		this.bidMarketQuote = getMarketQuote(bidFull, BidQuote::new);
		this.tick = tick;
	}

	/**
	 * 成行気配値を取得
	 * @param <T> 型パラメータ
	 * @param full
	 * @param constructor
	 * @return 成行気配値
	 */
	private static <T extends Quote> T getMarketQuote(final List<T> full,
			final BiFunction<BigDecimal, BigDecimal, T> constructor) {
		return full.stream()
				.filter(e -> e.getPrice() == null)
				.findFirst()
				.orElse(constructor.apply(null, BigDecimal.ZERO));
	}

	public BigDecimal getBasePrice() {
		return expectedExecutionPrice != null ? expectedExecutionPrice : referencePrice;
	}

	public BigDecimal getBaseAskQty() {
		final BigDecimal basePrice = getBasePrice();
		return getBetterQty(basePrice, askFull.values());
	}

	public BigDecimal getBaseBidQty() {
		final BigDecimal basePrice = getBasePrice();
		return getBetterQty(basePrice, bidFull.values());
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
		return getBetterQty(bestPrice, askFull.values());
	}

	public BigDecimal getBestBidQty() {
		final BigDecimal bestPrice = getBestAskPrice();
		return getBetterQty(bestPrice, bidFull.values());
	}

	private static <T extends Quote> BigDecimal getBetterQty(final BigDecimal price, final Collection<T> collection) {
		return collection.stream()
				.filter(e -> e.isBetterOrEqual(price))
				.map(Quote::getQty)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
