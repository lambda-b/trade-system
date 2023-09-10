package com.web.trade.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonApiOut implements Serializable {

	/** 名前 */
	private String name;

	/** 年齢 */
	private int age;

	/** 勤め先 */
	private CorporationDto workplace;

	/** 友達 */
	private List<String> friends;
}
