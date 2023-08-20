package com.web.trade.view.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.web.trade.domain.order.Execution;
import com.web.trade.domain.order.Order;
import com.web.trade.enums.HoldingType;
import com.web.trade.enums.OrdStatus;
import com.web.trade.enums.SideOfOrder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 約定注文詳細クラス
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class FilledOrderDetailView implements OrderDetailView {

	/** 注文 */
	private final Order order;

	/** 約定 */
	private final Execution execution;

	@Override
	public OrdStatus getStatus() {
		if (order.getStatus() == OrdStatus.FILLED) {
			// 全出来の場合はその旨を表示
			return OrdStatus.FILLED;
		}
		// それ以外は未約定の注文ステータスが入っているが、約定部分に関しては一部約定として表示
		return OrdStatus.PARTIALLY_FILLED;
	}

	@Override
	public SideOfOrder getSide() {
		return execution.getSide();
	}

	@Override
	public HoldingType getHoldingType() {
		return order.getHoldingType();
	}

	@Override
	public BigDecimal getPrice() {
		return execution.getPrice();
	}

	@Override
	public BigDecimal getQty() {
		return execution.getQty();
	}

	@Override
	public LocalDateTime getDateTime() {
		return execution.getExecDateTime();
	}

	@Override
	public BigDecimal getSettlementAmount() {
		return execution.getSettlementAmount();
	}

	@Override
	public LocalDate getSettlementDate() {
		return execution.getSettlementDate();
	}

}
