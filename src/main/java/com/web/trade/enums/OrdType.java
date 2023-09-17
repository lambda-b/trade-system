package com.web.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注文種別
 */
@Getter
@AllArgsConstructor
public enum OrdType {

	/** 成行 */
	MARKET("成行"),

	/** 指値 */
	LIMIT("指値"),
	;

	/** ラベル */
	private final String label;
}
