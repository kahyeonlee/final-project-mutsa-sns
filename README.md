# 💬 Mutsa - SNS

### 💻 개발환경
***
- 에디터 : Intellij Ultimate
- 개발 툴 : SpringBoot 2.7.5
- 자바 : JAVA 11
- 빌드 : Gradle 6.8
- 서버 : AWS EC2
- 배포 : Docker
- 데이터베이스 : MySql 8.0
- 필수 라이브러리 : SpringBoot Web, MySQL, Spring Data JPA, Lombok, Spring Security


### 📋 기능정의
***
#### **1. 회원 인증,인가**
    - 모든 회원은 회원가입을 통해 회원이 됩니다.
    - 로그인을 하지 않으면 SNS 기능 중 피드를 보는 기능만 가능합니다.
    - 로그인한 회원은 글쓰기, 수정, 댓글, 좋아요, 알림 기능이 가능합니다.

#### **2. post**

    - 포스트를 쓰려면 회원가입 후 로그인(Token받기)을 해야 합니다.
    - 포스트의 길이는 총 300자 이상을 넘을 수 없습니다.
    - 포스트의 한 페이지는 20개씩 보이고 총 몇 개의 페이지인지 표시가 됩니다.
    - 로그인 하지 않아도 글 목록을 조회 할 수 있습니다.
    - 수정 기능은 글을 쓴 회원만이 권한을 가집니다.
    - 포스트의 삭제 기능은 글을 쓴 회원만이 권한을 가집니다.

### 💡 EndPoint
***
#### **1. 회원가입 - POST /api/v1/users/join**
- 입력
```json
  "password": "1234",
  "userName": "gh"
```
- 결과
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "id": 9,
    "userName": "gh"
  }
}

```
#### **2. 로그인 - POST /api/v1/users/login**
- 입력
```json
  "password": "1234",
  "userName": "gh"
```
- 결과
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImdoIiwiaWF0IjoxNjcyMDE3MjM4LCJleHAiOjE2NzIwMzUyMzh9.PcSlsqu0jNvf67laXgD9WdK0Gv0NMWAy18tfNkr5DTE"
  }
}
```
#### **3. 포스트 작성 - POST /api/v1/posts**
- 입력
```json
{
  "body": "hi. it's me",
  "title": "hi"
}
```
- 결과
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "postId": 18,
    "message": "포스트 등록 완료"
  }
}
```
#### **4. 포스트 수정 - PUT /api/v1/posts/{id}**
- 입력 (매개변수로 postId)
```json
{
  "body": "hello. it's me",
  "title": "hello"
}
```
- 결과
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "postId": 18,
    "message": "포스트 수정 완료"
  }
}
```
#### **5. 포스트 삭제 - DELETE /api/v1/posts/{postId}**
- 입력(매개변수로 postID)
- 결과
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "postId": 18,
    "message": "포스트 삭제 완료"
  }
}
```
#### **6. 포스트 1개 조회(상세) - GET /api/v1/posts/{postId}**
- 입력(매개변수로 postID)
- 결과
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "id": 18,
    "title": "hello",
    "body": "hello. it's me",
    "userName": "gh",
    "createdAt": "2022-12-26T10:17:37.933636",
    "lastModifiedAt": "2022-12-26T10:19:24.281174"
  }
}
```
#### **7. 포스트 리스트 조회 - GET /api/v1/posts**
- 결과
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "content": [
      {
        "id": 18,
        "title": "hello",
        "body": "hello. it's me",
        "userName": "gh",
        "createdAt": "2022-12-26 10:17:37",
        "lastModifiedAt": "2022-12-26 10:19:24"
      },
      {
        "id": 17,
        "title": "hi",
        "body": "hi. it's me",
        "userName": "gh",
        "createdAt": "2022-12-26 10:17:36",
        "lastModifiedAt": "2022-12-26 10:17:36"
      },
      {
        "id": 16,
        "title": "hello-title",
        "body": "hello-body",
        "userName": "kyeongrok27",
        "createdAt": "2022-12-26 00:54:53",
        "lastModifiedAt": "2022-12-26 00:54:53"
      }
    ],
    "pageable": {
      "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
      },
      "offset": 0,
      "pageNumber": 0,
      "pageSize": 20,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 15,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "first": true,
    "numberOfElements": 15,
    "empty": false
  }
}
```
### 📉 ERD Diagram
***
![img.png](img.png)