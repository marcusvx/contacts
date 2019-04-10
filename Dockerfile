FROM postgres:latest
ADD /scripts/startup.sql /docker-entrypoint-initdb.d/

FROM openjdk:8-jdk-alpine as BUILD

COPY . /src
WORKDIR /src
RUN ./gradlew --no-daemon shadowJar

FROM openjdk:8-jre-alpine

COPY --from=BUILD /src/build/libs/contacts.jar /bin/contacts/app.jar
WORKDIR /bin/contacts

CMD ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7001","-jar","app.jar"]