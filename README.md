<div align="center">
<h1> <a href="https://antique-almandine-653.notion.site/Mutsa-SNS-eed83a8aef6c4f20b26b76ed4a3b6e8b"> Mutsa - SNS π¬</a></h1>
</div>

### λ©μ¬μ€λ€μ€(mustsa-SNS) κ°μΈ νλ‘μ νΈ
****
`mutsaSNS(λ©μ¬μ€λ€μ€) νλ‘μ νΈ`λ λ‘κ·ΈμΈ, κΈμ°κΈ°, μμ , μ­μ , νΌλκΈ°λ₯(νμ΄μ§), λκΈ, μ’μμ, μλ¦Ό κΈ°λ₯μ κ°μ§κ³  μλ snsμλλ€.

<br>

### π» κ°λ°νκ²½
***
- **μλν°** : Intellij Ultimate
- **κ°λ° ν΄** : SpringBoot 2.7.5
- **μλ°** : JAVA 11
- **λΉλ** : Gradle 6.8
- **μλ²** : AWS EC2
- **λ°°ν¬** : Docker
- **λ°μ΄ν°λ² μ΄μ€** : MySql 8.0
- **νμ λΌμ΄λΈλ¬λ¦¬** : SpringBoot Web, MySQL, Spring Data JPA, Lombok, Spring Security

<br>

### π νλ‘μ νΈλ₯Ό μ§ννλ©΄μ λλ μ 
*** 
#### 1μ£Όμ°¨ λ―Έμ μν μ
- νλ‘μ νΈμμ `Test μ½λ μμ±`μ μ°μ΅,μ μ©νλ €κ³  λΈλ ₯νμκ³  νμ μΈμ¦, μΈκ° κΈ°λ₯μ Admin κΈ°λ₯μ μΆκ°ν λ νμ€νΈ μ½λλ₯Ό λλ €λ³΄λ©΄μ  `unit νμ€νΈ`λ₯Ό ν  μ μμ΄μ swaggerμμ νμ€νΈνλ μ€ν μκ°μ μ€μΌ μ μμκΊΌ κ°μ΅λλ€.
- GitLabμ ν΅ν΄μ `CI/CDλ₯Ό μ μ©`νμ¬ νλ‘μ νΈλ₯Ό μ§ννμ΅λλ€. `λ°°ν¬ μ€ν¬λ¦½νΈ`λ₯Ό λ§λ€κ³  μ μ©νλλ° μ΄λ €μμ κ²ͺκΈ΄ νμ§λ§  μμ μ¬ν­μ΄ μκΈΈλλ§λ€ λ°°ν¬ μμμ μν΄λ λμ λ°λ³΅ μμμ μμ¨  μ μμμ΅λλ€.
- `λ³΄μν΄μΌ ν  μ `
  1. log μ°μ΄μ νμΈνλ μ΅κ΄
  2. μ£Όμ μ²λ¦¬(ν νλ‘μ νΈ μ§νμ μ½λ μ€λͺ)

#### 2μ£Όμ°¨ λ―Έμ μν μ
- νμκ³Ό μ½λλ¦¬λ·°λ₯Ό ν΅ν΄μ λ¦¬λ·° λ°μλ λΆλΆμΈ λ©μλμ΄λ¦μ λͺννκ² μ°κ³   `controller`μμ μ½λλ₯Ό μ΅λν μ€μ΄κ³  `service`μμ ν΄κ²°μ νλλ‘ νμ¬ μ½λ κ°λμ±μ λμ΄λ €κ³  λΈλ ₯νμμ΅λλ€.
- `soft delete`λ₯Ό μ¬μ©ν΄μ μ€λ¬΄μμ μ°λ κ±°μ²λΌ DBμμ μμ  μ­μ λ₯Ό λ°©μ§ν  μ μμ΄μ ν₯λ―Έλ‘μ λ€. 
- `cascade`/`orphanRemoval`μ μ μ©νμ¬ FK μ μ©λ λ°μ΄ν° μ­μ  μ²λ¦¬λ₯Ό μ μ©ν΄λ΄μ μλ‘μ΄ λΆλΆμ κ³΅λΆνκ² λμ΄μ μ’μμ΅λλ€.
- `λ³΄μν΄μΌν  μ `
  1. spring-security testμ λν΄μ κ³΅λΆ


<br>

### πμ²΄ν¬λ¦¬μ€νΈ
****
####  AWS EC2μ Docker λ°°ν¬
- [x] AWS EC2 t3.small μΈμ€ν΄μ€ μμ±
- [x] μΈμ€ν΄μ€μ Docker μ€μΉ
####  [Gitlab CI & Crontab CD](https://velog.io/@gmeoq/series/GitLAb)
- [x] GitLabμ μ νλ‘μ νΈ λ§λ€κΈ°
- [x] Dockerfile μμ±
- [x] νμ΄νλΌμΈ μμ±
- [x] Dockerμ pull,run
- [x] λ°°ν¬ μ€ν¬λ¦½νΈ λ§λ€κΈ°
- [x] Crontab μ¬μ©
#### [Swagger](http://ec2-3-34-130-47.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/#/)
- [x] [Swagger μμ‘΄μ± μΆκ°](https://velog.io/@gmeoq/SpringBoot-Swagger-%EC%A0%81%EC%9A%A9)
- [x] .yml νμΌ μμ 
- [x] SwaggerConfiguration μμ±
- [x] [swaggerConfig Custom](https://velog.io/@gmeoq/swagger-custom%ED%95%98%EA%B8%B0)

<br>

### π κΈ°λ₯ μ μμ
*******
#### νμ μΈμ¦,μΈκ°
- μ μ μ΄λ¦κ³Ό λΉλ°λ²νΈλ₯Ό κΈ°μνκ³  νμκ°μ , μ μ μ΄λ¦ μ€λ³΅μ custom exception λ°μ
- λ‘κ·ΈμΈμ, token λ°ν
#### κ²μλ¬Ό λ±λ‘ ,μμ , μ­μ , 1κ°μ‘°ν, λ¦¬μ€νΈ
- λ‘κ·ΈμΈ ν, κ²μλ¬Ό λ±λ‘/μμ /μ­μ  κ°λ₯
- λΉνμμ΄κ±°λ λ‘κ·ΈμΈμ μν κ²½μ°μλ κ²μλ¬Ό 1κ° μ‘°ν, λ¦¬μ€νΈ μ‘°ν κΈ°λ₯μ μ¬μ© κ°λ₯ν¨
- κ²μλ¬Ό λ±λ‘μ 300μ μ΄λ΄λ‘λ§ κ°λ₯
- κ²μλ¬Ό μμ , μ­μ  κΆνμ λ³ΈμΈμ κ²μλ¬ΌμΌ λλ§ κ°λ₯
- κ²μλ¬Ό λ¦¬μ€νΈλ ν νμ΄μ§μ 20κ°μ©λ§ λ³΄μ΄λ³΄λ‘ νμ
#### λκΈ λ±λ‘, μμ , μ­μ , λ¦¬μ€νΈ
- λ‘κ·ΈμΈ ν, λκΈ λ±λ‘/μμ /μ­μ /λͺ©λ‘ κ°λ₯
- λκΈ λ±λ‘μ 100μ μ΄λ΄λ‘λ§ κ°λ₯ 
- λκΈ μμ , μ­μ  κΆνμ λ³ΈμΈμ΄ μμ±ν λκΈμΌ κ²½μ°μλ§ κ°λ₯
#### λ§μ΄νΌλ
- λ΄κ° μμ±ν κΈλ§ λ³΄μ΄λ κΈ°λ₯
- λͺ©λ‘ κΈ°λ₯ νμ΄μ§ μ²λ¦¬, νΌλ κ°―μ 20κ°
#### μ’μμ
- λ‘κ·ΈμΈ ν , μ’μμ λλ₯Όμ μλ κΈ°λ₯
- μ€λ³΅μΌλ‘ μ’μμ λλ₯΄λ κ²½μ°, custom exception λ°μ
#### μλ λͺ©λ‘ μ‘°ν
- λ€λ₯Έ νμμ΄ μ’μμλ₯Ό λλ₯΄κ±°λ λκΈμ μμ±ν  μ κ²μλ¬Ό μμ±μκ° μλλͺ©λ‘μ νμΈν  μ μλ κΈ°λ₯
- μλ μ‘°νλ νμλ§ κ°λ₯

<br>

### π ERD Diagram
***
![λ©μ¬_erd](https://user-images.githubusercontent.com/114658792/211472194-72023b5e-b7da-4c0a-ba61-d323662c77d6.png)


<br>

### π‘ EndPoint 
***

| **no** | **κ΅¬λΆ** | **κΈ°λ₯** | **Method** | **URI** |
| --- | --- | --- | --- | --- |
| 1 | νμ | νμκ°μ | POST  | /api/v1/users/join |
| 2 |  | λ‘κ·ΈμΈ | POST  | /api/v1/users/login |
| 3 | ν¬μ€νΈ | κ²μλ¬Ό λ±λ‘ | POST  | /api/v1/posts |
| 4 |  | κ²μλ¬Ό μ­μ  | DELETE | /api/v1/posts/{id} |
| 5 |  | κ²μλ¬Ό μμ  | PUT | /api/v1/posts/{id} |
| 6 |  | κ²μλ¬Ό 1κ° μ‘°ν | GET | /api/v1/posts/{id} |
| 7 |  | κ²μλ¬Ό λͺ©λ‘ μ‘°ν | GET |  /api/v1/posts |
| 8 | λκΈ | λκΈ λ±λ‘ | POST  | /api/v1/posts/{postsId}/comments |
| 9 |  | λκΈ μ­μ  | DELETE | /api/v1/posts/{postsId}/comments/{id} |
| 10 |  | λκΈ μμ  | PUT | /api/v1/posts/{postId}/comments/{id} |
| 11 |  | λκΈ λͺ©λ‘ μ‘°ν | GET | /api/v1/posts/{postId}/comments |
| 12 | μ’μμ | μ’μμ λλ₯΄κΈ° | POST  | /api/v1/posts/{postId}/likes |
| 13 |  | μ’μμ  μΉ΄μ΄νΈ | GET | /api/v1/posts/{postsId}/likes |
| 14 | λ§μ΄νΌλ | λ§μ΄νΌλ μ‘°ν | GET | /api/v1/posts/my |
| 15 | μλ¦Ό | μλ¦Ό μ‘°ν | GET |  /api/v1/alarms |

<br>

### π² URI μλ ₯/ λ°νκ°
******
#### **1. νμκ°μ - POST /api/v1/users/join**

- μλ ₯
```json
  "password": "1234",
  "userName": "gh"
```
- κ²°κ³Ό
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "id": 9,
    "userName": "gh"
  }
}

```
#### **2. λ‘κ·ΈμΈ - POST /api/v1/users/login**
- μλ ₯
```json
  "password": "1234",
  "userName": "gh"
```
- κ²°κ³Ό
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImdoIiwiaWF0IjoxNjcyMDE3MjM4LCJleHAiOjE2NzIwMzUyMzh9.PcSlsqu0jNvf67laXgD9WdK0Gv0NMWAy18tfNkr5DTE"
  }
}
```
#### **3. ν¬μ€νΈ μμ± - POST /api/v1/posts**
- μλ ₯
```json
{
  "body": "hi. it's me",
  "title": "hi"
}
```
- κ²°κ³Ό
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "postId": 18,
    "message": "ν¬μ€νΈ λ±λ‘ μλ£"
  }
}
```
#### **4. ν¬μ€νΈ μμ  - PUT /api/v1/posts/{id}**
- μλ ₯ (λ§€κ°λ³μλ‘ postId)
```json
{
  "body": "hello. it's me",
  "title": "hello"
}
```
- κ²°κ³Ό
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "postId": 18,
    "message": "ν¬μ€νΈ μμ  μλ£"
  }
}
```
#### **5. ν¬μ€νΈ μ­μ  - DELETE /api/v1/posts/{postId}**
- μλ ₯(λ§€κ°λ³μλ‘ postID)
- κ²°κ³Ό
```json
{
  "resultCode": "SUCCESS",
  "result": {
    "postId": 18,
    "message": "ν¬μ€νΈ μ­μ  μλ£"
  }
}
```
#### **6. ν¬μ€νΈ 1κ° μ‘°ν(μμΈ) - GET /api/v1/posts/{postId}**
- μλ ₯(λ§€κ°λ³μλ‘ postID)
- κ²°κ³Ό
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
#### **7. ν¬μ€νΈ λ¦¬μ€νΈ μ‘°ν - GET /api/v1/posts**
- κ²°κ³Ό
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
#### **8. λκΈ μμ± - POST /api/v1/posts/{postsId}/comments**
- μλ ₯νΌ (JSON νμ)
```json
{
	"comment" : "comment test4"
}
```
- λ¦¬ν΄ (JSON νμ)
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

#### **9. λκΈ  μμ  - PUT /api/v1/posts/{postId}/comments/{id}**
- μλ ₯νΌ (JSON νμ)
```json
{
  "comment" : "modify comment"
}
```
- λ¦¬ν΄ (JSON νμ)
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
#### **10. λκΈ  μ­μ  - DELETE /api/v1/posts/{postId}/comments/{id}**
- λ¦¬ν΄ (JSON νμ)
```json
{
  "resultCode": "SUCCESS",
  "result":{
    "message": "λκΈ μ­μ  μλ£",
    "id": 4
  }
}
```
#### **11. λκΈ λͺ©λ‘ μ‘°ν - GET /api/v1/posts/{postId}/comments/{id}**
- λ¦¬ν΄ (JSON νμ)
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
      "offset": 0,β¦},
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
#### **12. μ’μμ λλ₯΄κΈ° - POST  /api/v1/posts/{postId}/likes**
- λ¦¬ν΄ (JSON νμ)
```json
{
	"resultCode":"SUCCESS",
    "result": "μ’μμλ₯Ό λλ μ΅λλ€."
}
```
#### **13. μ’μμ μ count - GET  /api/v1/posts/{postId}/likes**
- λ¦¬ν΄ (JSON νμ)
```json
{
  "resultCode":"SUCCESS",
  "result": 0
}
```
#### **14. λ§μ΄νΌλ μ‘°ν - GET  /api/v1/posts/my**
- λ¦¬ν΄ (JSON νμ)
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
      "sort":{"empty": true, "sorted": false, "unsorted": true }, "offset": 0,β¦},
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
#### **15. μλ¦Ό λͺ©λ‘ μ‘°ν - GET  /api/v1/alarms**
- λ¦¬ν΄ (JSON νμ)
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