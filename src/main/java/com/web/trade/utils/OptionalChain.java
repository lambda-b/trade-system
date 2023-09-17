package com.web.trade.utils;

import java.util.function.Function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;

/**
 * getter処理をnull-safeにするための utilクラス
 */
@UtilityClass
public final class OptionalChain {

	/**
	 * target
	 * @param <T> 型パラメータ
	 * @param param パラメータ
	 * @return chain
	 */
	public static <T> Chain<T> target(final T param) {
		return new Chain<T>(param);
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Chain<T> {

		/** 内部オブジェクト */
		@Accessors(fluent = true)
		private final T result;

		/**
		 * null安全なgetter
		 * @param <U> 型パラメータ
		 * @param getter
		 * @return 新規のchain
		 */
		public <U> Chain<U> chain(final Function<T, U> getter) {
			final U object = result == null ? null : getter.apply(result);
			return new Chain<U>(object);
		}
	}
}
