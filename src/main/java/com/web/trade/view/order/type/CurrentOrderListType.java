package com.web.trade.view.order.type;

import com.web.trade.domain.order.Order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * CurrentOrderListType
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrentOrderListType implements OrderListViewType {

	/** インスタンス */
	private static final OrderListViewType INSTANCE = new CurrentOrderListType();

	/**
	 * インスタンス取得
	 * @return インスタンス
	 */
	public static OrderListViewType getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isDisplayUnfilledOrder(final Order order) {
		return !order.isFullyFilled();
	}
}
