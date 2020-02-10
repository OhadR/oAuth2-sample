oAuth2-sample   [![Build Status](https://travis-ci.org/OhadR/oAuth2-sample.svg?branch=maven-1.6.2-SNAPSHOT)](https://travis-ci.org/OhadR/oAuth2-sample)   [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.ohadr/authentication-flows/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.ohadr/authentication-flows)
=============

This project is a oAuth2 POC, consists of all 3 oAuth parties: the authentication server, a resource server, and a client app.
Each party is represented by its own WAR.

## 23-02-2016: Spring Versions Updated

On 23-02-2016, we have updated Spring versions to the newest!

* Spring Security: 4.0.3.RELEASE
* Spring: 4.2.4.RELEASE
* Spring Security oAuth: 2.0.9.RELEASE

## Make it work

* Deploy all 3 WARs on a servlet container, e.g. tomcat.
* Browse http://localhost:8080/oauth2-client/hello. The client needs a login by itsealf: admin/admin (Spring Security expects your client web-app to have its own credentials).
* client app tries to call the resource-server url http://localhost:8080/oauth2-resource-server/welcome
* This will redirect to oauth2.0 authentication server. Login to authentication-server, currently it is from mem: demo@ohadr.com/demo. it can be configured to read from a DB.
* client should access the resource server using the access-token, and print a message.

## How to Run?
from command line, use the following command:
    
    mvn clean tomcat7:run -Dohadr.project.port=<port>


Project Components
==================

JAR: auth-common
------------
common code for authentication.  You can find it also in this project,
and also it is available in Maven repository:

```xml
<dependency>
  <groupId>com.ohadr</groupId>
  <artifactId>auth-common</artifactId>
  <version>1.1.3</version>
</dependency>
```

Note the version - make sure you use the latest.

KeyStore things to know:
========================
1. a keystore shall be created, both for SSL and for signing the tokens.
2. its alias and password should be updated in the prop file as well as in the tomcat's server.xml
3. algorithm should be DSA (because in the access-token signature my code expects it to be "SHA1withDSA"
4. if you want to work with "localhost", you should make the name "localhost": 
5. http://stackoverflow.com/questions/6908948/java-sun-security-provider-certpath-suncertpathbuilderexception-unable-to-find/12146838#12146838

creating a token using Java's keytool:
keytool.exe -genkeypair -alias <alias> -keypass <key-password> -keyalg DSA -keystore <file-name> -storepass <ks-password> -storetype JCEKS -v


Java Encryption:
================
Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
cipher.init(Cipher.ENCRYPT_MODE, secretKey);
String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
return encryptedString;

http://techie-experience.blogspot.co.il/2012/10/encryption-and-decryption-using-aes.html
http://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html#init(int, java.security.Key)


HTML forms:
onSubmit vs action

