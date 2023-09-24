package com.web.trade.view.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.web.trade.domain.order.Order;
import com.web.trade.view.order.type.CurrentOrderListType;
import com.web.trade.view.order.type.OrderHistoryType;
import com.web.trade.view.order.type.OrderListViewType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 表示用注文クラス
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderView {

	/** 注文 */
	private final Order order;

	/** 表示タイプ */
	private final OrderListViewType type;

	/**
	 * 注文照会一覧取得
	 * @param orders
	 * @return 注文照会一覧
	 */
	public static List<OrderView> getCurrentOrders(final List<Order> orders) {
		return orders.stream()
				.map(e -> new OrderView(e, CurrentOrderListType.getInstance()))
				.filter(OrderView::hasDisplayData)
				.collect(Collectors.toList());
	}

	/**
	 * 注文履歴一覧取得
	 * @param orders
	 * @return 注文履歴一覧
	 */
	public static List<OrderView> getOrderHistory(final List<Order> orders) {
		return orders.stream()
				.map(e -> new OrderView(e, OrderHistoryType.getInstance()))
				.filter(OrderView::hasDisplayData)
				.collect(Collectors.toList());
	}

	/**
	 * 注文番号取得
	 * @return 注文番号
	 */
	public String getOrderId() {
		return order.getOrderId();
	}

	/**
	 * シンボル取得
	 * @return シンボル
	 */
	public String getSymbol() {
		return String.format("%s (%s)", order.getSecurityName(), order.getSecurityCode());
	}

	/**
	 * 表示すべきデータがあるかどうか
	 * @return true: データあり / false: データなし
	 */
	public boolean hasDisplayData() {
		return type.isDisplayUnfilledOrder(order) || CollectionUtils.isEmpty(order.getExecutions());
	}

	/**
	 * 表示用詳細リスト取得
	 * @return 詳細リスト
	 */
	public List<OrderDetailView> getDetails() {
		final List<OrderDetailView> details = new ArrayList<>();
		if (type.isDisplayUnfilledOrder(order)) {
			details.add(new UnfilledOrderDetailView(order));
		}

		if (!CollectionUtils.isEmpty(order.getExecutions())) {
			final List<OrderDetailView> execDetails = order.getExecutions().stream()
					.map(exec -> new FilledOrderDetailView(order, exec))
					.collect(Collectors.toList());
			details.addAll(execDetails);
		}

		return details;
	}
}
