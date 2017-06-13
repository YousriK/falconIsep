package com.falcon.avisep.configuration;

import javax.servlet.http.HttpServletResponse;

public class ClientSMSError {
    private Integer idSMS;
    private String sms;

    public ClientSMSError(HttpServletResponse res, Exception e) {
        this.idSMS = res.getStatus();
        this.sms = e.getMessage();
    }

	public Integer getIdSMS() {
		return idSMS;
	}

	public void setIdSMS(Integer idSMS) {
		this.idSMS = idSMS;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

    
}
