package com.abacus.franchise.model;

import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mailFrom;
    private String mailTo;
    @Setter
    private String mailCc;
    @Setter
    private String mailBcc;
    @Setter
    private String mailSubject;
    @Setter
    private String mailContent;
    private String contentType = "text/plain";
    private List<Object> attachments = new ArrayList<>();

    // ===== Constructors =====

    public Mail() {
        // default constructor
    }

    public Mail(String mailFrom,
                String mailTo,
                String mailCc,
                String mailBcc,
                String mailSubject,
                String mailContent,
                String contentType,
                List<Object> attachments) {

        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.mailCc = mailCc;
        this.mailBcc = mailBcc;
        this.mailSubject = mailSubject;
        this.mailContent = mailContent;
        this.contentType = contentType != null ? contentType : "text/plain";
        this.attachments = attachments != null ? attachments : new ArrayList<>();
    }

    // ===== Getters & Setters =====

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailCc() {
        return mailCc;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public String getMailContent() {
        return mailContent;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType != null ? contentType : "text/plain";
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments != null ? attachments : new ArrayList<>();
    }
}
