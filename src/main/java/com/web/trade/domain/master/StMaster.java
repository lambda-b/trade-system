package com.web.trade.domain.master;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

/**
 * 銘柄マスタ
 */
@Getter
@Builder
public class StMaster {

	/** 銘柄コード */
	private final String code;

	/** 銘柄名 */
	private final String name;

	/** 基準値 */
	private final BigDecimal basePrice;
}
