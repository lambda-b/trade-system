package com.web.trade.domain.master;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

/**
 * TickTest
 */
public class TickTest {

	@Test
	void test_getBidTick() {
		// setup
		final Pair<BigDecimal, BigDecimal> value1 = Pair.of(new BigDecimal("1000"), new BigDecimal("5"));
		final Pair<BigDecimal, BigDecimal> value2 = Pair.of(new BigDecimal("100"), new BigDecimal("1"));
		final Pair<BigDecimal, BigDecimal> value3 = Pair.of(new BigDecimal("10000"), new BigDecimal("10"));
		final Tick tick = Tick.create(List.of(value1, value2, value3), Pair::getLeft, Pair::getRight);

		// exercise
		final BigDecimal result1 = tick.getBidTick(new BigDecimal("99"));
		final BigDecimal result2 = tick.getBidTick(new BigDecimal("100"));
		final BigDecimal result3 = tick.getBidTick(new BigDecimal("999"));
		final BigDecimal result4 = tick.getBidTick(new BigDecimal("1000"));
		final BigDecimal result5 = tick.getBidTick(new BigDecimal("9995"));
		final BigDecimal result6 = tick.getBidTick(new BigDecimal("10000"));

		// verify
		assertThat(result1).isNull();
		assertThat(result2).isEqualTo(new BigDecimal("1"));
		assertThat(result3).isEqualTo(new BigDecimal("1"));
		assertThat(result4).isEqualTo(new BigDecimal("5"));
		assertThat(result5).isEqualTo(new BigDecimal("5"));
		assertThat(result6).isEqualTo(new BigDecimal("10"));
	}

	@Test
	void test_getAskTick() {
		// setup
		final Pair<BigDecimal, BigDecimal> value1 = Pair.of(new BigDecimal("1000"), new BigDecimal("5"));
		final Pair<BigDecimal, BigDecimal> value2 = Pair.of(new BigDecimal("100"), new BigDecimal("1"));
		final Pair<BigDecimal, BigDecimal> value3 = Pair.of(new BigDecimal("10000"), new BigDecimal("10"));
		final Tick tick = Tick.create(List.of(value1, value2, value3), Pair::getLeft, Pair::getRight);

		// exercise
		final BigDecimal result1 = tick.getAskTick(new BigDecimal("99"));
		final BigDecimal result2 = tick.getAskTick(new BigDecimal("100"));
		final BigDecimal result3 = tick.getAskTick(new BigDecimal("998"));
		final BigDecimal result4 = tick.getAskTick(new BigDecimal("999"));
		final BigDecimal result5 = tick.getAskTick(new BigDecimal("9990"));
		final BigDecimal result6 = tick.getAskTick(new BigDecimal("9995"));

		// verify
		assertThat(result1).isNull();
		assertThat(result2).isEqualTo(new BigDecimal("1"));
		assertThat(result3).isEqualTo(new BigDecimal("1"));
		assertThat(result4).isEqualTo(new BigDecimal("5"));
		assertThat(result5).isEqualTo(new BigDecimal("5"));
		assertThat(result6).isEqualTo(new BigDecimal("10"));
	}
}
