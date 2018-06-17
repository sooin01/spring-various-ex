### spring-board-ex

##### log4jdbc-log4j2 설정

> pom.xml 파일에 추가

```
<dependency>
	<groupId>org.bgee.log4jdbc-log4j2</groupId>
	<artifactId>log4jdbc-log4j2-jdbc4.1</artifactId>
	<version>1.16</version>
</dependency>
```

> database-context.xml 파일에 아래 내용으로 변경

```
<property name="driverClassName" value="net.sf.log4jdbc.sql.jdbcapi.DriverSpy" />
<property name="jdbcUrl" value="jdbc:log4jdbc:mariadb://localhost:3306/test" />
```

> src/main/resources 경로에 log4jdbc.log4j2.properties 파일 생성

```
#log4jdbc.drivers=org.mariadb.jdbc.Driver # mariadb jdbc 일 경우 필요
log4jdbc.spylogdelegator.name=net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator
log4jdbc.dump.sql.maxlinelength=0 # 공백 허용
```

> logback.xml 파일에 추가

```
<logger name="mybatis.sql" level="info" />

<logger name="log4jdbc.log4j2" level="error" additivity="false">
	<appender-ref ref="STDOUT" />
</logger>

<Logger name="jdbc.sqlonly" level="off" />
<Logger name="jdbc.sqltiming" level="info" />
<Logger name="jdbc.audit" level="off" />
<Logger name="jdbc.resultset" level="off" />
<Logger name="jdbc.resultsettable" level="info" />
<Logger name="jdbc.connection" level="off" />
```

> mybatis-config.xml 파일에 interceptor 설정 주석 처리
> mybatis logging을 막기 위한 설정

```
<settings>
	<setting name="logPrefix" value="mybatis.sql." />
</settings>

<!-- <plugins>
	<plugin interceptor="SqlLogInterceptor" />
</plugins> -->
```
