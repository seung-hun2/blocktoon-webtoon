# BlockToon Webtoon
[BlockToon Organization 주소](https://github.com/N0T-BAD)

MSA 프로젝트로 6개의 서비스로 구성되어있는데 그 중 웹툰 서비스입니다. 해당 링크를 접속하시면 자세한 정보와 세부 내용을 보실 수 있습니다.

클라이언트가 웹툰을 볼 수 있는 환경과 동시에 웹툰을 생성할 수 있는 기능이 있습니다. 해당 서비스에서는 조회기능과 생성 기능을 구현하였습니다. 
웹툰 서비스에는 요일/장르별 조회, 베스트 조회, 검색, 메인페이지 조회, 웹툰/회차 생성요청, 요청 조회, (웹툰/회차 생성 요청) 승인 or 반려 기능이 있습니다. 



### 환경 변수
연결을 위한 환경 변수 세팅입니다.
<table>
  <tr>
    <td><b>environment</b></td>
    <td><b>description</b></td>
  </tr>
  <tr>
    <td><b>{GCP_BUCKETKEY_JSON}</b></td>
    <td><b>GCP bucket 등록을 위한 private key를 .json 형식으로 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DB_MASTER_URL}</b></td>
    <td><b>MY SQL MASTER DB URL을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DB_SLAVE_URL}</b></td>
    <td><b>MY SQL SLAVE DB URL을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DB_USERNAME}</b></td>
    <td><b>MY SQL DB USERNAME을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DB_PASSWORD}</b></td>
    <td><b>MY SQL DB PASSWORD를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{GCP_PROJECT_ID}</b></td>
    <td><b>GCP PROJECT ID를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{EUREKA_DEFAULT_ZONE}</b></td>
    <td><b>EUREKA DEFAULT ZONE을 입력해주세요</b></td>
  </tr>
  
  <tr>
    <td><b>{KAFKA_BOOTSTRAP_ADDRESS}}</b></td>
    <td><b>Kafka bootstrap 주소를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DOCKERHUB_USERNAME}</b></td>
    <td><b>DOCKERHUB 명을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DOCKERHUB_PASSWORD}</b></td>
    <td><b>DOCKERHUB 비밀번호를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{VM_INSTANCE_HOST}</b></td>
    <td><b>VM 인스턴스 Host를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{SSH_USERNAME}</b></td>
    <td><b>SSH USERNAME을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{SSH_PRIVATE_KEY}</b></td>
    <td><b>SSH PRIVATE KEY를 입력해주세요</b></td>
  </tr>
</table>

* 환경 변수를 바탕으로 application.yml 파일을 생성합니다.

```yml
server:
  port: 8084
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${eureka.client.service-url.defaultZone}

spring:
  application:
    name: webtoon-service
    
  datasource:
    master:
      driver-class-name: com.mysql.cj.jdbc.Driver
      jdbc-url: ${spring.datasource.master.jdbc-url}
      username: ${spring.datasource.master.username}
      password: ${spring.datasource.master.password}

    slave:
      driver-class-name: com.mysql.cj.jdbc.Driver
      jdbc-url: ${spring.datasource.slave.jdbc-url}
      username: ${spring.datasource.slave.username}
      password: ${spring.datasource.slave.password}

  kafka:
    bootstrapAddress: ${spring.kafka.bootstrapAddress}
    viewTopic: viewCount
    interestTopic: interestCount
    ratingTopic: rating
    viewGroup: viewCountGroup
    interestGroup: interestCountGroup
    ratingGroup: ratingGroup

  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true
    hibernate:
      ddl-auto: update
    show-sql: true

  cloud:
    gcp:
      storage:
        credentials:
          location: classpath:gcpkey.json
        project-id: ${spring.cloud.gcp.storage.credentials.project.id}

  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB


```
* 위의 과정을 마치고 프로그램을 실행합니다.

<br>

<br>
