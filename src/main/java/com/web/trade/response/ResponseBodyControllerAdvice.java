package com.web.trade.response;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.trade.annotation.MockJsonRestController;
import com.web.trade.utils.UnderScore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice(annotations = { MockJsonRestController.class })
public class ResponseBodyControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {

	/** ObjectMapper */
	private final ObjectMapper objectMapper;

	/** ResourceLoader */
	private final ResourceLoader loader;

	/** logger */
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	protected void beforeBodyWriteInternal(final MappingJacksonValue bodyContainer, final MediaType contentType,
			final MethodParameter returnType, final ServerHttpRequest request, final ServerHttpResponse response) {

		final String path = request.getURI().getPath();
		final Resource resource = loader.getResource("classpath:mock" + path + ".json");
		final Object data = bodyContainer.getValue();

		try (InputStream stream = resource.getURL().openStream()) {
			final Object out = objectMapper.readValue(stream, data.getClass());
			UnderScore.merge(out, data);
			bodyContainer.setValue(out);
		} catch (final IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
}
