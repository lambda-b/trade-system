package com.web.trade.view.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.web.trade.domain.order.Order;
import com.web.trade.enums.HoldingType;
import com.web.trade.enums.OrdStatus;
import com.web.trade.enums.SideOfOrder;
import com.web.trade.utils.CalcUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 未約定の表示に関するクラス<br>
 * 部分約定しているケースの未約定部分についても本クラスで取扱う
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class UnfilledOrderDetailView implements OrderDetailView {

	/** 注文 */
	private final Order order;

	@Override
	public OrdStatus getStatus() {
		if (order.getStatus() != OrdStatus.PARTIALLY_FILLED) {
			// 最優先ステータスが一部約定でないとき
			return order.getStatus();
		}

		if (CalcUtils.isNullOrZero(order.getExpiredQty())) {
			// 約定以外のものについて失効されていない => 注文は取消中
			return OrdStatus.PENDING_CANCEL;
		}

		return OrdStatus.EXPIRED;
	}

	@Override
	public SideOfOrder getSide() {
		return order.getSide();
	}

	@Override
	public HoldingType getHoldingType() {
		return order.getHoldingType();
	}

	@Override
	public BigDecimal getPrice() {
		return order.getLimitPrice();
	}

	@Override
	public BigDecimal getQty() {
		return order.getUnfilledQty();
	}

	@Override
	public LocalDateTime getDateTime() {
		return order.getReceiptDateTime();
	}

	@Override
	public BigDecimal getSettlementAmount() {
		final OrdStatus status = this.getStatus();
		if (status == OrdStatus.NEW || status == OrdStatus.PENDING_NEW) {
			// 概算受渡金額を返す
			return order.getEstimateSettlementAmount();
		}
		return null;
	}

	@Override
	public LocalDate getSettlementDate() {
		final OrdStatus status = this.getStatus();
		if (status == OrdStatus.NEW || status == OrdStatus.PENDING_NEW) {
			// 受渡予定日を返す
			return order.getEstimateSettlementDate();
		}
		return null;
	}
}
