package com.learning.photoapp.api.accounts.ui.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountsController {

	@RequestMapping("/status/check")
	public String status()
	{
		return "working";
	}
}
