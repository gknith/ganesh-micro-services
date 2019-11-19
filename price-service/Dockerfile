FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.ganesh.PriceServiceApplicationKt"]
HEALTHCHECK --interval=5m --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:8082/actuator/health/ || exit 1