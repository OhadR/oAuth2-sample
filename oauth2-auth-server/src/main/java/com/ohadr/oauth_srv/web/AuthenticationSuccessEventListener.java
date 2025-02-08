package com.ohadr.oauth_srv.web;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationSuccessEventListener implements
		ApplicationListener<AuthenticationSuccessEvent>
{
	Logger log = LogManager.getLogger(getClass());

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent ev)
	{
	    String username = ev.getAuthentication().getName();
	    log.info("login success for user: " + username);

	    //Do Something with the knowledge the login was successfull...
	}

}
