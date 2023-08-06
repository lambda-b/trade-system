package com.web.trade.domain.session;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.web.trade.domain.SecurityToken;
import com.web.trade.domain.marketdata.MarketPrice;
import com.web.trade.domain.master.StMaster;
import com.web.trade.enums.TradeSessionStatus;
import com.web.trade.utils.OptionalChain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DayTradeSession
 */
@Getter
@AllArgsConstructor
public class DayTradeSession implements TradeSession {

	/** status */
	private final TradeSessionStatus status;

	@Override
	public boolean isExchangeOpen() {
		return status.isExchangeOpen();
	}

	@Override
	public boolean isOrderAcceptable() {
		return status.isOrderAcceptable();
	}

	@Override
	public BigDecimal getReferencePrice(final SecurityToken token) {
		final BigDecimal basePrice = OptionalChain.target(token)
				.chain(SecurityToken::getStMaster)
				.chain(StMaster::getBasePrice)
				.result();

		final BigDecimal amClosePrice = OptionalChain.target(token)
				.chain(SecurityToken::getMarketPrice)
				.chain(MarketPrice::getAmClosePrice)
				.result();
		final BigDecimal pmClosePrice = OptionalChain.target(token)
				.chain(SecurityToken::getMarketPrice)
				.chain(MarketPrice::getPmClosePrice)
				.result();

		final BigDecimal expectedExecPrice = OptionalChain.target(token)
				.chain(SecurityToken::getMarketPrice)
				.chain(MarketPrice::getExpectedExecPrice)
				.result();

		return List.of(expectedExecPrice, pmClosePrice, amClosePrice, basePrice).stream()
				.filter(Objects::nonNull)
				.findFirst()
				.orElse(null);
	}
}
