# nbc_16

Spring Boot 기반의 인증/회원/권한(ADMIN/USER) 예제를 포함한 API 서버입니다.  
로컬에서는 H2(메모리)로 빠르게 구동하고, 배포 환경에서는 JAR 실행(EC2 등)로 운영하는 구성을 가정합니다.

> 주요 키워드: Spring Boot 3.x, Spring Security(JWT 없이 기본 구조), Swagger(OpenAPI), H2(in-memory, 콘솔), Gradle, REST API

---

## 기술 스택

- Language: Java 17+
- Framework: Spring Boot 3.x
  - spring-web, spring-validation
  - spring-security
  - spring-data-jpa
- DB
  - Local: H2 (in-memory)
  - Prod: RDB(선택) — 프로파일로 분리
- 문서화: springdoc-openapi (Swagger UI)
- 빌드/런타임: Gradle, JAR

---

## 프로젝트 구조(개요)

