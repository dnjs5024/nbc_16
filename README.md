# nbc_16

Spring Boot 기반의 인증/회원/권한(ADMIN/USER) 예제를 포함한 API 서버입니다.  
로컬에서는 H2(메모리)로 빠르게 구동하고, 배포 환경에서는 JAR 실행(EC2 등)로 운영하는 구성을 가정합니다.

> 주요 키워드: Spring Boot 3.x, Spring Security(JWT), Swagger(OpenAPI), H2(in-memory, 콘솔), Gradle, REST API

---

## 기술 스택

- Language: Java 17+
- Framework: Spring Boot 3.x
  - spring-web
  - spring-security
  - spring-data-jpa
- DB
  -  H2 (in-memory)
- 문서화: springdoc-openapi (Swagger UI)
- 빌드/런타임: Gradle, JAR

---

## 프로젝트 구조(개요)

nbc16/
├─ src/main/java/...
│ ├─ common/config/SecurityConfig.java # 시큐리티 설정 (/auths/, /login/ 허용, /admin/** ROLE_ADMIN)
│ ├─ common/filter/SecurityFilter.java # (있는 경우) 인증/예외 필터 훅
│ ├─ domain/user/... # User, UserRole(ADMIN/USER) 등
│ └─ ... # Controller/Service/Repository
└─ src/main/resources/
├─ application.yml # 공통 설정
