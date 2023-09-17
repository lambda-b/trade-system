package com.web.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 売買区分
 */
@Getter
@AllArgsConstructor
public enum SideOfOrder {

	/** 買付 */
	BUY("買付"),

	/** 売却 */
	SELL("売却"),
	;

	/** ラベル */
	private final String label;
}
