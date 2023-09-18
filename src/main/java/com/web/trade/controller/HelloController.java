package com.web.trade.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.trade.dto.HelloApiOut;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {

	/** ObjectMapper */
	private final ObjectMapper objectMapper;

	/** ResourceLoader */
	private final ResourceLoader loader;

	@GetMapping("/hello")
	HelloApiOut getHello(final HttpServletRequest request) throws IOException {
		final String path = request.getRequestURI();
		final Resource resource = loader.getResource("classpath:mock" + path + ".json");

		try (InputStream stream = resource.getURL().openStream()) {
			final HelloApiOut out = objectMapper.readValue(stream, HelloApiOut.class);
			return out;
		}
	}
}
