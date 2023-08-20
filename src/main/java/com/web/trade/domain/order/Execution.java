package com.web.trade.domain.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.web.trade.enums.SideOfOrder;

import lombok.Builder;
import lombok.Getter;

/**
 * 約定
 */
@Getter
@Builder
public class Execution {

	/** 約定ID */
	private final String execId;

	/** 注文ID */
	private final String orderId;

	/** 売買区分 */
	private final SideOfOrder side;

	/** 約定単価 */
	private final BigDecimal price;

	/** 約定数量 */
	private final BigDecimal qty;

	/** 約定日時 */
	private final LocalDateTime execDateTime;

	/** 受渡金額 */
	private final BigDecimal settlementAmount;

	/** 受渡日 */
	private final LocalDate settlementDate;
}
