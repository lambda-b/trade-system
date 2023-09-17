package com.web.trade.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

import com.web.trade.annotation.MockJsonRestController;
import com.web.trade.dto.PersonApiOut;

import lombok.RequiredArgsConstructor;

@MockJsonRestController
@RequiredArgsConstructor
public class PersonController {

	@GetMapping("/person")
	PersonApiOut getPerson(final HttpServletRequest request) throws IOException {
		final PersonApiOut out = new PersonApiOut();
		out.setName("佐藤");
		return out;
	}
}
