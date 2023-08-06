package com.web.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 取引セッション状態
 */
@Getter
@AllArgsConstructor
public enum TradeSessionStatus {

	/** 前場開始 */
	AM_OPENING_AUCTION(false, true),

	/** 前場中 */
	AM_REGULAR_AUCTION(true, true),

	/** 前場終了 */
	AM_CLOSING_AUCTION(false, true),

	/** 場間 */
	LUNCH_TIME(false, true),

	/** 後場開始 */
	PM_OPENING_AUCTION(false, true),

	/** 後場中 */
	PM_REGULAR_AUCTION(true, true),

	/** 後場終了 */
	PM_CLOSING_AUCTION(false, true),

	/** 受付不能時間 */
	MAINTENANCE_TIME(false, false),

	/** 夜間 */
	NIGHT(false, true),
	;

	/** 市場状態 */
	private boolean exchangeOpen;

	/** 注文受付可否 */
	private boolean orderAcceptable;

}
