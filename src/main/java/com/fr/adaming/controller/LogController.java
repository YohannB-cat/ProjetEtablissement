package com.fr.adaming.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
//@Log4j2
@Slf4j
public class LogController {

	@GetMapping(path = "/log/test")
	public void test() {

		log.trace("");
		log.debug("");
		log.info("");
		log.warn("");
		log.error("");
	}

}
