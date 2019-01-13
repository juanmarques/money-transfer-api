FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/money-transfer-api*.jar money-transfer-api.jar
CMD java ${JAVA_OPTS} -jar money-transfer-api.jar