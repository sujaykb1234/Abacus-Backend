package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.WhatsAppService;

@RestController
@RequestMapping("abacus/v1/whatsapp")
public class WhatsAppController {

	@Autowired
	private WhatsAppService whatsAppService;

	@PostMapping("/sendMessage")
	public SuccessResponse sendWhatsAppMessages(@RequestParam String mobile, @RequestParam String message) {
		return whatsAppService.sendWhatsAppMessages(mobile, message);
	}

}
