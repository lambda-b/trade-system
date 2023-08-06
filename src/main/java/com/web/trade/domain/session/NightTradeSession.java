package com.web.trade.domain.session;

import java.math.BigDecimal;

import com.web.trade.domain.SecurityToken;
import com.web.trade.enums.TradeSessionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * NightTradeSession
 */
@Getter
@AllArgsConstructor
public class NightTradeSession implements TradeSession {

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
		return token.getStMaster().getBasePrice();
	}
}
