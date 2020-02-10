package com.ohadr.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthProperties
{
	@Value("${com.ohadr.oauth2.token.issuer}")
	private String tokenIssuer;

	@Value("${com.ohadr.oauth2.token.timeToLive}")
	private int tokenTimeToLive;

	@Value("${com.ohadr.oauth2.token.refreshTimeToLive}")
	private int refreshTokenTimeToLive;

	@Value("${com.ohadr.oauth2.token.cryptoEnabled}")
	private boolean cryptoEnabled;
	
	public String getTokenIssuer()
	{
		return tokenIssuer;
	}

	public int getTokenTimeToLive()
	{
		return tokenTimeToLive;
	}

	public int getRefreshTokenTimeToLive()
	{
		return refreshTokenTimeToLive;
	}

	public boolean isCryptoEnabled()
	{
		return cryptoEnabled;
	}

}
