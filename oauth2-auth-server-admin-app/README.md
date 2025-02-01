Starting a MySQL instance is simple:

    docker pull mysql/mysql-server

    docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
    docker run --name ohads-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d -p 3306:3306 mysql/mysql-server:latest

... where some-mysql is the name you want to assign to your container, my-secret-pw is the password to be set for the MySQL root user and tag is the tag specifying the MySQL version you want. See the list above for relevant tags.

Or use docker-compose:

    docker-compose -f docker-compose.yml up -d

the schema of can be found here:

    spring-security-oauth2-authorization-server-1.4.1.jar
    org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql