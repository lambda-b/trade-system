package com.web.trade.view.order.type;

import com.web.trade.domain.order.Order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * OrderHistory
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderHistoryType implements OrderListViewType {

	/** インスタンス */
	private static final OrderListViewType INSTANCE = new OrderHistoryType();

	/**
	 * インスタンス取得
	 * @return インスタンス
	 */
	public static OrderListViewType getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isDisplayUnfilledOrder(final Order order) {
		if (order.isFullyFilled()) {
			return false;
		}

		switch (order.getStatus()) {
		case NEW:
		case PENDING_NEW:
		case PENDING_CANCEL:
		case REJECT:
			return false;
		default:
			return true;
		}
	}

}
