package com.web.trade.domain.session;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.web.trade.domain.SecurityToken;
import com.web.trade.enums.TradeSessionStatus;

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
		final BigDecimal basePrice = token.getStMaster().getBasePrice();
		final BigDecimal amClosePrice = token.getMarketPrice().getAmClosePrice();
		final BigDecimal pmClosePrice = token.getMarketPrice().getPmClosePrice();
		final BigDecimal expectedExecPrice = token.getMarketPrice().getExpectedExecPrice();
		return List.of(expectedExecPrice, pmClosePrice, amClosePrice, basePrice).stream()
				.filter(Objects::nonNull)
				.findFirst()
				.orElse(null);
	}
}
