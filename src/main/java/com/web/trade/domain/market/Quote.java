package com.web.trade.domain.market;

import java.math.BigDecimal;

/**
 * 気配
 */
public interface Quote {

	/**
	 * 成行価格を表すオブジェクト<br>
	 * null が成行を表すものとする
	 */
	BigDecimal MARKET_PRICE = null;

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
