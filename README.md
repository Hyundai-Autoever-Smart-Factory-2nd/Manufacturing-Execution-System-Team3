# Manufacturing-Execution-System-Team3

## 🧑‍💻 Code Convention

### Backend (Java - Spring Boot)
- **패키지 네이밍**: `com.mes.module`
- **클래스명**: PascalCase (`OrderService`, `UserController`)
- **메서드명/변수명**: camelCase (`getUserById`, `orderList`)
- **상수**: UPPER_SNAKE_CASE (`MAX_RETRY_COUNT`)
- **주석**: Javadoc 스타일 (`/** ... */`)
- **구조**: Controller → Service → Repository 계층 구조 준수

### Frontend (Vue.js)
- **컴포넌트 파일명**: PascalCase (`OrderList.vue`)
- **폴더 구조**:


- **상태 관리**: Pinia 사용, store 모듈 단위 관리
- **API 호출**: `/api/` 디렉토리에서 Axios 래퍼 활용
- **Lint/Formatter**: ESLint + Prettier 적용

### Git & Commit Message
- **브랜치 전략**: 
- `main` : 배포
- `develop` : 개발
- `feature/*` : 기능 단위 브랜치
- **커밋 규칙 (Conventional Commit)**
- `init: 초기 커밋`
- `feat: 기능 추가`
- `fix: 버그 수정`
- `docs: 문서 수정`
- `refactor: 코드 리팩토링`
- `style: 코드 스타일 수정`
- `test: 테스트 코드`

## 🌿 Branch Rules

### 브랜치 종류
- **main** : 배포용 안정 버전
- **develop** : 개발 통합 브랜치
- **feature/*** : 기능 개발 브랜치 (개발자별 기능 단위)
- **fix/*** : 버그 수정 브랜치
- **release/*** : 배포 준비 브랜치
- **hotfix/*** : 운영 긴급 수정 브랜치

### 브랜치 네이밍 규칙
- 기능 개발: `feature/기능명`  
  예) `feature/login-api`, `feature/order-dashboard`
- 버그 수정: `fix/이슈번호-설명`  
  예) `fix/123-order-count-bug`
- 릴리즈: `release/버전`  
  예) `release/1.0.0`
- 핫픽스: `hotfix/설명`  
  예) `hotfix/login-null-exception`
