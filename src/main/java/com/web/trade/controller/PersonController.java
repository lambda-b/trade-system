package com.web.trade.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.trade.dto.PersonApiOut;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PersonController {

	/** ObjectMapper */
	private final ObjectMapper objectMapper;

	@GetMapping("/person")
	PersonApiOut getPerson(final HttpServletRequest request) throws IOException {
		final String path = request.getRequestURI();
		final ClassPathResource resource = new ClassPathResource("mock" + path + ".json");
		return objectMapper.readValue(resource.getFile(), PersonApiOut.class);
	}

}
