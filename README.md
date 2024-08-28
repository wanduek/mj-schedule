# Schedule Server

이 프로젝트는 사용자 관리와 일정 관리를 위한 Spring Boot 기반의 서버 애플리케이션입니다. 이 `README.md` 파일에서는 주요 클래스와 메서드, 필드의 기능을 설명합니다.

<pre>
  API 명세서
</pre>
<img width="925" alt="스크린샷 2024-08-29 오전 1 08 08" src="https://github.com/user-attachments/assets/aaac3c55-da89-41f2-a6a1-10fb13b910bd">

<pre>
  ERD 
</pre>
![Untitled](https://github.com/user-attachments/assets/504172b8-4d88-4759-a537-cc3e130f98f6)


# Schedule Server

이 프로젝트는 사용자 관리, 일정 관리 및 댓글 관리 기능을 제공하는 Spring Boot 기반의 서버 애플리케이션입니다. 아래는 프로젝트에서 구현된 주요 기능들과 그 상태를 설명합니다.

## 주요 기능

### 1. 사용자 관리 기능

- **사용자 등록**
  - **기능 설명**: 새로운 사용자를 등록할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `POST /users`
  - **설명**: 사용자는 이름, 비밀번호, 이메일을 입력하여 계정을 생성할 수 있습니다. 비밀번호는 안전하게 암호화되어 저장됩니다.

- **사용자 조회**
  - **기능 설명**: 특정 사용자의 정보를 조회할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `GET /users/{id}`
  - **설명**: 사용자 ID를 통해 사용자의 정보를 조회할 수 있습니다.

- **사용자 정보 수정**
  - **기능 설명**: 사용자의 이름, 비밀번호, 이메일 등의 정보를 수정할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `PUT /users/{id}`
  - **설명**: 사용자 ID를 통해 해당 사용자의 정보를 수정할 수 있습니다.

- **사용자 삭제**
  - **기능 설명**: 특정 사용자를 삭제할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `DELETE /users/{id}`
  - **설명**: 사용자 ID를 통해 해당 사용자를 삭제할 수 있습니다.

- **로그인**
  - **기능 설명**: 사용자가 로그인할 수 있는 기능입니다.
  - **구현 상태**: 미완료
  - **엔드포인트**: `POST /users/login`
  - **설명**: 사용자 이름과 비밀번호로 로그인할 수 있으며, 로그인 성공 시 JWT 토큰이 생성됩니다.

- **로그아웃**
  - **기능 설명**: 사용자가 로그아웃할 수 있는 기능입니다.
  - **구현 상태**: 미완료
  - **엔드포인트**: `POST /users/logout`
  - **설명**: 사용자는 로그아웃 요청을 통해 JWT 토큰이 삭제된 상태로 로그아웃할 수 있습니다.

- **사용자 프로필 조회**
  - **기능 설명**: 로그인된 사용자의 프로필 정보를 조회할 수 있는 기능입니다.
  - **구현 상태**: 미완료
  - **엔드포인트**: `GET /users/profile`
  - **설명**: JWT 토큰을 통해 현재 로그인된 사용자의 정보를 조회할 수 있습니다.

### 2. 일정 관리 기능

- **일정 생성**
  - **기능 설명**: 새로운 일정을 생성할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `POST /schedules`
  - **설명**: 일정의 제목, 내용, 작성자 등을 입력하여 일정을 생성할 수 있습니다. 생성된 일정은 작성자와 연결됩니다.

- **일정 조회**
  - **기능 설명**: 특정 일정 또는 모든 일정을 조회할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `GET /schedules/{id}` (특정 일정 조회), `GET /schedules` (모든 일정 조회)
  - **설명**: 일정 ID를 통해 특정 일정을 조회하거나, 모든 일정을 조회할 수 있습니다.

- **일정 수정**
  - **기능 설명**: 특정 일정을 수정할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `PUT /schedules/{id}`
  - **설명**: 일정 ID를 통해 해당 일정의 제목이나 내용을 수정할 수 있습니다.

- **일정 삭제**
  - **기능 설명**: 특정 일정을 삭제할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `DELETE /schedules/{id}`
  - **설명**: 일정 ID를 통해 해당 일정을 삭제할 수 있습니다.

### 3. 댓글 관리 기능

- **댓글 등록**
  - **기능 설명**: 특정 일정에 댓글을 추가할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `POST /comments`
  - **설명**: 일정 ID와 함께 댓글 내용을 입력하여 새로운 댓글을 생성할 수 있습니다. 댓글은 해당 일정과 작성자와 연결됩니다.

- **댓글 조회**
  - **기능 설명**: 특정 댓글을 조회할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `GET /comments/{id}`
  - **설명**: 댓글 ID를 통해 특정 댓글의 상세 내용을 조회할 수 있습니다.

- **댓글 수정**
  - **기능 설명**: 특정 댓글을 수정할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `PUT /comments/{id}`
  - **설명**: 댓글 ID를 통해 해당 댓글의 내용을 수정할 수 있습니다.

- **댓글 삭제**
  - **기능 설명**: 특정 댓글을 삭제할 수 있는 기능입니다.
  - **구현 상태**: 완료
  - **엔드포인트**: `DELETE /comments/{id}`
  - **설명**: 댓글 ID를 통해 해당 댓글을 삭제할 수 있습니다.

## 구현 상태

현재 프로젝트는 사용자 관리, 일정 관리 및 댓글 관리 기능이 모두 구현된 상태입니다. 사용자와 일정, 댓글 간의 연관 관계 설정, JWT 토큰을 이용한 인증 및 인가 기능도 정상적으로 동작하고 있습니다. 향후 추가적인 기능 구현이나 성능 최적화가 필요할 수 있습니다.

### 추가 고려사항

- **보안 강화**: 현재 구현된 JWT 토큰 인증 방식은 기본적인 보안 기능을 제공하지만, 향후 토큰의 만료 시간 관리, 토큰 재발급 기능 등을 추가하여 보안을 강화할 수 있습니다.
- **예외 처리**: 예외 상황에 대한 보다 세밀한 처리 및 사용자 친화적인 에러 메시지 제공이 필요할 수 있습니다.
- **성능 최적화**: 대규모 사용자를 대상으로 한 성능 테스트와 최적화 작업이 필요할 수 있습니다.
