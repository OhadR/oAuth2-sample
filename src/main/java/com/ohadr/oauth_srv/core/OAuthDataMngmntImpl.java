package com.ohadr.oauth_srv.core;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ohadr.authentication.utils.oAuthConstants;
import com.ohadr.oauth_srv.interfaces.OAuthDataManagement;
import com.ohadr.oauth_srv.interfaces.OAuthRepository;
import com.ohadr.oauth_srv.types.AuthenticationPolicy;
import com.ohadr.oauth_srv.types.OAuthUser;
import com.ohadr.oauth_srv.types.OauthAccountState;

@Component
public class OAuthDataMngmntImpl implements OAuthDataManagement 
{
	private static Logger log = Logger.getLogger(OAuthDataMngmntImpl.class);
	
	private OAuthRepository oAuthRepository;

	@Override
	public boolean setLoginSuccessForUser(String username) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pair<String, String> createAccount(
			String email,
			String encodedPassword, 
			String secretQuestion,
			String encodedAnswer,
			String redirectUri)
	{
		try
		{
			OAuthUser oauthUser = oAuthRepository.getUser( email );
			
			//if user exist, but not activated - we allow re-registration:
			if(oauthUser != null && !oauthUser.getEnabled())
			{
				oAuthRepository.deleteOAuthAccount( email );
			}

			oAuthRepository.createAccount(email, encodedPassword, secretQuestion, encodedAnswer);
		}
		catch(DataIntegrityViolationException e)
		{
			//get the cause-exception, since it has a better message:
			Throwable root = e.getRootCause();
			String msg = root.getMessage();
			Assert.isTrue(msg.contains("Duplicate entry"));
			

			log.error( msg );
			return Pair.of(oAuthConstants.ERROR, "USER_ALREADY_EXIST");
		}
		

//		String serverPath = extractServerPath(request, redirectUri);
		
//TODO
		//we grab the "redirect-uri"from the login page, and pass it here, so after account-creation we will redirect the user back to his document:
/*		sendMail(email,
				MailMessage.AUTHENTICATION_MAIL_SUBJECT, 
				MailMessage.OAUTH_AUTHENTICATION_MAIL_BODY,
				AuthenticationUtil.OAUTH_ACTIVATE_ACCOUNT,
				serverPath, 
				redirectUri );
*/		
		return ImmutablePair.of(oAuthConstants.OK, "");
	}

	@Override
	public AuthenticationPolicy getAuthenticationSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OauthAccountState isAccountLocked(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendPasswordRestoreMail(String email, String redirectUri) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSecretAnswer(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setLoginFailureForUser(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendUnlockAccountMail(String email, String redirectUri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPassword(String email, String encodedPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pair<String, String> changePassword(String username,
			String encodedCurrentPassword, String newEncodedPassword)
	{
		//validate current password:
		String dbPassword = oAuthRepository.getEncodedPassword(username);
		if(dbPassword == null)
		{
//			throw errorsHandler.createError(ApiErrors.USER_NOT_EXIST, username);
			return Pair.of(oAuthConstants.ERROR, "USER_NOT_EXIST");
		}
		if( !dbPassword.equals(encodedCurrentPassword) )
		{
			//password mismatch: error; update the counter and throw an error
//			String isLocked = onLoginFailure(username);
//			boolean retVal = Boolean.valueOf(isLocked);

//			throw errorsHandler.createError(ApiErrors.CHANGE_PASSWORD_BAD_OLD_PASSWORD, username);
			return Pair.of(oAuthConstants.ERROR, "CHANGE_PASSWORD_BAD_OLD_PASSWORD");
		}
		
		
		if( changePassword(username, newEncodedPassword) )
		{
			return Pair.of(oAuthConstants.OK, "");
		}
		else
		{
			return Pair.of(oAuthConstants.ERROR, "CHANGE PASSWORD FAILED - DB");
		}
	}

	private boolean changePassword(String username, String newEncodedPassword) 
	{
		//via oAuthProcessor, since we want to UPDATE the DB:
		boolean changed = oAuthRepository.changePassword(username, newEncodedPassword);
		if(changed)
		{
			log.info("changing password for user " + username);
		}
		else
		{
			log.error("could not change password to user " + username);
		}
		return changed;
	}

	@Override
	public String getSecretQuestion(String email) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
