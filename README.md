# Artvu Back Batch
아트뷰 배치 저장소

### 사용 기술
**Language** : Java        
**Batch Framework** : Spring Batch        
**DB** : MariaDB, JPA       

### 백엔드 소스
https://github.com/maiorem/artvu-be

### 도입 배경
**문제상황**

- 초기 설계단계에서 일부 관리자 입력값을 제외한 KOPIS 정보는 KOPIS 오픈API를 그대로 활용하여 로그인 유저가 활용하는 정보만 저장하도록 기획되었으나 해당 API를 실시간으로 활용하고 메인페이지의 큐레이팅 된 테마를 감당하기에는 속도가 지나치게 느림.
- 이후 메인 페이지 진입 인원이 늘어날 수록 프론트 서버에 부담이 커질 것으로 판단됨.

**해결**

- Spring Batch 도입을 제안하고 이를 실현하기 위해 Spring Batch 5버전을 학습하고 적용하여 매일 자정에 KOPIS로부터 데이터를 받아 DB에 저장하고 날짜가 지난 공연에 대해 공연종료 처리를 하도록 함.



### 처리 방식
- 매일 자정 공연예술통합전산망(이하 KOPIS)으로부터 공연목록 / 공연상세 / 공연시설상세 정보를 받아 DB에 저장한다.
- 공연 종료일이 오늘 이전인 공연은 공연종료 처리한다.

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FJrIiE%2FbtsIg7ceEYS%2F6Ent0GLmDKK5W9yv1ZQq50%2Fimg.png" />
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcmbNvD%2FbtsIjhqDxw3%2FuyRwfopDoropgRdSeU2htK%2Fimg.png" />
