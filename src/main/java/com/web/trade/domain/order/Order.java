package com.web.trade.domain.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.web.trade.enums.HoldingType;
import com.web.trade.enums.OrdStatus;
import com.web.trade.enums.OrdType;
import com.web.trade.enums.SideOfOrder;
import com.web.trade.utils.CalcUtils;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * 注文
 */
@Getter
@Builder
public class Order {

	/** 注文番号 */
	private final String orderId;

	/** 銘柄コード */
	private final String securityCode;

	/** 銘柄名 */
	private final String securityName;

	/** 預り区分 */
	private final HoldingType holdingType;

	/** 売買区分 */
	private final SideOfOrder side;

	/** 注文種別 */
	private final OrdType orderType;

	/** 注文状態 */
	private final OrdStatus status;

	/** 注文(指値)価格 */
	private final BigDecimal limitPrice;

	/** 注文数量 */
	private final BigDecimal qty;

	/** 注文日時 */
	private final LocalDateTime receiptDateTime;

	/** 約定数量 */
	private final BigDecimal execQty;

	/** 失効数量 */
	private final BigDecimal expiredQty;

	/** 約定リスト */
	private final List<Execution> executions;

	/** 概算値 */
	@Getter(AccessLevel.NONE)
	private final OrderEstimate estimate;

	/**
	 * 未約定の数量を取得
	 * @return 未約定数量
	 */
	public BigDecimal getUnfilledQty() {
		return CalcUtils.subtract(qty, execQty);
	}

	/**
	 * 注文がすべて約定済かどうかの判定<br>
	 * 注文数量と約定数量の比較により判定する
	 * @return true: すべて約定済み / false: 未約定を含む
	 */
	public boolean isFullyFilled() {
		return CalcUtils.isNullOrZero(getUnfilledQty());
	}

	/**
	 * 概算約定代金取得
	 * @return 概算約定代金
	 */
	public BigDecimal getEstimateExecAmount() {
		if (estimate == null) {
			return null;
		}
		return estimate.getExecAmount();
	}

	/**
	 * 概算受渡金額取得
	 * @return 概算受渡金額
	 */
	public BigDecimal getEstimateSettlementAmount() {
		if (estimate == null) {
			return null;
		}
		return estimate.getSettlementAmount();
	}

	/**
	 * 受渡予定日取得
	 * @return 受渡予定日
	 */
	public LocalDate getEstimateSettlementDate() {
		if (estimate == null) {
			return null;
		}
		return estimate.getSettlementDate();
	}

	/**
	 * 概算手数料取得
	 * @return 概算手数料
	 */
	public BigDecimal getEstimateCommission() {
		if (estimate == null) {
			return null;
		}
		return estimate.getCommission();
	}
}
