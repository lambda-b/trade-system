package com.web.trade.domain;

import com.web.trade.domain.marketdata.MarketPrice;
import com.web.trade.domain.master.StMaster;

import lombok.Builder;
import lombok.Getter;

/**
 * セキュリティトークン
 */
@Getter
@Builder
public class SecurityToken {

	/** 銘柄マスタ */
	private final StMaster stMaster;

	/** 市場価格 */
	private final MarketPrice marketPrice;
}
