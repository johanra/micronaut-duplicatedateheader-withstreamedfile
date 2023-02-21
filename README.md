# micronaut-duplicatedateheader-withstreamedfile

A controller (DocumentController) with an endpoint (download) which returns CompletedFile returns the date header twice in the response headers.

```
curl --location --request GET 'localhost:8080/api/document/download' --head
```
returns

```
HTTP/1.1 200 OK
date: Tue, 21 Feb 2023 13:18:24 GMT
Connection: keep-alive
Content-Type: text/plain
date: Tue, 21 Feb 2023 13:18:24 GMT
expires: Tue, 21 Feb 2023 13:19:24 GMT
Cache-Control: private, max-age=60
last-modified: Tue, 21 Feb 2023 13:18:24 GMT
transfer-encoding: chunked
```


An endpoint that uses String as return value just returns a single date header 

```
curl --location --request GET 'localhost:8080/api/document/info' --head
```
returns

```
HTTP/1.1 200 OK
date: Tue, 21 Feb 2023 13:18:30 GMT
Content-Type: application/json
content-length: 16
connection: keep-alive
```

In my case this causes a problem because the micronaut application runs inside an istio mesh where duplicate headers are [merged](https://istio.io/latest/docs/reference/config/security/normalization)

This merge then results in an invalid value for the date header as this can only contain a single value.

According to this [rfc](https://www.rfc-editor.org/rfc/rfc7230#section-3.2.2), a sender should not generate multiple header fields with the same name unless the entire field value is defined as comma separated list