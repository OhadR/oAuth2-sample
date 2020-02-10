package com.ohadr.security.oauth.examples.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestOperations;

import com.ohadr.security.oauth.examples.DemoService;

import java.net.URI;


public class DemoServiceImpl implements DemoService
{
	private static Logger log = Logger.getLogger(DemoServiceImpl.class);

	@Autowired
	private RestOperations butkeDemoRestTemplate;

    private String demoUrl;


    public void setDemoUrl(String demoUrl)
    {
        this.demoUrl = demoUrl;
    }

    @Override
    public String getTrustedMessage()
    {
    	log.info("sending GET request to " + demoUrl);
        String demo = butkeDemoRestTemplate.getForObject(URI.create(demoUrl), String.class);
        return demo;
    }
}
