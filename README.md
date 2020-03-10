# dbunit-multiple-datasource

![Main workflow](https://github.com/arkanjoms/dbunit-multiple-datasource/workflows/Main%20workflow/badge.svg)

Example project with:
* spring-boot
* java 8
* postgres
* integration tests
* dbunit
* h2

## Run app

Create a database and two schemas with name `customer` and `store`, set the postgres configuration with the host and credentials.
Execute command below:
```bash
$ mvn spring-boot:run
```

In another shell run command:
```bash
$ curl --request GET \
  --url 'http://localhost:8080/customers?page=0&size=10'
```

## Run tests

Execute command:
```bash
$ mvn test
```
