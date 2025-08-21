# nbc_16

Spring Boot 기반의 인증/회원/권한(ADMIN/USER) 예제를 포함한 API 서버입니다.  
로컬에서는 H2(메모리)로 빠르게 구동하고, 배포 환경에서는 JAR 실행(EC2 등)로 운영하는 구성을 가정합니다.

> 주요 키워드: Spring Boot 3.x, Spring Security(JWT), Swagger(OpenAPI), H2(in-memory, 콘솔), Gradle, REST API

---
## 배포 주소 & Swagger

http://52.1.240.211:8080/swagger

테스트 순서
1. 관리자 회원가입
2. 유저 회원가입
3. 관리자 로그인
4. 토큰 넣기 우측 상단
<img width="1498" height="349" alt="image" src="https://github.com/user-attachments/assets/621a2855-3c28-4603-b221-89168eebe384" />
5. 유저 회원에 권한 부여


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

nbc_16/
├─ nbc16/
│  └─ src/
│     ├─ main/java/...
│     │  ├─ common/config/SecurityConfig.java   # 보안 설정
│     │  ├─ common/filter/SecurityFilter.java   # (사용 시) 커스텀 보안 필터
│     │  ├─ domain/user/...                     # User, UserRole(ADMIN/USER), Repository
│     │  ├─ api/auth/...                        # 인증/회원가입 컨트롤러
│     │  ├─ api/user/...                        # 사용자 조회 컨트롤러
│     │  └─ api/admin/...                       # 관리자 전용(권한 변경 등)
│     └─ main/resources/
│        ├─ application.yml
│        ├─ application-local.yml
│        └─ application-prod.yml
└─ README.md

## 보안/권한 정책

공개: /auths/**, /login/**, /swagger (문서 페이지)

관리자 전용: /admin/** (ROLE_ADMIN 필요)

기타: 프로젝트 정책에 따라 인증 필수

표준 에러 코드 가이드:

비밀번호 불일치: 401 Unauthorized

권한 부족(관리자 전용 접근): 403 Forbidden

사용자 없음: 404 Not Found

중복 가입: 409 Conflict
