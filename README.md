# 특화 PJT

## 📢 Ground Rule

---

🕘 매주 스크럼 일정 : 월~ 금 아침 미팅 끝난 후 

🙂 Rules 안지키면 뚝배기

👄 질문에 대한 답변은 발표자

## 프로젝트 일정 및 아이디어

---

[일정](https://www.notion.so/9c1d9bbaa66a4eb4968776198f8a3277)

[아이디어 기획](https://www.notion.so/4d4c7ec501d3401491365efc8a23fa36)

[Rules](https://www.notion.so/Rules-18a0b82cadf24c29ae9af2d61337867a)

[화면 설계](https://www.notion.so/cb773810b64a46159cbe5dde950c72f9)

[DB설계](https://www.notion.so/DB-d43c12f7360e4b398ca41345f16af140)

## **아이디어 회의**

### 📍1인가구를 위한 맞춤형 매물 추천 서비스

## 📜프로젝트 명

나혼자 살거다

### **배경**

1인가구의 비중이 20년 만에 2배 넘게 증가.

[[그래픽뉴스] '2030' 1인가구](https://m.yonhapnewstv.co.kr/news/MYH20210803016000038)

증가하는 1인가구에 맞는 서비스를 제공하고자 사용자에게 맞는 위치, 매물을 추천해주자!.
## 🛠Stack
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> <img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/gitlab-FCA121?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=aws&logoColor=white"> <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">

### **제공 기능**

- 사용자가 원하는 매물을 직접 확인
- 원하는 매물을 찜하기 등록
- 사용자의 정보, 찜한 매물을 정보를 보기
- 나에게 맞는 위치 및 매물 추천
    - 중요하게 생각하는 시설 을 기반으로
- 매물과 관련된 부동산 정보 전달

### 🎬**기능흐름**

1. 질문이나 검색 버튼을 이용하여 이동
    - 질문 :  여러가지 질문을 통해 사용자가 원하는 기준을 저장
    - 기준을 통한 가중치 계산
    - map을 이용하여 원하는 동의 매물들 보임

    - 검색 : 사용자 필터링으로 원하는 기준을 선택
    - 기준을 통한 가중치 계산
    - map을 이용하여 원하는 동의 매물들 보임
2. 원하는 동을 클릭 혹은 확대
3. 매물들의 리스트들을 출력
    1. 원하는 매물의 상세정보를 확인
    2. 원하는 매물 찜하기
4. 자신이 선택한 동과 비슷한 기준을 가진 다른 동들을 추천
5. 프로필 페이지에서 찜했던 매물들 확인

### **예상되는 리스크**

- 데이터의 양이 많아 서버의 과부하 문제
- 거리, 건물의 수를 이용하여 가중치를 계산하는 알고리즘의 신뢰성 문제

**시장조사**

- 직방,다방 여러 부동상 플랫폼이 존재 하지만 사용자 맞춤형 추천 서비스는 없다,

[부동산 필수 앱 다방](https://www.dabangapp.com/)

[No.1 부동산 앱, 직방](https://www.zigbang.com/)

### **실현가능성**

플랫폼 제작에는 문제가 없으나

데이터를 가공하고 저장, 저장된 데이터를 이용하여 가중치는 계산하는 부분에서 어려움을 겪을것 이라 예상.

### **FeedBack**

- 데이터 업데이트, 유지보수 관련
- 매물등록할때 계산해서 DB에 들어가야함

## 👨‍💻Specification

> 프로젝트의 명세!

### 배포 환경

- __URL__ : 
✔️ http://3.38.97.229/
✔️ http://j5d106.p.ssafy.io/
- __배포 여부__ : ⭕️
- __접속 가능__ : ⭕️
- __HTTPS 적용__ : ING~
  <br>

### 개발 환경
> Front-end 
<br>
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> <img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"><br>

- Framework : Vue.js
- 지원 환경 : Web / Mobile / Web App / Native App
- 담당자 : 강민주, 윤경한

> Back-end
<br>
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=aws&logoColor=white"> <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">


- Framework : Spring boot / Node.js / Docker 
- Database : MySQL 
- 담당자 : 배태호, 변준형, 봉대현

### 🎨화면설계

1. 사용자

![Untitled](img/main.png)

![Untitled](img/login.png)

![Untitled](img/mypage.png)

![Untitled](img/register.png)

![Untitled](img/zzim.png)

2. 지도검색

![Untitled](img/map1.png)

![Untitled](img/map2.png)

![Untitled](img/map3.png)

![Untitled](img/map4.png)

![Untitled](img/map5.png)

![Untitled](img/map6.png)

![Untitled](img/map7.png)

![Untitled](img/map8.png)

![Untitled](img/map9.png)

![Untitled](img/map10.png)


3. 질문

![Untitled](img/q1.png)

![Untitled](img/q2.png)

![Untitled](img/q3.png)

![Untitled](img/q4.png)

![Untitled](img/q5.png)

### 👨‍💻DB설계

![db설계](https://user-images.githubusercontent.com/77487962/131943812-a5914a16-c85f-48ca-889b-a7d04dd0d216.PNG)



## 🤭Rules (꼭 지킵시다!!)
```
협업을 위해 개발에 필요한 여러 규칙들을 정의합니다
```

### GIT 규칙

- 작업단위로 브랜치 생성하여 작업하기
- 브랜치 구조 : master - develop(한개) - 기능별 브랜치 이름(역할 분배 된 대로)
- 브랜치명 규칙 : **#jira-number_branchName**
- Merge 규칙 : 명세서 기반, 지라 기준 한개의 작업 또는 한개의 부작업 구현 완료시 Merge
    - **반드시 다른 사람에게 코드 리뷰를 받은 후 merge** 할 것
- Commit 규칙 : 수정 시 Modify 기능명, 추가 시 Add 기능명, 삭제시 Delete 기능명 등 앞에 확실히 알 수 있는 단어 붙이기
    - COMMIT END

        <타입> 리스트

        - feat : 기능 (새로운 기능)
        - fix : 버그 (버그 수정)
        - refactor: 리팩토링
        - build : 빌드 시스템 수정, 외부 종속 라이브러리 수정 (npm, gulp 등)
        - style : 스타일 (코드 형식, 세미콜론 추가: 비즈니스 로직에 변경 없음)
        - docs : 문서 (문서 추가, 수정, 삭제)
        - test : 테스트 (테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경 없음)
        - perf : 성능 개선

    제목 첫 글자를 대문자로

    제목은 명령문으로

    제목 끝에 마침표(.) 금지

    제목과 본문을 한 줄 띄워 분리하기

    본문은 "어떻게" 보다 "무엇을", "왜"를 설명한다.

    본문에 여러줄의 메시지를 작성할 땐 "-"로 구분

### JIRA 규칙

- 큰틀은 BE, FE로 구분
- 명세서 기준 스토리는 분류, 작업은 제목, 부작업은 명세의 기능으로 구성한다
- 작업은 작업을 포함하는 스토리를 이슈에 연결하여 생성
- 부작업은 작업에서 더많은 조치에서 생성
- 담당자와 스토리포인트 설정하기
- 스프린트에서 상태 변경하기

### 파일명 및 변수명 규칙

- vue 관련 파일명 : 소문자로만 구성, 띄어쓰기시 - 로 구분
- Spring 관련 파일 : 파스칼 표기법, 카멜 표기법과 동일하지만 첫 글자를 대문자로 표기 ex) PascalCase
- 메소드의 이름은 대소문자 혼용 가능하지만 반드시 동사를 사용하여 소문자로 시작 ex) checkRange()
- 그 외 일반적인 변수 : 카멜 표기법, 각 단어의 첫글자는 대문자로 하되, 첫 글자만 소문자로 표기 ex) camelCase
- 엔티티의 멤버 변수 이름은 DB에 있는 변수명을 기본
- is 접두사를 통해 boolean변수를 표기한다 ex) 로그인이 되었는지 체크하는 변수 : isLogin
- n 접두사를 통해 객체의 개수를 나타내는 변수를 표기한다 ex) nUser
- No 접미사를 통해 엔티티 번호를 나타낸다 ex) userNo, coferenceNo
- 상수의 경우 대문자로 표기한다 ex) 회원의 등급 : USER_GUEST, USER_ADMIN
- 최대한 직관적으로 작성 ex) ~num, ~cnt,
