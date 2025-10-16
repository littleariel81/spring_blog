## 프로젝트 소개
스프링부트 백엔드 개발자 학습용 블로그 프로젝트입니다.  
자바와 Spring Boot, Gradle을 활용하여 REST API 기반 블로그 기능을 구현했습니다.

- **기술 스택**: Java 24, Spring Boot, Spring Data JPA, H2 DB, Gradle, GitHub Actions (CI)
- **CI/CD**: 현재 GitHub Actions CI만 설정, CD(AWS 배포)는 미구현
- **기능**:
    - 게시글 CRUD
    - JWT 토큰 기반 인증
    - CI 통과 확인

---

## 프로젝트 구조
```
spring-blog/
├─ src/main/java # Service, Controller, Repository
├─ src/main/resources # Configuration, static files
├─ gradle/ # Gradle Wrapper
├─ gradlew / gradlew.bat
├─ build.gradle
└─ .github/workflows/ci.yml
```