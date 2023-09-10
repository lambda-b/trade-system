package com.web.trade.response;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.trade.utils.UnderScore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ResponseBodyControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {

	/** ObjectMapper */
	private final ObjectMapper objectMapper;

	/** logger */
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	protected void beforeBodyWriteInternal(final MappingJacksonValue bodyContainer, final MediaType contentType,
			final MethodParameter returnType, final ServerHttpRequest request, final ServerHttpResponse response) {

		final String path = request.getURI().getPath();
		final ClassPathResource resource = new ClassPathResource("mock" + path + ".json");
		final Object data = bodyContainer.getValue();
		try {
			final Object out = objectMapper.readValue(resource.getFile(), data.getClass());
			UnderScore.merge(out, data);
			bodyContainer.setValue(out);
		} catch (final IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
}
