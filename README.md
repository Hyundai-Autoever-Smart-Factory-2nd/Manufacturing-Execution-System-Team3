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
