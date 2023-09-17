package com.web.trade.domain.marketdata;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

/**
 * 市場価格
 */
@Getter
@Builder
public class MarketPrice {

	/** 前場終値 */
	private final BigDecimal amClosePrice;

	/** 後場終値 */
	private final BigDecimal pmClosePrice;

	/** 予想約定価格 */
	private final BigDecimal expectedExecPrice;
}
