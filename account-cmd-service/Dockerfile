FROM openjdk:16-alpine3.13
ADD target/account-cmd-service.jar account-cmd-service.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/account-cmd-service.jar"]
