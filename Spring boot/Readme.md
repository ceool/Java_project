## 목차

> Spring boot 2.3.9.RELEASE <br>
> Java 13.0.2

### [0. Web Service & Web Application](#0-web-service--web-application) <br>
 - [Web Service](#web-service) <br>
 - [SOAP (Simple Object Access Protocol)](#soap-simple-object-access-protocol) <br>
 - [SOAP-ENV: Envelope](#SOAP-ENV-Envelope) <br>
 - [REST (REpresentational State Transfer)](#rest-representational-state-transfer) <br>
 - [RESTful](#RESTful) <br>
 - [SOAP vs REST](#SOAP-vs-REST) <br>

### [1. Spring Boot로 개발하는 RESTful Service](#1-Spring-Boot로-개발하는-RESTful-Service) <br>
 - [1.1 SprinBoot 개요](#11-SprinBoot-개요) <br>
 - [1.2 REST API 설계](#12-REST-API-설계) <br>
 - [1.3 Spring Boot Project 생성, 실행](#13-spring-boot-project-생성-실행) <br>
 - [1.4 HelloWorld Controller 추가](#14-HelloWorld-Controller-추가) <br>
 - [1.5 HelloWorld Bean 추가](#15-HelloWorld-Bean-추가) <br>
 - [1.6 DispatcherServlet, 프로젝트 동작 이해하기](#16-dispatcherservlet-프로젝트-동작-이해하기) <br>
 - [1.7 Path Variable](#17-Path-Variable) <br>

### [2. User Service API 추가](#2-User-Service-API-추가) <br>
 - [2.1 User Domain 생성](#21-User-Domain-생성) <br>
 - [2.2 GET](#22-GET) <br>
 - [2.3 POST (Status Code: 201)](#23-post-status-code-201) <br>
 - [2.4 Exception Handling](#24-Exception-Handling) <br>
 - [2.5 DELETE](#25-DELETE) <br>

### [3. RESTful Service 기능 확장](#3-RESTful-Service-기능-확장) <br>
 - [3.1 Validation (검증)](#31-validation-검증) <br>
 - [3.2 Internationalization (국제화)](#32-internationalization-국제화) <br>
 - [3.3 XML format으로 반환하기](#33-XML-format으로-반환하기) <br>
 - [3.4 Filtering](#34-Filtering) <br>
 - [3.5 Version 관리](#35-Version-관리) <br>

### [4. Spring Boot API 사용](#4-Spring-Boot-API-사용) <br>
 - [4.1 REST API Level3을 위한 HATEOAS 설정](#41-REST-API-Level3을-위한-HATEOAS-설정) <br>
 - [4.2 REST API Documentation을 위한 Swagger 설정](#42-REST-API-Documentation을-위한-Swagger-설정) <br>
 - [4.3 REST API Monitoring을 위한 Actuator 설정](#43-REST-API-Monitoring을-위한-Actuator-설정) <br>
 - [4.4 Spring Security](#44-Spring-Security) <br>

### [5. JPA 사용](#5-JPA-사용) <br>

### [6. REST API 설계 가이드](#6-REST-API-설계-가이드) <br>


<br>
<br>

## 0. Web Service & Web Application
 - 네트워크 상에서 서로 다른 종류의 컴퓨터들 간에 상호작용하기 위한 소프트웨어 시스템 (HTML, JSON, XML, images)
 - client <--> web server <--> web application <--> database 

### Web Service
 - Request(input) / Response(output) Format (Data exchange 2 formats: XML, JSON)
 - Request Structure
 - Response Structure
 - Endpoint(URL ...)

<br>

### SOAP (Simple Object Access Protocol)
 - XML request / XML response

### SOAP-ENV: Envelope
```
 - SOAP-ENV: Header
 - SOAP-ENV: Body
```

<br>

### REST (REpresentational State Transfer)
 - Resource의 Representation에 의한 상태 전달
 - HTTP Method를 통해 Resource를 처리하기 위한 아키텍쳐

### RESTful
 - REST API를 제공하는 웹 서비스

![사진1](https://user-images.githubusercontent.com/62891711/118434388-f22b8000-b717-11eb-9c93-33d3e2dede89.png)

#### Resource
 - URI(Uniform Resource Identifier), 인터넷 자원을 나타내는 유일한 주소
 - XML, HTML, JSON

<br>

### SOAP vs REST
 - Restrictions vs Architectural Approach
 - Data Exchange Format
 - Service Definition
 - Transport
 - Ease of implementation

<br>

## 1. Spring Boot로 개발하는 RESTful Service
### 1.1 SprinBoot 개요
 - 단독 실행 가능한 플랫폼을 개발하기 위한 애플리케이션 (웹 애플리케이션 내장)
 - 기본설정이 되어있는 starter 컴포넌트를 제공
 - 가능한 자동으로 설정되어 있음
 - 상용화에 필요한 통계, 상태 체크, 외부 설정 등을 제공
 - 설정을 위한 XML 코드를 생성하거나 요구하지 않음
 - https://start.spring.io/ 에서 생성

```
# 해당 프로젝트는 아래와 같은 디펜던시를 초기에 설정함

Developer Tools
 - Spring Boot DevTools
 - Lombok
Web
 - Spring Web
SQL
 - Spring Data JPA
 - H2 Database
```

<br>


### 1.2 REST API 설계
### 1.3 Spring Boot Project 생성, 실행
```
 - resources - application.yml 파일 생성시, yml 기반으로 셋팅 가능
```

<br>

### 1.4 HelloWorld Controller 추가
 - package com.example.restfulwebservice.helloworld, HelloWorldController.java
```
@RestController
 - 컨트롤러 추가

@GetMapping(path = "/hello-world")
 - GET API 맵핑
```

<br>


### 1.5 HelloWorld Bean 추가
```
@GetMapping(path = "/hello-world-bean")
```

![사진2](https://user-images.githubusercontent.com/62891711/118434343-d9bb6580-b717-11eb-8727-af07bbf12aad.png)

```
위 사진과 같이 코드 자동 생성을 위한 lombok 사용 (어노테이션 추가)
@Data
 - 클래스안의 모든 private 필드에 대해 @Getter와 @Setter를 적용하여 세터/게터를 만들어주며
 - 클래스내에 @ToString 과 @EqualsAndHashCode를 적용시켜 메소드를 오버라이드 해주며
 - @RequiredArgsConstructor를 지정해 준다.

@AllArgsConstructor
 - 해당 모든 필드의 생성자를 자동으로 생성해주는 어노테이션
 - @NotNull option 에 따라 null check 도 해준다.

@NoArgsConstructor
 - 매개변수가 없는 디폴트 생성자를 생성함
 - 이외에도 @RequiredArgsConstructor 있음.

(intellij에서 Lombok 플러그인 설치 추천)
```


<br>



### 1.6 DispatcherServlet, 프로젝트 동작 이해하기

#### Spring Boot 동작원리
```
# application.yml
# 설정이름: 값

logging:
  level:
    org.springframework: DEBUG
```

```
# aplication.properties
# 설정이름=값

logging.level.org.springframework = DEBUG
```
Spring Boot 자동 구성(Auto Configuration)
 - DispatcherServletAutoConfiguration (디스패처 서블릿 자동 구성)
 - ErrorMvcAutoConfiguration (오류 Mvc 자동 구성)
 - HttpMessageConvertersAutoConfiguration --> JSON convert

<br>

#### DispatcherServlet

![사진3](https://user-images.githubusercontent.com/62891711/118434344-d9bb6580-b717-11eb-9101-dba83adbf9d3.png)

- 클라이언트의 모든 요청을 한곳으로 받아서 처리
- 요청에 맞는 Handler로 요청을 전달
- Handler의 실행 결과를 Http Response 형태로 만들어서 반환

<br>


![사진4](https://user-images.githubusercontent.com/62891711/118434346-da53fc00-b717-11eb-9c50-43e9e6409bd9.png)

- RestController
- Spring4부터 @RestController 지원
- @Controller + @ResponseBody
- View를 갖지 않는 REST Data(JSON/XML)를 반환


<br>


 ### 1.7 Path Variable
 - ex) http://localhost:8080/books/
 - ex) http://localhost:8080/books/1 or http://localhost:8080/books/123 (가변)
```
ex) 
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
```

<br>


## 2. User Service API 추가
![사진5](https://user-images.githubusercontent.com/62891711/118434962-ebe9d380-b718-11eb-8729-739ece71eb9d.png)

### 2.1 User Domain 생성
```
package com.example.restfulwebservice.user;
 - UserDaoService.java

추후 의존성 주입 (DI)을 위한 UserDaoService 위에 @Service 어노테이션 추가.
 - @Component 사용 가능
 - DI, 의존성 주입은 필요한 객체를 직접 생성하는 것이 아닌 외부로 부터 필요한 객체를 받아서 사용하는 것이다.
 - 이를 통해 객체간의 결합도를 줄이고 코드의 재활용성을 높여준다.
```

```
@Component
 - Spring에서 관리되는 객체임을 표시하기 위해 사용하는 가장 기본적인 annotation이다. 즉, scan-auto-detection과 dependency injection을 사용하기 위해서 사용되는 가장 기본 어노테이션이다.

@Controller
 - Web MVC 코드에 사용되는 어노테이션이다. @RequestMapping 어노테이션을 해당 어노테이션 밑에서만 사용할 수 있다. 

@Repository
 - data repository를 나타내는 어노테이션이다. 플랫폼 특정 exception을 잡아 Spring의 unchecked exception으로 뱉어내준다. ( PersistenceExceptionTranslationPostProcessor )

@Service
 - 비즈니스 로직이나 respository layer 호출하는 함수에 사용된다. 다른 어노테이션과 다르게 @Component에 추가된 기능은 없다. 하지만 나중에 Spring 측에서 추가적인 exception handling을 해줄 수도 있으니 비즈니스 로직에는 해당 어노테이션을 사용한다.
```

<br>


### 2.2 GET
```
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
    }
```

<br>


### 2.3 POST (Status Code: 201)
```
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
```

<br>


### 2.4 Exception Handling
 [Introduce Variable (Ctrl+Alt+V)]
```
// UserNotFoundException.java
package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

```

```
        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
```


#### AOP 이용
```
AOP는 Aspect Oriented Programming의 약자로 관점 지향 프로그래밍이라고 불린다. 관점 지향은 쉽게 말해 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 각각 모듈화하겠다는 것이다.
```
```
# ExceptionResponse.java
package com.example.restfulwebservice.exception;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
```
```
# CustomizedResponseEntityExceptionHandler.java
package com.example.restfulwebservice.exception;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```


<br>


### 2.5 DELETE
```
# UserDaoService.java

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()) {
            User user = iterator.next();

            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
```
```
# UserController.java

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
```

<br>


## 3. RESTful Service 기능 확장
### 3.1 Validation (검증)
```
# User.java

    @Size(min=2, message = "Name은 2글자 이상 입력해주세요.")
    private String name;
    @Past
    private Date joinDate;
```

```
# UserController.java
# @Valid 추가

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
	...
    }
```
```
# CustomizedResponseEntityExceptionHandler.java

## 상속된 ResponseEntityExceptionHandler.java로 이동해서
## handleMethodArgumentNotValid 위에 @Override 추가 (지정하는 것이 좋음)

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed", ex.getBindingResult().toString());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
```

<br>



### 3.2 Internationalization (국제화)
#### @Configuration 등록
 - LocaleResolver
 - Default Locale (Locale.US or Locale.KOREA)
 - ResourceBundleMessageSource
#### Usage
 - generate message budle files
 - @Autowired MessageSource
 - @RequestHeader(value = "Accept-Language", required = false)
 - messageSource.getMessage("greeting.message", null, local)

```
# RestfulWebServiceApplication.java

   @Bean
    public LocaleResolver localeResolver() {
       SessionLocaleResolver localeResolver = new SessionLocaleResolver();
       localeResolver.setDefaultLocale(Locale.KOREA);

       return localeResolver;
   }
```
```
# application.yml
## 다국어 파일 이름을 messages로 지정

spring:
  messages:
    basename: messages
```

```
# messages.properties

greeting.message=안녕하세요
```

```
# messages_en.properties

greeting.message=Hello
```

```
# messages_fr.properties

greeting.message=Bonjour
```

```
# HelloWorldController.java

    # 어노테이션 의존성 주입 추가
    # 같은 타입을 가진 Bean을 자동으로 주입
    @Autowired
    private MessageSource messageSource;
    
    @GetMapping(path = "hello-world-internationalized")
    public String helloWorldInternationalized(
            @RequestHeader(name="Accept-Language", required = false) Locale locale) {

        return messageSource.getMessage("greeting.message", null, locale);
    }
```

<br>


### 3.3 XML format으로 반환하기
```
# pom.xml
# 아래 디펜던시 추가

        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
            <version>2.11.4</version>
        </dependency>
```
```
# 디펜던시 추가후,
# 아래와 같이 API를 호출하면 xml로 불러와짐.

KEY : Accept
VALUE : application/xml
```

<br>

### 3.4 Filtering
 - 중요한 정보 필터링
![사진6](https://user-images.githubusercontent.com/62891711/118440657-f315df00-b722-11eb-8d7b-af275f96b873.png)

#### 사전준비
```
# User.java
private String password;
private String ssn;

# UserDaoService.java (password,ssn 추가)
users.add(new User(1, "ceool", new Date(), "pass1,", "701010-1111111")
```

<br>

#### 방법1
```
# User.java
## JsonIgnore만 추가해도 정보를 숨길 수 있음.
    @JsonIgnore
    private String password;
    
    @JsonIgnore
    private String ssn;
```

#### 방법2
```
# User.java
## class 위에서 어노테이션을 추가하여 필터링 가능

@JsonIgnoreProperties(value = {"password", "ssn"})
public class User {
	...
}
```

<br>


#### 개별 사용자, 전체 사용자 불러오기 (AdminController)
```
# User.java
## class 위에서 어노테이션을 추가

@JsonFilter("UserInfo")
public class User {
	...
}
```
```
# AdminUserController.java

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "password", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value = "/users/{id}")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "password", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }
```

<br>

### 3.5 Version 관리

![사진7](https://user-images.githubusercontent.com/62891711/118442392-80f2c980-b725-11eb-95d5-cc98d1227907.png)


#### 기존 User 정보 상속하여 V2 만듦
```
#UserV2.java

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfoV2")
public class UserV2 extends User {
    private String grade;
}
```

<br>

#### 방법1: v1, v2를 직접 적음
```
# AdminUserController.java
## 해당 파일 참고해서 작성 (v2는 grade가 추가됨.)

@GetMapping("/v1/users/{id}")
	...
	
@GetMapping("/v2/users/{id}")
	...
```

#### 방법2: Request Parameter 이용

```
# AdminUserController.java

@GetMapping(value = "/users/{id}/", params = "version=1")
@GetMapping(value = "/users/{id}/", params = "version=2")

ex) http://localhost:8088/admin/users/1/?version=2
```

#### 방법3: Header 이용
```
# AdminUserController.java

@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")

ex)
Headers
 - KEY: X-API-VERSION
 - VALUE : 2
http://localhost:8088/admin/users/1
```

#### 방법4:produces 사용
```
# AdminUserController.java

@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")

ex)
Headers
 - KEY: Accept
 - VALUE : application/vnd.company.appv2+json
http://localhost:8088/admin/users/1
```

![사진8](https://user-images.githubusercontent.com/62891711/118444981-cf559780-b728-11eb-911c-04dd8d17fe84.png)

<br>

## 4. Spring Boot API 사용
### 4.1 REST API Level3을 위한 HATEOAS 설정
### 4.2 REST API Documentation을 위한 Swagger 설정
### 4.3 REST API Monitoring을 위한 Actuator 설정
### 4.4 Spring Security

## 5. JPA 사용
## 6. REST API 설계 가이드
