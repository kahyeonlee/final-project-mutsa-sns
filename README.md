<div align="center">
<h1> <a href="https://antique-almandine-653.notion.site/Mutsa-SNS-eed83a8aef6c4f20b26b76ed4a3b6e8b"> Mutsa - SNS 💬</a></h1>
</div>

### 🎁 프로젝트를 진행하면서 느낀 점
*** 
- 프로젝트에서 `Test 코드 작성`을 연습,적용하려고 노력하였고 회원 인증, 인가 기능에 Admin 기능을 추가할때 테스트 코드를 돌려보면서  `unit 테스트`를 할 수 있어서 swagger에서 테스트하는 실행 시간을 줄일 수 있다 
- GitLab을 통해서 `CI/CD를 적용`하여 프로젝트를 진행했습니다. `배포 스크립트`를 만들고 적용하는데 어려움을 겪긴 했지만  수정사항이 생길때마다 배포 작업을 안해도 되서 반복 작업을 업앨  수 있었습니다.
- `보완해야 할 점`
  1. log 찍어서 확인하는 습관
  2. 주석 처리(팀 프로젝트 진행시 코드 설명)

### 📌체크리스트
****
#### ✅ AWS EC2에 Docker 배포
1. [x] AWS EC2 t3.small 인스턴스 생성
2. [x] 인스턴스에 Docker 설치
#### ✅ [Gitlab CI & Crontab CD](https://velog.io/@gmeoq/series/GitLAb)
1. [x] GitLab에 새 프로젝트 만들기
2. [x] Dockerfile 생성
3. [x] 파이프라인 생성
4. [x] Docker에 pull,run
5. [x] 배포 스크립트 만들기
6. [x] Crontab 사용
#### ✅ [Swagger 이용](https://velog.io/@gmeoq/SpringBoot-Swagger-%EC%A0%81%EC%9A%A9)
1. [x] Swagger 의존성 추가
2. [x] SwaggerConfiguration 생성
####  ✅ 인증/인가 필터

####  ✅ 게시물 등록, 수정, 삭제, 상세, 리스트

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

### 📉 ERD Diagram
***
![img_2.png](img_2.png)


### 📋 요구 사항 정의서
***
![img_1.png](img_1.png)



### 💡 EndPoint - [Swagger](http://ec2-3-34-178-92.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/#/)
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
