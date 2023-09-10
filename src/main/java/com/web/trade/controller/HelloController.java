package com.web.trade.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
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

	@GetMapping("/hello")
	HelloApiOut getHello(final HttpServletRequest request) throws IOException {
		final String path = request.getRequestURI();
		final ClassPathResource resource = new ClassPathResource("mock" + path + ".json");
		final HelloApiOut out = objectMapper.readValue(resource.getFile(), HelloApiOut.class);
		return out;
	}
}
