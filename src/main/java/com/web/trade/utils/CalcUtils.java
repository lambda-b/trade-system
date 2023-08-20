package com.web.trade.utils;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CalcUtils {

	/**
	 * 足し算
	 * @param value1
	 * @param value2
	 * @return 和
	 */
	public static BigDecimal add(final BigDecimal value1, final BigDecimal value2) {
		final BigDecimal calcValue1 = value1 != null ? value1 : BigDecimal.ZERO;
		final BigDecimal calcValue2 = value2 != null ? value2 : BigDecimal.ZERO;
		return calcValue1.add(calcValue2);
	}

	/**
	 * 引き算
	 * @param value1
	 * @param value2
	 * @return 差
	 */
	public static BigDecimal subtract(final BigDecimal value1, final BigDecimal value2) {
		final BigDecimal calcValue1 = value1 != null ? value1 : BigDecimal.ZERO;
		final BigDecimal calcValue2 = value2 != null ? value2 : BigDecimal.ZERO;
		return calcValue1.subtract(calcValue2);
	}

	/**
	 * Nullまたはゼロ確認
	 * @param value
	 * @return true: Null or 0 / false: 左以外
	 */
	public static boolean isNullOrZero(final BigDecimal value) {
		return value == null || value.compareTo(BigDecimal.ZERO) == 0;
	}
}
