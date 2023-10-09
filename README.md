# 채용을 위한 웹 서비스 (wanted-pre-onboarding-backend)

## ⌛️ 프로젝트 정보

|    분류     |                                                 내용                                                  |  
|:---------:|:---------------------------------------------------------------------------------------------------:|
|    주제     |                                               채용을 위한 웹 서비스                                              |
|    기간     |                                       2023.10.07 ~      2023.10.09                                 |
| API 문서 링크 | [🔗link](https://documenter.getpostman.com/view/26726157/2s9YJgU1UL) |

<br>

## <span style="color:lightblue"> 🔨 기술 스택

개발 언어 : <img src="https://img.shields.io/badge/JAVA-17-FFFFFF?style=flate&logo=openjdk&logoColor=FFFFFF">
<br>
개발 프레임 워크: <img src="https://img.shields.io/badge/SpringBoot-3.1.1-6DB33F?style=flate&logo=SpringBoot&logoColor=6DB33F">
<img src="https://img.shields.io/badge/junit5-FFFFFF?style=flate&logo=junit5&logoColor=junit5">
<br>
데이터베이스 : <img src="https://img.shields.io/badge/SQLite3-003B57?style=for-the-badge&logo=SQLite&logoColor=white" >
<br>
도구 : <img src="https://img.shields.io/badge/GitHub-181717?style=flate&logo=GitHub&logoColor=white">
<img src="https://img.shields.io/badge/postman-FFFFFF?style=flate&logo=postman&logoColor=postman">
<br>

--- 
## <span style="color:lightblue"> 🔖 Commit Convention

|    Convention     |                                                 내용                                                  |  
|:---------:|:---------------------------------------------------------------------------------------------------:|
|    setting     |                                       프로젝트 환경 설정                                |
|    feat     |                     기능 추가                                                                    |
|    refactor     |                                    새로운 기능이나 버그 수정없이 구현 개선                               |
|    test     |                     테스트 코드 작성                                |
|    fix     |                                       버그 수정                                |
|    docs     |                     문서 수정                                                              |
|    chore     |                     기타 변경 사항                                                                    |


---
### 다이어그램
<img width="334" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/257a0dc3-9763-4bac-a734-9a380220ed55">

--- 
### 💡개발 내용
#### 채용 공고
<details>
<summary> 공고 등록할 수 있습니다. </summary>
<div markdown="1">
<br>
<strong>API: POST /jobs/create</strong>
<br><br>
<ul>
  <img width="609" alt="등록" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/cfd4bb28-3905-4c28-ae54-b85c6358769c"><br>
<li> ❗이 때 , "id", "companyName" 의 정보가 Company DB 에 있는 정보와 상이하다면 등록 불가. </li>
<img width="612" alt="등록예외" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/76425f3b-2c14-497b-a9f8-9ba585fce9c9">
</ul><br><br>
</div>
</details>

<details>
<summary> 공고를 상세 조회할 수 있습니다. </summary>
<div markdown="1">
<br>
<strong>API: GET /jobs/{jobId}</strong>
<br><br>
<ul>
  <img width="612" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/71694c70-fdb5-4c02-ac74-6e9f9a0ff5ed"><br>
<li> ➕ 채용내용, 회사가 올린 다른 채용공고가 추가 포함 됨 </li>
<img width="484" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/99aa6f07-672e-4233-9656-9a57c2bf4973"><br>
<li> ❗일치하는 jobId 가 없을 경우</li>
<img width="607" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/a67e165a-3d07-4b18-a191-30650028abd8"><br><br>
</ul>
</div>
</details>

<details>
<summary> 공고 전체 목록을 조회할 수 있습니다. </summary>
<div markdown="1">
<br>
<strong>API: GET /jobs</strong>
<br><br>
<ul>
 <img width="611" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/1ed6a0bc-bebf-4caa-8de6-1cad08873ee5"><br><br>
</ul>
</div>
</details>

<details>
<summary> 공고를 검색할 수 있습니다. </summary>
<div markdown="1">
<br>
<strong>API: GET /jobs/search?searchWord={searchWord} </strong>
<br><br>
<ul>
  <li> 포지션 : 검색하는 포지션이 검색 키워드와 정확하게 일치해야 목록에 표시 </li>
  <img width="623" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/d1b13f2a-d93b-4bd9-a019-dfa6e6f4bc7c"><br>
  <li> 회사명 : 회사명에 검색 키워드가 포함되어 있다면 목록에 표시 </li>
  <img width="587" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/1291a65d-e45c-41cf-8db4-124609b6096f"><br>
  <li> ❗일치하는 검색 결과가 없을 경우</li>
  <img width="656" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/4b82ceee-a103-46b2-a8ed-053682c0046e">
  <li> ➕ 중복 내용 ( 예를 들어, 포지션 - BE , 회사명 - ..BE.. 가 포함될 때 )을 고려해 Set 사용 </li><br><br>
</ul>
</div>
</details>

<details>
<summary> 공고를 업데이트 할 수 있습니다. </summary>
<div markdown="1">
<br>
<strong>API: PUT /jobs/{jobId}</strong>
<br><br>
<ul>
  <li> 업데이트 전 jobId 1 검색</li>
  <img width="444" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/f6832405-dfeb-4e08-8fde-83ebf137dabc">
  <br><br>

  <li> 업데이트 jobId 1</li>
  <img width="680" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/f6891a61-6eae-4297-a0a2-df14fb67b51e">
  <br><br>

  <li> 업데이트 후 jobId 1 검색</li>
  <img width="679" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/5fc09e71-0c99-4165-8909-8cfe70b9cfd3"><br>

  <li> ❗jobId 와 companyId 가 매칭되지 않을 때 </li>
  <img width="683" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/71ab1920-aeab-4c73-971c-0768febf1f4e"><br>
  
  <li> ➕ @Setter 지양을 위해 Job 클래스에 jobUpdate 를 구현. ( JobId , companyId , companyName 을 제외를 제외한 나머지 모두 변경 가능)</li>
  <img width="349" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/0b76d44d-5ae0-4232-bc8c-4e0e6e7cf7be"><br>

</ul><br>
</div>
</details>

<details>
<summary> 공고 삭제할 수 있습니다. </summary>
<div markdown="1">
<br>
<strong>API: DELETE /jobs/{jobId}</strong>
<br><br>
<ul>
  <img width="675" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/b7849fc2-e9b3-469d-aeb2-218445f49f84">
  <li> 삭제된 jobId 1 </li><br>
  <img width="449" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/73b9cafc-6a1c-46a4-9366-e73bfc5ad86a">
</ul><br><br>
</div>
</details>


#### 사용자
<details>
<summary> 기업 채용공고에 사용자는 지원합니다. </summary>
<div markdown="1">
<br>
<strong>API: POST /users/{userId}/support?jobId={jobId}</strong>
<br><br>
<ul>
  <img width="679" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/006fa548-e042-45a0-b3fc-201152c4506f">
  <li> 지원 정보가 담긴 테이블</li>
  <img width="243" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/9e351fed-5d4f-46df-a931-e55a2367f199">

<li> ❗같은 사용자가 같은 job 에 중복 지원하려 할 때</li>
  <img width="679" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/2c912c24-3967-4932-92eb-60ee4f4f7b56"><br>
<li> ❗userId 를 찾을 수 없을 때</li>
<img width="680" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/005fd199-7d10-44bb-870b-7dc8a8d1e8d0">
<li> ❗jobId 를 찾을 수 없을 때</li>
<img width="682" alt="image" src="https://github.com/walwaljj/wanted-pre-onboarding-backend/assets/108582847/cae93ed8-fd27-4df7-8b83-89a24417dbf4">




</ul><br><br>
</div>
</details>

