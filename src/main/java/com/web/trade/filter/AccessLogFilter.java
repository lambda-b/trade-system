package com.web.trade.filter;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AccessLogFilter extends OncePerRequestFilter {

	/** logger */
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain)
			throws ServletException, IOException {

		MDC.put("uuid", UUID.randomUUID().toString());
		logger.info("ACCESS START. uuid={}", MDC.get("uuid"));

		try {
			filterChain.doFilter(request, response);
		} catch (final Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			logger.info("ACCESS END. uuid={}", MDC.get("uuid"));
		}

	}

}
