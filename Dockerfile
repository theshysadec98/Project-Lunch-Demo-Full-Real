FROM openjdk:11-jre-slim

COPY target/*-included-dependencies.jar /usr/app/lunch.jar

CMD ["java", "-jar", "/usr/app/lunch.jar"]
