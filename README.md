# SPRING Cloud Stream Test 

spring cloud stream을 이용한 kafka consuming, producing 테스트 프로젝트 (공부할 목적)


환경

- docker
- docker-compose
- java 9
- spring boot 2.0.1.RELEASE
- kafka 1.1.0





환경 셋팅

$ docker-compose up -d
$ docker exec -it kafka-test /opt/kafka/bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic test-input
$ docker exec -it kafka-test /opt/kafka/bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic test-output





빌드 및 실행

$ gradle runApplication 




설명

