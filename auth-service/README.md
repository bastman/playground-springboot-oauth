# oauth authorization service

- http://www.hascode.com/2016/03/setting-up-an-oauth2-authorization-server-and-resource-provider-with-spring-boot/#Creating_the_Authorization_Server

login with basic auth -> receive jwt

    $ curl -XPOST -k foo:foosecret@localhost:9000/hascode/oauth/token \
         -d grant_type=password -d client_id=foo -d client_secret=abc123 \
         -d redirect_uri=http://www.example-service.local -d username=bar -d password=barsecret -v
     
 