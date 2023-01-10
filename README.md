<div align="center">
<h1> <a href="https://antique-almandine-653.notion.site/Mutsa-SNS-eed83a8aef6c4f20b26b76ed4a3b6e8b"> Mutsa - SNS 💬</a></h1>
</div>

### 멋사스네스(mustsa-SNS) 개인 프로젝트
****
`mutsaSNS(멋사스네스) 프로젝트`는 로그인, 글쓰기, 수정, 삭제, 피드기능(페이징), 댓글, 좋아요, 알림 기능을 가지고 있는 sns입니다.

<br>

### 💻 개발환경
***
- **에디터** : Intellij Ultimate
- **개발 툴** : SpringBoot 2.7.5
- **자바** : JAVA 11
- **빌드** : Gradle 6.8
- **서버** : AWS EC2
- **배포** : Docker
- **데이터베이스** : MySql 8.0
- **필수 라이브러리** : SpringBoot Web, MySQL, Spring Data JPA, Lombok, Spring Security

<br>

### 🎁 프로젝트를 진행하면서 느낀 점
*** 
#### 1주차 미션 수행 시
- 프로젝트에서 `Test 코드 작성`을 연습,적용하려고 노력하였고 회원 인증, 인가 기능에 Admin 기능을 추가할때 테스트 코드를 돌려보면서  `unit 테스트`를 할 수 있어서 swagger에서 테스트하는 실행 시간을 줄일 수 있을꺼 같습니다.
- GitLab을 통해서 `CI/CD를 적용`하여 프로젝트를 진행했습니다. `배포 스크립트`를 만들고 적용하는데 어려움을 겪긴 했지만  수정사항이 생길때마다 배포 작업을 안해도 되서 반복 작업을 업앨  수 있었습니다.
- `보완해야 할 점`
  1. log 찍어서 확인하는 습관
  2. 주석 처리(팀 프로젝트 진행시 코드 설명)

#### 2주차 미션 수행 시
- 팀원과 코드리뷰를 통해서 리뷰 받았던 부분인 메서드이름을 명확하게 쓰고  `controller`에서 코드를 최대한 줄이고 `service`에서 해결을 하도록 하여 코드 가독성을 높이려고 노력하였습니다.
- `soft delete`를 사용해서 실무에서 쓰는 거처럼 DB에서 완전 삭제를 방지할 수 있어서 흥미로웠다. 
- `cascade`/`orphanRemoval`을 적용하여 FK 적용된 데이터 삭제 처리를 적용해봐서 새로운 부분을 공부하게 되어서 좋았습니다.
- `보완해야할 점`
  1. spring-security test에 대해서 공부


<br>

### 📌체크리스트
****
####  AWS EC2에 Docker 배포
- [x] AWS EC2 t3.small 인스턴스 생성
- [x] 인스턴스에 Docker 설치
####  [Gitlab CI & Crontab CD](https://velog.io/@gmeoq/series/GitLAb)
- [x] GitLab에 새 프로젝트 만들기
- [x] Dockerfile 생성
- [x] 파이프라인 생성
- [x] Docker에 pull,run
- [x] 배포 스크립트 만들기
- [x] Crontab 사용
#### [Swagger](http://ec2-3-34-130-47.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/#/)
- [x] [Swagger 의존성 추가](https://velog.io/@gmeoq/SpringBoot-Swagger-%EC%A0%81%EC%9A%A9)
- [x] .yml 파일 수정
- [x] SwaggerConfiguration 생성
- [x] [swaggerConfig Custom](https://velog.io/@gmeoq/swagger-custom%ED%95%98%EA%B8%B0)

<br>

### 📝 기능 정의서
*******
#### 회원 인증,인가
- 유저이름과 비밀번호를 기입하고 회원가입 , 유저이름 중복시 custom exception 발생
- 로그인시, token 발행
#### 게시물 등록 ,수정, 삭제, 1개조회, 리스트
- 로그인 후, 게시물 등록/수정/삭제 가능
- 비회원이거나 로그인을 안한 경우에도 게시물 1개 조회, 리스트 조회 기능은 사용 가능함
- 게시물 등록은 300자 이내로만 가능
- 게시물 수정, 삭제 권한은 본인의 게시물일 때만 가능
- 게시물 리스트는 한 페이지에 20개씩만 보이보록 표시
#### 댓글 등록, 수정, 삭제, 리스트
- 로그인 후, 댓글 등록/수정/삭제/목록 가능
- 댓글 등록은 100자 이내로만 가능 
- 댓글 수정, 삭제 권한은 본인이 작성한 댓글일 경우에만 가능
#### 마이피드
- 내가 작성한 글만 보이는 기능
- 목록 기능 페이징 처리, 피드 갯수 20개
#### 좋아요
- 로그인 후 , 좋아요 누를수 있는 기능
- 중복으로 좋아요 누르는 경우, custom exception 발생
#### 알람 목록 조회
- 다른 회원이 좋아요를 누르거나 댓글을 작성할 시 게시물 작성자가 알람목록을 확인할 수 있는 기능
- 알람 조회는 회원만 가능

<br>

### 📉 ERD Diagram
***
![](../../Pictures/멋사_erd.png)


<br>

### 💡 EndPoint 
***

| **no** | **구분** | **기능** | **Method** | **URI** |
| --- | --- | --- | --- | --- |
| 1 | 회원 | 회원가입 | POST  | /api/v1/users/join |
| 2 |  | 로그인 | POST  | /api/v1/users/login |
| 3 | 포스트 | 게시물 등록 | POST  | /api/v1/posts |
| 4 |  | 게시물 삭제 | DELETE | /api/v1/posts/{id} |
| 5 |  | 게시물 수정 | PUT | /api/v1/posts/{id} |
| 6 |  | 게시물 1개 조회 | GET | /api/v1/posts/{id} |
| 7 |  | 게시물 목록 조회 | GET |  /api/v1/posts |
| 8 | 댓글 | 댓글 등록 | POST  | /api/v1/posts/{postsId}/comments |
| 9 |  | 댓글 삭제 | DELETE | /api/v1/posts/{postsId}/comments/{id} |
| 10 |  | 댓글 수정 | PUT | /api/v1/posts/{postId}/comments/{id} |
| 11 |  | 댓글 목록 조회 | GET | /api/v1/posts/{postId}/comments |
| 12 | 좋아요 | 좋아요 눌르기 | POST  | /api/v1/posts/{postId}/likes |
| 13 |  | 좋아요  카운트 | GET | /api/v1/posts/{postsId}/likes |
| 14 | 마이피드 | 마이피드 조회 | GET | /api/v1/posts/my |
| 15 | 알림 | 알림 조회 | GET |  /api/v1/alarms |

<br>

### 📲 URI 입력/ 반환값
******
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
#### **8. 댓글 작성 - POST /api/v1/posts/{postsId}/comments**
- 입력폼 (JSON 형식)
```json
{
	"comment" : "comment test4"
}
```
- 리턴 (JSON 형식)
```json
{
	"resultCode": "SUCCESS",
	"result":{
		"id": 4,
		"comment": "comment test4",
		"userName": "test",
		"postId": 2,
		"createdAt": "2022-12-20T16:15:04.270741"
	}
}
```

#### **9. 댓글  수정 - PUT /api/v1/posts/{postId}/comments/{id}**
- 입력폼 (JSON 형식)
```json
{
  "comment" : "modify comment"
}
```
- 리턴 (JSON 형식)
```json
{
  "resultCode": "SUCCESS",
  "result":{
    "id": 4,
    "comment": "modify comment",
    "userName": "test",
    "postId": 2,
    "createdAt": "2022-12-20T16:15:04.270741",
    "lastModifiedAt": "2022-12-23T16:15:04.270741"
  }
}
```
#### **10. 댓글  삭제 - DELETE /api/v1/posts/{postId}/comments/{id}**
- 리턴 (JSON 형식)
```json
{
  "resultCode": "SUCCESS",
  "result":{
    "message": "댓글 삭제 완료",
    "id": 4
  }
}
```
#### **11. 댓글 목록 조회 - DELETE /api/v1/posts/{postId}/comments/{id}**
- 리턴 (JSON 형식)
```json
{
  "resultCode": "SUCCESS",
  "result":{
    "content":[
      {
        "id": 3,
        "comment": "comment test3",
        "userName": "test",
        "postId": 2,
        "createdAt": "2022-12-20T16:07:25.699346"
      },
      {
        "id": 2,
        "comment": "comment test2",
        "userName": "test",
        "postId": 2,
        "createdAt": "2022-12-20T16:03:30.670768"
      }
    ],
    "pageable":{"sort":{"empty": false, "sorted": true, "unsorted": false },
      "offset": 0,…},
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 10,
    "number": 0,
    "sort":{
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
  }
}
```
#### **12. 좋아요 누르기 - POST  /api/v1/posts/{postId}/likes**
- 리턴 (JSON 형식)
```json
{
	"resultCode":"SUCCESS",
    "result": "좋아요를 눌렀습니다."
}
```
#### **13. 좋아요 수 count - GET  /api/v1/posts/{postId}/likes**
- 리턴 (JSON 형식)
```json
{
  "resultCode":"SUCCESS",
  "result": 0
}
```
#### **14. 마이피드 조회 - GET  /api/v1/posts/my**
- 리턴 (JSON 형식)
```json
{
  "resultCode": "SUCCESS",
  "result":{
    "content":[
      {
        "id": 4,
        "title": "test",
        "body": "body",
        "userName": "test",
        "createdAt": "2022-12-16T16:50:37.515952"
      }
    ],
    "pageable":{
      "sort":{"empty": true, "sorted": false, "unsorted": true }, "offset": 0,…},
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "sort":{
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
  }
```
#### **15. 알림 목록 조회 - GET  /api/v1/alarms**
- 리턴 (JSON 형식)
```json
{
	"resultCode":"SUCCESS",
  "result": {
	"content":
	[
		{
	      "id": 1,
	      "alarmType": "NEW_LIKE_ON_POST",
        "fromUserId": 1,
        "targetId": 1,
	      "text": "new like!",
	      "createdAt": "2022-12-25T14:53:28.209+00:00",
	  }
	]
	}
}
```