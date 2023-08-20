package com.web.trade.view.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.web.trade.enums.HoldingType;
import com.web.trade.enums.OrdStatus;
import com.web.trade.enums.SideOfOrder;

/**
 * 表示用注文詳細クラス
 */
public interface OrderDetailView {

	/**
	 * 表示用注文状況
	 * @return 注文状況
	 */
	OrdStatus getStatus();

	/**
	 * 売買区分取得
	 * @return 売買区分取得
	 */
	SideOfOrder getSide();

	/**
	 * 預り区分取得
	 * @return 預り区分
	 */
	HoldingType getHoldingType();

	/**
	 * 表示用単価取得
	 * @return 表示用単価
	 */
	BigDecimal getPrice();

	/**
	 * 表示用数量
	 * @return 表示用数量
	 */
	BigDecimal getQty();

	/**
	 * 表示用日時取得
	 * @return 表示用日時
	 */
	LocalDateTime getDateTime();

	/**
	 * 表示用受渡金額取得
	 * @return 表示用受渡金額
	 */
	BigDecimal getSettlementAmount();

	/**
	 * 表示用受渡日
	 * @return 表示用受渡日
	 */
	LocalDate getSettlementDate();
}
