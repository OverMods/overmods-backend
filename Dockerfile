# Build Spring WAR
FROM gradle:8.7.0-jdk17 AS builder

WORKDIR /usr/src/app
COPY src/ ./src/
COPY gradle/ ./gradle/
COPY build.gradle .
COPY settings.gradle .

RUN gradle bootWar

# Serve WAR
FROM tomcat AS final

COPY --from=builder /usr/src/app/build/libs/backend-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war