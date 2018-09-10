package com.taj.model;

import org.springframework.lang.NonNull;

import javax.validation.constraints.Size;

/**
 * Created by User on 9/10/2018.
 */
public class RecievesNewsMailsModel {

    private int mailId;
    @NonNull
    @Size(max = 450, min = 8, message="email should have at least 8 characters")
    private String mail;

    public RecievesNewsMailsModel(int mailId, @Size(max = 450, min = 8, message = "email should have at least 8 characters") String mail) {
        this.mailId = mailId;
        this.mail = mail;
    }

    public RecievesNewsMailsModel() {
    }

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
