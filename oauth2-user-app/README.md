# user app

this web application is a sample for application the end-user uses to technically use the resources, and to register a
new oauth2-client. This is what the /oauth2-clients API does.
And the auth-server should read the clients from the DB, rather than from the application.yml file.

Starting a MySQL instance is simple:

    docker pull mysql/mysql-server

    docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
    docker run --name ohads-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d -p 3306:3306 mysql/mysql-server:latest

... where some-mysql is the name you want to assign to your container, my-secret-pw is the password to be set for the MySQL root user and tag is the tag specifying the MySQL version you want. See the list above for relevant tags.

Or use docker-compose:

    docker-compose -f docker-compose.yml up -d

## oauth2-registered-client

the schema of can be found here:

    spring-security-oauth2-authorization-server-1.4.1.jar
    org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql

## users-schema

Spring Security itself doesn’t provide a built-in SQL schema for storing users and passwords like it does for OAuth2 
client credentials. However, the typical way to store users in a database is by creating a schema that aligns with 
Spring Security’s JdbcUserDetailsManager, which expects a table structure similar to:

    oauth2-auth-server/src/main/resources/users-schema.sql

# TODO

i store in the DB all the clients, but maybe i should indicate which client relates to which user. by username?
thus, user can see all his oauth-clients...

also, maintain the users in the SQL and the change the login for this app to not "admin" role, but a regualr
user. the login will be "regualr", not "oAuth" (??)

how user is registered? maybe use authentication-flows? :) or just KISS... this is only demo, not prod!!