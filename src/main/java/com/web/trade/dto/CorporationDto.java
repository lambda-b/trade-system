package com.web.trade.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorporationDto implements Serializable {

	/** 企業名 */
	private String name;

	/** 所在地 */
	private String address;

	/** 電話番号 */
	private String phoneNumber;

	/** 資本金 */
	private BigDecimal capital;
}
