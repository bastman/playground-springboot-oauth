# oauth authorization service

login with basic auth -> receive jwt

    $ curl -XPOST -k foo:foosecret@localhost:9000/hascode/oauth/token \
         -d grant_type=password -d client_id=foo -d client_secret=abc123 \
         -d redirect_uri=http://www.example-service.local -d username=bar -d password=barsecret -v
     
 