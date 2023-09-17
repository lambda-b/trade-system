package com.web.trade.view.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.web.trade.domain.order.Order;

import lombok.AllArgsConstructor;

/**
 * 表示用注文クラス
 */
@AllArgsConstructor
public class OrderView {

	/** 注文 */
	private final Order order;

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
	 * 表示用詳細リスト取得
	 * @return 詳細リスト
	 */
	public List<OrderDetailView> getDetails() {
		final List<OrderDetailView> details = new ArrayList<>();
		if (!order.isFullyFilled()) {
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
