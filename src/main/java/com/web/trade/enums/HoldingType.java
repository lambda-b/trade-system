package com.web.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 預り区分
 */
@Getter
@AllArgsConstructor
public enum HoldingType {

	/** 一般 */
	GENERAL("一般"),

	/** 特定 */
	SPECIFIC("特定"),
	;

	/** ラベル */
	private final String label;
}
