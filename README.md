# SPRING Cloud Stream Test 

spring cloud stream을 이용한 kafka consuming, producing 테스트 프로젝트 (공부할 목적) <br/><br/>

<br/><br/>


<h2>환경</h2>

- docker
- docker-compose
- java 9
- spring boot 2.0.1.RELEASE (webflux, spring-cloud-stream)
- kafka 1.1.0

<br/><br/>



<h2>환경 셋팅</h2>

$ docker-compose up -d

$ docker exec -it kafka-test /opt/kafka/bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic test-input

$ docker exec -it kafka-test /opt/kafka/bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic test-output

<br/><br/>



<h2>빌드 및 실행</h2>

$ gradle runApplication 

<br/><br/>


<h2>설명</h2>
API로 받은 로그성 데이터를 kafka output 토픽에 producing

input 토픽 리스너가 메시지 consuming.
API 호출하여 kafka output 토픽에 producing 하고 SINK.