package com.web.trade.domain;

import java.math.BigDecimal;

import com.web.trade.domain.marketdata.MarketPrice;
import com.web.trade.domain.master.StMaster;
import com.web.trade.domain.session.TradeSession;

import lombok.Builder;
import lombok.Getter;

/**
 * セキュリティトークン
 */
@Builder
public class SecurityToken {

	/** 銘柄マスタ */
	@Getter
	private final StMaster stMaster;

	/** 市場価格 */
	@Getter
	private final MarketPrice marketPrice;

	/** session */
	private final TradeSession session;

	public BigDecimal getReferencePrice() {
		return session.getReferencePrice(this);
	}
}
