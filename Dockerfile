FROM openjdk:11-jre-slim
EXPOSE 8080
ADD ./target/ContactsAPI-1.0.0.jar ContactsAPI-1.0.0.jar

ENTRYPOINT ["java","-jar","ContactsAPI-1.0.0.jar"]
