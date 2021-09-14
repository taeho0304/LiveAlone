# Dependencies
[Back-end]
MySQL
Java 8+
Gradle 6.7+
Eclipse or IntelliJ

[Front-end]
Node 14+
VS Studio

# mysql 접근
url: jdbc:mysql://localhost:3306
username: root
password: ssafy

# 데이터베이스 생성
```sql
create database IF NOT EXISTS `livealone` collate utf8mb4_general_ci;
```

# Back-end + Front-end 전체 빌드 (프로젝트 루트 디렉토리에서 수행)
```bash
gradle clean build
```


# 프로젝트 디렉토리 구조 설명 (/src 하위 디렉토리 및 파일)
[Back-end]
```bash
.
└── main
    ├── generated
    ├── java
    │   └── com
    │       └── ssafy
    │           ├── GroupCallApplication.java
    │           ├── api  /* REST API 요청관련 컨트롤러, 서비스, 요청/응답 모델 정의*/
    │           │   ├── controller
    │           │   │   ├── AuthController.java
    │           │   │   └── UserController.java
    │           │   ├── request
    │           │   │   ├── UserLoginPostReq.java
    │           │   │   └── UserRegisterPostReq.java
    │           │   ├── response
    │           │   │   ├── UserLoginPostRes.java
    │           │   │   └── UserRes.java
    │           │   └── service
    │           │       ├── UserService.java
    │           │       └── UserServiceImpl.java
    │           ├── common /* 공용 유틸, 응답 모델, 인증, 예외처리 관련 정의*/
    │           │   ├── auth
    │           │   │   ├── JwtAuthenticationFilter.java
    │           │   │   ├── SsafyUserDetailService.java
    │           │   │   └── SsafyUserDetails.java
    │           │   ├── exception
    │           │   │   └── handler
    │           │   │       └── NotFoundHandler.java
    │           │   ├── model
    │           │   │   └── response
    │           │   │       └── BaseResponseBody.java
    │           │   └── util
    │           │       ├── JwtTokenUtil.java
    │           │       └── ResponseBodyWriteUtil.java
    │           ├── config /* WebMvc 및 JPA, Security, Swagger 등의 추가 플러그인 설정 정의*/
    │           │   ├── JpaConfig.java
    │           │   ├── SecurityConfig.java
    │           │   ├── SwaggerConfig.java
    │           │   └── WebMvcConfig.java
    │           └── db /* 디비에 저장될 모델 정의 및 쿼리 구현 */
    │               ├── entity
    │               │   ├── BaseEntity.java
    │               │   └── User.java
    │               └── repository
    │                   ├── UserRepository.java
    │                   └── UserRepositorySupport.java
    └── resources
        ├── README.md
        ├── application.properties /* 웹 리소스(서버 host/port, 디비 host/port/계정/패스워드) 관련 설정 정의 */
        ├── babel.config.js
        ├── dist
        ├── package-lock.json
        ├── package.json
        ├── public
```
