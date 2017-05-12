# jwts-demo

- https://auth0.com/blog/securing-spring-boot-with-jwts/

- https://github.com/auth0-blog/spring-boot-jwts/blob/master/src/main/java/com/example/security/JWTLoginFilter.java

# api

POST /login

{"username":"admin", "password":"password"}

    $ curl -v -k -XPOST -H "Content-Type: application/json" -d '{"username":"admin", "password":"password"}' "http://localhost:8080/auth/login"

    $ curl -v -H "Authorization: Bearer <BEARER_TOKEN>" http://localhost:8080/api
