package com.web.trade.utils;

import java.lang.reflect.Method;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 名前が思いつかなかったので、javascriptの_を真似してみた
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UnderScore {

	/**
	 * mergeメソッド
	 * @param obj
	 * @param update
	 */
	public static void merge(final Object obj, final Object update) {
		if (!obj.getClass().isAssignableFrom(update.getClass())) {
			return;
		}

		final Method[] methods = obj.getClass().getMethods();

		for (final Method fromMethod : methods) {
			if (fromMethod.getDeclaringClass().equals(obj.getClass())
					&& fromMethod.getName().startsWith("get")) {

				final String fromName = fromMethod.getName();
				final String toName = fromName.replace("get", "set");

				try {
					final Method toMetod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
					final Object value = fromMethod.invoke(update, (Object[]) null);
					if (value != null) {
						toMetod.invoke(obj, value);
					}
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
