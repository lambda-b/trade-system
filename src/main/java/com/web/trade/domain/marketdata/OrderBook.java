package com.web.trade.domain.marketdata;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.web.trade.domain.marketdata.quote.AskQuote;
import com.web.trade.domain.marketdata.quote.BidQuote;
import com.web.trade.domain.marketdata.quote.Quote;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder")
public class OrderBook {

	/** 予想約定価格 */
	private final BigDecimal expectedExecPrice;

	/** フル板情報(売) - Builder処理でソートされているものとして取り扱う */
	private final List<AskQuote> askQuotes;

	/** フル板情報(買) - Builder処理でソートされているものとして取り扱う */
	private final List<BidQuote> bidQuotes;

	/** 呼値 */
	private final BigDecimal tick;

	/** 表示件数 */
	private final static int DISPLAY_NUMBER = 10;

	public boolean isMatching() {
		return expectedExecPrice != null;
	}

	public BigDecimal getMarketAskQty() {
		return getBetterQty(askQuotes, Quote.MARKET_PRICE);
	}

	public BigDecimal getMarketBidQty() {
		return getBetterQty(bidQuotes, Quote.MARKET_PRICE);
	}

	public BigDecimal getBestAskPrice() {
		if (!isMatching()) {
			return getFirstPrice(askQuotes);
		}
		final BigDecimal baseAskQty = getBetterQty(askQuotes, expectedExecPrice);
		final BigDecimal baseBidQty = getBetterQty(bidQuotes, expectedExecPrice);
		if (baseAskQty.compareTo(baseBidQty) < 0) {
			return expectedExecPrice.add(tick);
		}
		return expectedExecPrice;
	}

	public BigDecimal getBestBidPrice() {
		if (!isMatching()) {
			return getFirstPrice(bidQuotes);
		}
		final BigDecimal baseAskQty = getBetterQty(askQuotes, expectedExecPrice);
		final BigDecimal baseBidQty = getBetterQty(bidQuotes, expectedExecPrice);
		if (baseAskQty.compareTo(baseBidQty) > 0) {
			return expectedExecPrice.subtract(tick);
		}
		return expectedExecPrice;
	}

	public List<AskQuote> getAskBoard() {
		final BigDecimal bestPrice = getBestAskPrice();
		if (bestPrice == null) {
			return Collections.emptyList();
		}
		final BigDecimal bestQty = getBetterQty(askQuotes, bestPrice);
		return getDisplay(askQuotes, new AskQuote(bestPrice, bestQty));
	}

	public List<BidQuote> getBidBoard() {
		final BigDecimal bestPrice = getBestBidPrice();
		if (bestPrice == null) {
			return Collections.emptyList();
		}
		final BigDecimal bestQty = getBetterQty(bidQuotes, bestPrice);
		return getDisplay(bidQuotes, new BidQuote(bestPrice, bestQty));
	}

	public BigDecimal getOverAskQty() {
		final List<AskQuote> askBoard = getAskBoard();
		final BigDecimal sumQty = getSumQty(askQuotes);
		final BigDecimal displayQty = getSumQty(askBoard);
		return sumQty.subtract(displayQty);
	}

	public BigDecimal getUnderBidQty() {
		final List<BidQuote> bidBoard = getBidBoard();
		final BigDecimal sumQty = getSumQty(bidQuotes);
		final BigDecimal displayQty = getSumQty(bidBoard);
		return sumQty.subtract(displayQty);
	}

	private static <T extends Quote> BigDecimal getBetterQty(final Collection<T> quotes, final BigDecimal price) {

		return quotes.stream()
				.filter(e -> e.isBetterOrEqual(price))
				.map(Quote::getQty)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private static <T extends Quote> BigDecimal getSumQty(final Collection<T> quotes) {
		return quotes.stream()
				.map(Quote::getQty)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private static <T extends Quote> List<T> getDisplay(final Collection<T> quotes, final T bestQuote) {
		return Stream.concat(Stream.of(bestQuote), quotes.stream()
				.filter(e -> !e.isBetterOrEqual(bestQuote.getPrice())))
				.limit(DISPLAY_NUMBER)
				.collect(Collectors.toList());
	}

	private static <T extends Quote> BigDecimal getFirstPrice(final Collection<T> quotes) {
		return quotes.stream()
				.findFirst()
				.map(Quote::getPrice)
				.orElse(null);
	}

	public static class Builder {

		/** askFull */
		private List<AskQuote> askQuotes;

		/** bidFull */
		private List<BidQuote> bidQuotes;

		/**
		 * askFull設定
		 * @param askFull
		 * @return builder
		 */
		public Builder askQuotes(final List<AskQuote> askFull) {
			this.askQuotes = askFull.stream().sorted().collect(Collectors.toList());
			return this;
		}

		/**
		 * bidFull設定
		 * @param bidFull
		 * @return builder
		 */
		public Builder bidQuotes(final List<BidQuote> bidFull) {
			this.bidQuotes = bidFull.stream().sorted().collect(Collectors.toList());
			return this;
		}
	}
}
