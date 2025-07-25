# 📝 Spring Memo CRUD Project

기초적인 Spring Boot 기반의 **메모장 CRUD 웹 애플리케이션**<br>
✅ RESTful한 API 설계 →  계층별 책임 분리 (Layered Architecture) →  JDBC Template + MySQL DB 연동까지 확장

---

## 🔧 사용 기술 스택

- Java 17
- Spring Boot 3.x
- Spring Web
- Lombok
- IntelliJ IDEA
- Postman (API 테스트)
- Spring Data JDBC
- MySQL 8.x
  
---

## 🧩 아키텍처 구성

- `Controller` : HTTP 요청 수신 및 응답 처리
- `Service` : 비즈니스 로직 처리
- `Repository` : DB 접근(JDBC Template)
- `Entity/DTO` : 데이터 계층 분리

> ❗ 모든 계층은 Spring Bean으로 등록되고, 생성자 주입을 통해 의존성을 관리

---

## 📌 주요 기능 (CRUD)

| 기능 | Method | URL | 설명 |
|------|--------|-----|------|
| 메모 생성 | POST | `/memos` | 메모 추가 (제목 + 내용) |
| 전체 조회 | GET | `/memos` | 모든 메모 조회 |
| 단건 조회 | GET | `/memos/{id}` | 특정 메모 조회 |
| 전체 수정 | PUT | `/memos/{id}` | 메모 제목 & 내용 덮어쓰기 |
| 제목만 수정 | PATCH | `/memos/{id}` | 메모 제목만 일부 수정 |
| 삭제 | DELETE | `/memos/{id}` | 특정 메모 삭제 |

- 모든 요청/응답은 JSON 형식으로 처리됨
- 적절한 HTTP 상태 코드 사용 (e.g. 201 Created, 404 Not Found, 400 Bad Request 등)

---

## 💾 DB 구성

- **데이터베이스** : MySQL
- **Schema** : `memo`
- **테이블 구조**

```sql
CREATE TABLE memo (
    id       BIGINT       AUTO_INCREMENT PRIMARY KEY COMMENT '메모 식별자',
    title    VARCHAR(100) NOT NULL COMMENT '제목',
    contents TEXT COMMENT '내용'
);
```
---

## 📂 디렉터리 구조 예시
```bash
com.example.memo
├── controller         # MemoController
├── service
│   ├── MemoService
│   └── MemoServiceImpl
├── repository
│   ├── MemoRepository
│   └── JdbcTemplateMemoRepository
├── dto
│   ├── MemoRequestDto
│   └── MemoResponseDto
│   └── MemoTitleUpdateDto
├── entity
│   └── Memo
```

---

## 📌 테스트 및 결과

- Postman을 활용한 API 테스트

- API 호출 시 다음 상태 코드 반환 확인:
  - `201 Created` (생성 성공)
  - `200 OK` (조회, 수정, 삭제 성공)
  - `404 Not Found` (데이터 없음)
  - `400 Bad Request` (필수 데이터 누락)

---

## ✔️ 개선한 점
- Controller의 책임을 **요청과 응답 처리**로 제한
- **Service**에 비즈니스 로직 집중
- **Repository**에 DB 접근 전담
- **JDBC Template + RowMapper 활용**으로 SQL 분리 및 유연성 확보
- `Optional` 활용한 안전한 null 처리
- `@Transactional`을 통해 트랜잭션 처리 보장

## ⚠️ 한계점 및 개선 방향

- ❌ 예외 처리 공통화가 아직 미흡 → `@ControllerAdvice` 도입 필요
- ❌ DTO 공유로 인한 **null 검사 중복**
- ❌ Service와 Repository의 인터페이스 도입 목적 학습 필요
