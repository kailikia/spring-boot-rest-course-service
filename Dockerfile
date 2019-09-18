FROM java:8-jdk-alpine

COPY elearn-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch elearn-0.0.1-SNAPSHOT.jar'

EXPOSE 8080

ENTRYPOINT ["java","-jar","elearn-0.0.1-SNAPSHOT.jar"]