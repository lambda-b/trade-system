package com.web.trade.domain.market;

import java.math.BigDecimal;

/**
 * 気配
 */
public interface Quote {

	/**
	 * 価格取得
	 * @return price
	 */
	BigDecimal getPrice();

	/**
	 * 数量取得
	 * @return qty
	 */
	BigDecimal getQty();

	/**
	 * より良い気配か
	 * @return better
	 */
	boolean isBetterOrEqual(BigDecimal price);

}
