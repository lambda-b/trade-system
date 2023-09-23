package com.web.trade.domain.master;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 呼値クラス
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tick {

	/** 呼値テーブル */
	private final List<TickData> table;

	/**
	 * 呼値取得
	 * @param price
	 * @return 呼値
	 */
	public BigDecimal get(final BigDecimal price) {
		return table.stream()
				.filter(e -> e.start.compareTo(price) <= 0)
				.max(TickData::compareTo)
				.map(e -> e.value)
				.orElse(null);
	}

	/**
	 * 呼値オブジェクト作成
	 * @param <T> 型パラメータ
	 * @param source
	 * @param getStart
	 * @param getValue
	 * @return 呼値
	 */
	public static <T> Tick create(final List<T> source, final Function<T, BigDecimal> getStart,
			final Function<T, BigDecimal> getValue) {
		final List<TickData> list = source.stream()
				.map(e -> new TickData(getStart.apply(e), getValue.apply(e)))
				.collect(Collectors.toList());

		return new Tick(list);
	}

	/**
	 * 呼値の値格納クラス<br>
	 * 外部公開しないためprivateクラス
	 */
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	private static class TickData implements Comparable<TickData> {

		/** 呼値開始値 */
		private final BigDecimal start;

		/** ティックサイズ */
		private final BigDecimal value;

		@Override
		public int compareTo(final TickData other) {
			return this.start.compareTo(other.start);
		}
	}
}
