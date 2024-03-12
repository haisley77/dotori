# 🎈 도토리 DO our sTORI [SSAFY 공통 프로젝트 우수상]
![메인페이지GIF](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/92483fab-b966-4e0e-a5bd-a65c198a3ca2)

# 🎈 서비스 개요

### ✏ 서비스 설명

도토리는 아이들을 위한 동화책 연극 서비스입니다. 아이들이 직접 원하는 동화를 고르고, 친구들을 모아 동화 속 캐릭터에 맞는 3D 아바타가 적용된 모습으로 연극할 수 있으며, 진행한 연극을 하나의 영상으로 받아볼 수 있습니다.  

### ✏ 타겟 

- 독서를 지루해하고 어려워하는 아이들
- 또래 친구들과 함께 하며 자신의 끼를 뽐내고 싶은 아이들
- 즐겁게 책을 읽고 싶은 아이들

### ✏ 기획 배경 및 기대 효과

- 어휘력 및 문해력 저하 사회적 이슈 해결
    - 짧고 자극적인 컨텐츠에 익숙해진 아이들에게, 친숙한 디지털 기기를 활용해 자연스럽게 독서를 접할 수 있는 플랫폼을 제공하여 최근 심각하게 보도되는 사회적 문제 해결에 도움이 될 수 있습니다.
- 창의력 및 문제 해결 능력 증진
    - 연극을 진행하며, 간접적으로 독서를 진행해 동화의 교훈을 체득하고 실제 문제 상황에 직면했을 때 창의적으로 대처할 수 있는 능력이 향상될 수 있습니다.
- 독서 집중력 및 몰입도 향상, 공감지수(EQ; Emotional Quotient) 발달
    - 사용자의 얼굴 랜드마크를 인식해 적용되는 3D 아바타 모델은 아이들에게 실제 동화 속 캐릭터가 된 듯한 느낌을 주어, 단순히 책을 읽을 때에는 이해하기 어려운 등장 캐릭터의 세세한 감정과 생각을 느낄 수 있도록 돕습니다.

# 🎈 프로젝트 산출물

### ✏ 요구사항 정의서

- 아이디어 기획 및 구체화 단계에서 요구사항 정의
- 우선순위 지정 후 기능 확정

![Untitled](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/baa15d84-8c3e-438a-b522-547d2a22a012)

### ✏ Wireframe

- Figma 활용 UI/UX 디자인 진행
- 기능 develop

![Untitled (1)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/3d1b45e2-b9ed-471f-bca0-64fa9606d6ec)

### ✏ ER-Diagram

- ERD Cloud 활용

![Untitled (2)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/bd8d19a1-e5e9-4860-8743-5e01c772bcbe)

### ✏ System Architecture

- Jenkins CI pipeline
- Nginx
- Docker

![Untitled (3)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/2cfb1093-09a2-405a-a56b-1fa93a16566b)

# 🎈 프로젝트 진행

### ✏ Notion

- 프로젝트 관련 학습 내용과 논의 내용을 notion 에 작성하여 문서 관리

![Untitled (4)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/3bbbcccf-ca69-49d4-998c-0ecc9f9defeb)

![Untitled (5)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/b3afecd2-62e2-4c7c-aeec-3f55025c2507)

### ✏ Jira

- 프로젝트 일정 관리
- 이슈 발행 및 담당자 지정
    - 매주 스프린트 시작 전 백로그에 이슈 등록
    - 소요 시간 별 스토리 포인트 지정
    - 하위 작업 등록을 통한 세부 일정 관리
- 스프린트 번다운 차트 활용

![Untitled (6)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/7379640f-c2f9-4e79-8f61-6c7e570d6aa8)

### ✏ Git

- 팀 Notion에 Git 행동 강령과 Commit convention 작성, 공유

![Untitled (8)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/c730e5c1-4e6b-4f38-84cd-8568e5222d17)

- 작업 브랜치 구성

```
master - frontend - feature - 기능 or 페이지 
       - backend - feature - 기능
``` 

# 🎈 서비스 화면 및 기능 소개

### ✏ 메인 페이지

- 시작하기 버튼을 통한 페이지 이동

![메인페이지GIF (1)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/5679a915-a3b7-4aec-8e96-d7acd9999f24)

### ✏ 로그인 페이지
- 소셜 로그인을 통한 간편 회원가입

![로그인페이지](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/70099240-1bc2-41b2-93d4-1027bcf879a0)

### ✏ 마이페이지-내 정보
- 회원 정보 확인 가능
- 프로필 이미지와 닉네임 변경

![마이페이지내정보](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/560a6287-07fb-4abb-b397-6a2f7adde956)

### ✏ 마이페이지-보관함
- 연극 후 녹화 완료된 영상 미리보기 및 다운로드

![마이페이지보관함](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/07f9b25e-5f91-4cb2-b2e4-d10c7dfed3b6)

### ✏ 책 리스트 페이지
- 연극 가능한 동화책 목록 조회
- 방 만들기 버튼을 통해 방 생성 모달 확인 가능

![44](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/bf24d940-56d6-4067-b65b-fcc209b6bbb4)

### ✏ 방 목록 페이지
- 참여 인원 별 참여 가능 여부 태그를 통해 확인 가능
- 입장하기 버튼을 통해 비밀번호 검증 후 연극 참여 가능
- 방 제목/책 제목으로 검색 가능

![방목록페이지](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/bcf60992-9239-4c3b-af44-4580267e2d21)

### ✏ 방 생성 페이지
- 선택한 동화책 세부 정보(줄거리, 역할, 3D 아바타) 조회
- 방 제목, 비밀번호 설정을 통한 공개/비공개 방 생성

![모달](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/01848e29-d45d-4aec-8638-fe979d76c818)

### ✏ 대기방(방장 화면)
- 참여자 화면 실시간 동기화 및 역할 선택 중복 방지
- 채팅을 통한 참여자 알림 및 실시간 소통
- 시작 버튼을 통한 연극방 이동

![대기방입장GIF](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/6f46dabc-b1b9-4f5f-b2f7-20ddadf1be67)

### ✏ 대기방(참여자 화면)
- 역할 선택 후 준비 버튼을 통해 준비 상태 알림

![대기방참여자시점 PNG](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/90c4bf85-a9e2-46f9-8f6b-4ded913371ff)

### ✏ 연극방 
- 동화책 씬 별 화면 녹화 기능 및 역할 별 권한 제한
- 참여자 화면 동기화

![연극방GIF](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/e2b5f759-1781-460d-9be9-04b59976a711)

### ✏ 연극 종료

![마지막페이지](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/0ddb000b-ef8a-400a-a08d-57ce250a9909)

# 🎈 기술 스택

### ✏ 사용 기술

![Untitled (9)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/7bb50f03-e189-4093-a7aa-6dea145c6140)

### ✏ Version

|Teck stack ||version|
|------|---|---|
|Frontend|Vue|3.4.14|
||Node|20.10.0|
||npm|10.2.3|
||Quasar CLI|2.3.0|
||Vite|5.0.12|
|Backend|SpringBoot |2.7.17|
||Openvidu |2.29.0|
||JavaCV |1.5.10|
|Database|MySQL|8.0.32|
||Redis||
|Infra|Docker||
||Nginx||
||AWS EC2||

# 🎈 화상 연결 기술

### ✏ WebRTC(Web Real-Time Communication)

WebRTC는 웹 브라우저간에 실시간 음성, 영상, 데이터 교환을 위한 기술입니다. WebRTC는 P2P(Peer-to-Peer) 기술을 이용한 통신입니다. 서로 다른 네트워크에 있는 Peer끼리 음성, 영상, 데이터를 교환할 수 있습니다.

### ✏ WebSocket

WebSocket은 양방향 통신을 제공하는 웹 프로토콜로, 클라이언트와 서버 간 실시간 데이터 전송을 지원합니다. 실시간 채팅, 게임, 주식 시세 업데이트 등에 널리 사용됩니다. 지속적인 연결을 통해 양방향 통신을 가능하게 하며, 이로써 실시간 데이터의 송수신이 가능합니다. TCP를 기반으로 신뢰성 있는 연결을 제공합니다.

### ✏ OpenVidu

OpenVidu는 오픈소스 프로젝트로서, WebRTC를 기반으로 하는 온라인 비디오 커뮤니케이션을 쉽게 구축할 수 있는 플랫폼입니다. 오픈비두는 주로 Kurento 미디어 서버를 기반으로 작동하며, WebRTC기술을 사용하여 브라우저 간에 실시간으로 오디오, 비디오 및 데이터를 전송할 수 있도록 해줍니다. OpenVidu는 사용자의 인증, 인가는 지원하지 않기 때문에 스프링 서버에서 인증, 인가 절차를 거친 후 서비스를 제공했습니다.

![Untitled (10)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/083f6812-83eb-462f-93fa-9523f6f84bae)

### ✏ 3D 모델(아바타)

Blender를 활용하여 3D 아바타를 제작하고 ShapeKey를 설정하여 형태를 변형시켰습니다. MediaPipe를 활용하여 사용자 얼굴의 형태 변화를 감지하고 Three.js를 활용하여 3D모델을 웹 애플리케이션에 렌더링했습니다. 감지된 변화를 3D 모델의 ShapeKey와 매핑시켜 사용자의 표정 변화에 아바타가 반응하도록 하였습니다.

### ✏ Blender(Shape Key)

Blender을 활용하여 다양한 3D 아바타를 편집하였습니다. Blender의 ShapeKey는 모델의 형태를 조정하는 기능입니다. 이를 활용하여 특정 상태로 모델의 형태를 변형시킬 수 있습니다.

![Untitled (11)](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/cdbf88b4-ac17-4f70-83cd-ec5cafd99a33)

### ✏ Media Pipe

MediaPipe Face Landmarker을 활용하여 이미지와 비디오에서 얼굴 랜드마크와 표정을 감지할 수 있습니다. 이를 활용하여 아이들의 얼굴 표정을 식별하고, 얼굴 필터와 효과를 적용하거나 가상 아바타를 만들 수 있습니다.

![미디어파이프](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/8ff48a7f-88a5-4f79-ad67-ce2521562b4e)

### ✏ Three.js

Three.js는 3D 그래픽을  JavaScript로 웹 브라우저에 생성하기 위한 오픈 소스 JavaScript 라이브러리입니다. WebGL을 기반으로 하며, WebGL은 웹 브라우저에서 3D 그래픽을 렌더링하기 위한 API입니다.

![blue_rat_thumbnail](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/1b82abfe-154b-4034-bca0-f063b9db1945)

![model_blue](https://github.com/Youth787/SSAFY_Algorithm_Study/assets/90955152/4ab84b6f-77c1-4386-8cfb-496b9464c86f)


---

# 🎈 프로젝트 후기

#### 조석현  
처음으로 프로젝트의 팀장을 맡아 진행을 해보았습니다. 팀장으로서 보다 팀원 간의 소통을 적극적으로 이어주지 못한 점이 많이 아쉬웠고, 제 부족함을 알 수 있었습니다. <br>
일정 관리와 명확한 역할 배분을 보다 잘 해냈더라면 더욱 완성도 높은 프로젝트가 될 수 있었을 것 같습니다.<br>
맡은 바 최선을 다해준 팀원들에게 감사하고, 다음 프로젝트에서는 이번 프로젝트에서 배운 것들을 양분 삼아 발전할 수 있도록 해야하겠습니다.

#### 양진우  
처음으로 다뤄보는 기술들인 WebRTC, WebSocket, OpenVidu, 그리고 Three.js를 활용한 프로젝트였습니다. <br>
초기에는 이러한 새로운 기술들을 다루는 데 어려움을 겪었지만, 그 과정에서 많은 것을 배우고 성장할 수 있는 기회가 되었습니다. <br>
프로젝트를 6주 안에 완료할 수 있을지에 대한 걱정도 있었지만, 우리 팀은 적극적으로 협력하여 목표를 성공적으로 달성할 수 있었습니다. <br>
많은 인원이 참여한 프로젝트였기 때문에 Git의 사용법에 대해 깊이 이해하고 활용할 수 있는 기회가 되었습니다. 이번 경험을 토대로 앞으로도 더 나은 개발자로 성장하고자 합니다.

#### 정연미  
사회적 이슈가 되는 부분을 찾아 문제점을 개선할 수 있는 방향으로 주제를 선정했습니다. <br>
'아이들'을 위해 제작한 서비스답게 디자인적으로나 기획적으로 귀여운 요소가 많아 더욱 흥미롭게 프로젝트를 진행해 나갈 수 있었습니다. <br>
관련 경험이 부족하여 기획 초반에는 구현 가능한 서비스 규모인지, 상상에만 그치는건 아닐지 걱정도 많이 됐지만, 뛰어난 팀원들 덕분에 많은 것을 배우고 한층 성장할 수 있었습니다. <br>
이번 프로젝트를 통해 cs의 중요성을 깨달으며 부족한 개념들과 이론을 꾸준히 학습해서 다음 프로젝트에서는 더 깊이 있게 고민하고 기능을 구현해 나가야겠다는 다짐을 했습니다. 

#### 이하은 
함께 생각하고 공유하고 고민하며 답을 찾는 과정에서 말로만 들어왔던 소통의 중요성을 체감할 수 있었고, <br>
팀원들의 피드백을 통해 내 코드를 돌아볼 수 있는 기회가 되어 감사했습니다. <br>
인원이 많고 시간이 촉박해 진행 상황 공유, 시간 관리에 어려움을 겪었던 것 같아 아쉬운 마음이 있고 
다음 프로젝트에서는 문서화나 스크럼을 통한 현황 공유에 힘 써서 이 점을 보완하고 싶습니다. 


#### 문준형
아이디어 구상단계에서 재미있는 주제를 골라 구현을 위해 모두 열심히 개발했던것같습니다. <br>
6명이라는 많은 인원으로는 프로젝트를 처음해봤기에 코드형상관리부터 스크럼프로세스까지 모든것을 경험해볼수있었습니다. <br>
또한 혼자는 할수없는 큰규모의 프로젝트가 완성되는과정을 직접 겪어볼수있어서 좋았습니다.

#### 김주형
짧다면 짧고 길다면 긴 6주 동안, 같은 목표를 향해 열심히 달렸는데요. <br>
결과물에 아쉬움도 있지만, 필수로 생각했던 기능들은 그래도 완수할 수 있어서 <br>
매우 보람찬 프로젝트였습니다. 쉽지 않은 풀스택 개발 동안 묵묵히 함께 해준 팀원들 감사합니다. <br>
