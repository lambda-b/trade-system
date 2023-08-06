package com.web.trade.domain.session;

import java.math.BigDecimal;

import com.web.trade.domain.Security;
import com.web.trade.domain.master.SecurityMaster;
import com.web.trade.enums.TradeSessionStatus;
import com.web.trade.utils.OptionalChain;

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
	public BigDecimal getReferencePrice(final Security security) {
		return OptionalChain.target(security)
				.chain(Security::getMaster)
				.chain(SecurityMaster::getBasePrice)
				.result();
	}
}
