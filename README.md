DemoResourceServer
---
resource server從request取得accessToken向redis驗證,驗證成功後藉由spring security annotation進行method權限控管。  
  
環境初使化
---
1. 啟動eureka server
2. 啟動oauth-service

測試
---
####Resource Owner Password Credentials Grant Flow （使用者的帳號密碼）
* 先向OAuth2取得token  
測試scope權限 #oauth2.hasScope('read')
````
curl -X GET \
  http://localhost:8080/beaccount/getbeaccount/3 \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer 8472b699-1a4f-4609-a8e7-1878ee20ad5d' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Host: localhost:8080' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'cookie: JSESSIONID=58724C454EED0C3FEA6718D6F5B01024' \
  -b JSESSIONID=58724C454EED0C3FEA6718D6F5B01024
````  
測試authorities權限 hasRole('ADMIN')
````
curl -X GET \
  http://localhost:8080/beaccount/deletebeaccount/3 \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer 8472b699-1a4f-4609-a8e7-1878ee20ad5d' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'cookie: JSESSIONID=58724C454EED0C3FEA6718D6F5B01024' \
  -b JSESSIONID=58724C454EED0C3FEA6718D6F5B01024
````  
####Client Credentials Grant Flow （Client 的帳號密碼）
* 先向OAuth2取得token  
測試scope權限 #oauth2.hasScope('infomessage.2c.marquee.read')
````$xslt
curl -X GET \
  http://localhost:8080/infomessage/read \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer 7b5ff6e0-ba89-4781-a54d-6f7c2f87f2e2' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'cookie: JSESSIONID=58724C454EED0C3FEA6718D6F5B01024' \
  -b JSESSIONID=58724C454EED0C3FEA6718D6F5B01024
````
測試scope權限***access_denied*** #oauth2.hasScope('infomessage.2c.marquee.update') 
````
curl -X GET \
  http://localhost:8080/infomessage/update \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer 7b5ff6e0-ba89-4781-a54d-6f7c2f87f2e2' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'User-Agent: PostmanRuntime/7.13.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'cookie: JSESSIONID=58724C454EED0C3FEA6718D6F5B01024' \
  -b JSESSIONID=58724C454EED0C3FEA6718D6F5B01024
````  
實作
---
1.RedisResourceConfig驗證token由redis驗  
2.RemoteResourceConfig驗證token向oauth驗