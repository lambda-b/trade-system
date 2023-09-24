package com.web.trade.view.order.type;

import com.web.trade.domain.order.Order;

/**
 * 注文一覧表示タイプ
 */
public interface OrderListViewType {

	/**
	 * 未約定部分の表示有無
	 * @param order
	 * @return true: 表示 / false: 非表示
	 */
	boolean isDisplayUnfilledOrder(final Order order);
}
