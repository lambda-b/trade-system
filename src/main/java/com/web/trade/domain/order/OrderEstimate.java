package com.web.trade.domain.order;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

/**
 * 注文見積もり
 */
@Getter
@Builder
public class OrderEstimate {

	/** 概算約定代金 */
	private final BigDecimal execAmount;

	/** 概算受渡金額 */
	private final BigDecimal settlementAmount;

	/** 受渡予定日 */
	private final LocalDate settlementDate;

	/** 概算手数料 */
	private final BigDecimal commission;
}
