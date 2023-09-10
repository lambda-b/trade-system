package com.web.trade.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloApiOut implements Serializable {

	/** 国 */
	private String country;

	/** 挨拶 */
	private String greeting;
}
