![cloud](./images/local.png)

- 웹소켓 통신 위해 neovisionaries 라이브러리 사용
- 끊기지 않는 웹소켓 통신 위해서 Publisher-Subscriber 패턴 활용
- 웹소켓 통신에서 receive 메시지 받는 method와 실제로 send 하는 부분이 작동하는 thread 차이 completableFuture.get() 으로 극복
- Kibana 대시보드 활용을 위해 ElasticSearch 사용
- ElasticSearch에 데이터를 보냄과 동시에 DL 예측 모델을 활용한 예상 지표 ElasticSearch index 에 삽입
    
    - Kibana 활용 DashBoard 화
  

![cloud](./images/cloud.png)

- 배포 중 통신이 끊기지 않기 위해 Nginx 사용
- 도커 컨테이너 이미지에 elasticsearch, kibana 사용 및 로컬에서 만든 DL 모델 삽입
- CI/CD 툴로서 Jenkins 를 활용
- GitHub WebHook 으로 push 시 trigger 발생시켜 Jenkins 빌드 자동화
