package com.web.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注文状態
 */
@Getter
@AllArgsConstructor
public enum OrdStatus {

	/** 受付中 */
	NEW("受付中"),

	/** 発注中 */
	PENDING_NEW("発注中"),

	/** 約定(一部) */
	PARTIALLY_FILLED("約定(一部)"),

	/** 約定(全出来) */
	FILLED("約定"),

	/** 取消中 */
	PENDING_CANCEL("取消中"),

	/** 失効 */
	EXPIRED("失効"),

	/** 受付拒否 */
	REJECT("受付拒否"),
	;

	/** ラベル */
	private final String label;
}
