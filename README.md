# Schedule Server

이 프로젝트는 사용자 관리와 일정 관리를 위한 Spring Boot 기반의 서버 애플리케이션입니다. 이 `README.md` 파일에서는 주요 클래스와 메서드, 필드의 기능을 설명합니다.

<pre>
  ## API 명세서
</pre>
<img width="925" alt="스크린샷 2024-08-29 오전 1 08 08" src="https://github.com/user-attachments/assets/aaac3c55-da89-41f2-a6a1-10fb13b910bd">

<pre>
  ERD 
</pre>
![Untitled](https://github.com/user-attachments/assets/504172b8-4d88-4759-a537-cc3e130f98f6)


## 1. `UserController`

`UserController`는 사용자 관련 HTTP 요청을 처리합니다.

### 메서드

- **`createUser(@RequestBody UserRequestDto requestDto)`**
  - **기능**: 사용자 등록 요청을 처리합니다.
  - **입력**: `UserRequestDto` 객체 (사용자 정보)
  - **출력**: `UserResponseDto` 객체 (등록된 사용자 정보)

- **`getUser(@PathVariable long id)`**
  - **기능**: 특정 ID를 가진 사용자의 정보를 조회합니다.
  - **입력**: 사용자 ID (`long`)
  - **출력**: `UserResponseDto` 객체 (조회된 사용자 정보)

- **`updateUser(@PathVariable long id, @RequestBody UserRequestDto requestDto)`**
  - **기능**: 특정 ID를 가진 사용자의 정보를 수정합니다.
  - **입력**: 사용자 ID (`long`), `UserRequestDto` 객체 (수정할 사용자 정보)
  - **출력**: `UserResponseDto` 객체 (수정된 사용자 정보)

- **`deleteUser(@PathVariable long id)`**
  - **기능**: 특정 ID를 가진 사용자를 삭제합니다.
  - **입력**: 사용자 ID (`long`)
  - **출력**: 없음 (HTTP 상태 코드 204)

- **`loginUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response)`**
  - **기능**: 사용자의 로그인 요청을 처리하고 JWT 토큰을 생성하여 쿠키에 저장합니다.
  - **입력**: 사용자 이름 (`String`), 비밀번호 (`String`)
  - **출력**: JWT 토큰이 포함된 쿠키

- **`getUserProfile(HttpServletRequest request)`**
  - **기능**: JWT 토큰을 통해 사용자의 프로필 정보를 조회합니다.
  - **입력**: HTTP 요청 (`HttpServletRequest`)
  - **출력**: 사용자 이름 (`String`)

- **`logout(HttpServletResponse response)`**
  - **기능**: 사용자의 로그아웃 요청을 처리하고 JWT 쿠키를 삭제합니다.
  - **입력**: HTTP 응답 (`HttpServletResponse`)
  - **출력**: 로그아웃 성공 메시지 (`String`)

## 2. `UserService`

`UserService`는 사용자 관련 비즈니스 로직을 처리합니다.

### 메서드

- **`getUserById(long id)`**
  - **기능**: 특정 ID를 가진 사용자의 정보를 조회합니다.
  - **입력**: 사용자 ID (`long`)
  - **출력**: `UserResponseDto` 객체 (조회된 사용자 정보)

- **`getUserProfile(String username)`**
  - **기능**: 사용자 이름을 통해 사용자의 프로필 정보를 조회합니다.
  - **입력**: 사용자 이름 (`String`)
  - **출력**: `UserResponseDto` 객체 (조회된 사용자 정보)

- **`createUser(UserRequestDto requestDto)`**
  - **기능**: 새 사용자를 생성합니다.
  - **입력**: `UserRequestDto` 객체 (새 사용자 정보)
  - **출력**: `UserResponseDto` 객체 (생성된 사용자 정보)

- **`updateUser(long id, UserRequestDto requestDto)`**
  - **기능**: 특정 ID를 가진 사용자의 정보를 업데이트합니다.
  - **입력**: 사용자 ID (`long`), `UserRequestDto` 객체 (업데이트할 사용자 정보)
  - **출력**: `UserResponseDto` 객체 (업데이트된 사용자 정보)

- **`deleteUser(long id)`**
  - **기능**: 특정 ID를 가진 사용자를 삭제합니다.
  - **입력**: 사용자 ID (`long`)
  - **출력**: 없음

- **`loginUser(String username, String password)`**
  - **기능**: 사용자의 로그인 요청을 처리하고 JWT 토큰을 생성합니다.
  - **입력**: 사용자 이름 (`String`), 비밀번호 (`String`)
  - **출력**: JWT 토큰 (`String`)

## 3. `JwtUtil`

`JwtUtil`은 JWT 토큰 생성 및 검증을 처리합니다.

### 메서드

- **`createToken(String username)`**
  - **기능**: 주어진 사용자 이름으로 JWT 토큰을 생성합니다.
  - **입력**: 사용자 이름 (`String`)
  - **출력**: JWT 토큰 (`String`)

- **`addJwtToCookie(String token, HttpServletResponse response)`**
  - **기능**: JWT 토큰을 HTTP 쿠키에 추가합니다.
  - **입력**: JWT 토큰 (`String`), HTTP 응답 (`HttpServletResponse`)
  - **출력**: 없음

- **`getTokenFromRequest(HttpServletRequest request)`**
  - **기능**: 요청에서 JWT 토큰을 추출합니다.
  - **입력**: HTTP 요청 (`HttpServletRequest`)
  - **출력**: JWT 토큰 (`String`)

- **`validateToken(String token)`**
  - **기능**: JWT 토큰이 유효한지 검증합니다.
  - **입력**: JWT 토큰 (`String`)
  - **출력**: 유효성 여부 (`boolean`)

- **`getUserInfoFromToken(String token)`**
  - **기능**: JWT 토큰에서 사용자 정보를 추출합니다.
  - **입력**: JWT 토큰 (`String`)
  - **출력**: 사용자 정보 (`Claims`)

- **`substringToken(String token)`**
  - **기능**: JWT 토큰에서 실제 토큰 문자열을 추출합니다.
  - **입력**: JWT 토큰 (`String`)
  - **출력**: 실제 토큰 문자열 (`String`)

## 4. `User`

`User`는 사용자 정보를 나타내는 엔티티입니다.

### 필드

- **`id`**
  - **기능**: 사용자의 고유 ID (`long`)

- **`username`**
  - **기능**: 사용자의 이름 (`String`)

- **`password`**
  - **기능**: 사용자의 암호화된 비밀번호 (`String`)

- **`email`**
  - **기능**: 사용자의 이메일 주소 (`String`)

### 메서드

- **`update(String username, String email)`**
  - **기능**: 사용자의 이름과 이메일을 업데이트합니다.
  - **입력**: 사용자 이름 (`String`), 이메일 (`String`)
  - **출력**: 없음

## 5. `Schedule`

`Schedule`은 일정 정보를 나타내는 엔티티입니다.

### 필드

- **`id`**
  - **기능**: 일정의 고유 ID (`long`)

- **`title`**
  - **기능**: 일정의 제목 (`String`)

- **`content`**
  - **기능**: 일정의 내용 (`String`)

- **`createdDate`**
  - **기능**: 일정 생성일 (`LocalDateTime`)

- **`updatedDate`**
  - **기능**: 일정 업데이트일 (`LocalDateTime`)

- **`author`**
  - **기능**: 일정을 작성한 사용자 (`User`)

### 메서드

- **`setTitle(String title)`**
  - **기능**: 일정 제목을 설정합니다.
  - **입력**: 제목 (`String`)
  - **출력**: 없음

- **`setContent(String content)`**
  - **기능**: 일정 내용을 설정합니다.
  - **입력**: 내용 (`String`)
  - **출력**: 없음

- **`setAuthor(User author)`**
  - **기능**: 일정 작성자를 설정합니다.
  - **입력**: 작성자 (`User`)
  - **출력**: 없음

## 6. `UserRequestDto`

`UserRequestDto`는 사용자 등록 및 수정 요청에 사용되는 데이터 전송 객체입니다.

### 필드

- **`username`**
  - **기능**: 사용자 이름 (`String`)

- **`password`**
  - **기능**: 비밀번호 (`String`)

- **`email`**
  - **기능**: 이메일 주소 (`String`)

## 7. `UserResponseDto`

`UserResponseDto`는 사용자 정보를 응답할 때 사용되는 데이터 전송 객체입니다.

### 필드

- **`id`**
  - **기능**: 사용자 ID (`long`)

- **`username`**
  - **기능**: 사용자 이름 (`String`)

- **`email`**
  - **기능**: 이메일 주소 (`String`)
