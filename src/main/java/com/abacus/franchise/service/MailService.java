package com.abacus.franchise.service;

import org.springframework.stereotype.Service;

import com.abacus.franchise.model.Mail;


@Service
public interface MailService {
	
	public void sendEmail(Mail mail);


}
