package br.mesttra.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

	
	@GetMapping("/")
	public String ola() {
		return "Olá mundo!";
	}
	
}
