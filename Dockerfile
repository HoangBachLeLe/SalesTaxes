FROM gradle:jdk17-alpine AS BUILD
WORKDIR /sales_taxes_build
COPY . /sales_taxes_build
RUN gradle bootJar

FROM eclipse-temurin:17-jre-alpine
WORKDIR /sales_taxes
COPY --from=BUILD /sales_taxes_build/build/libs/*.jar /sales_taxes/sales_taxes.jar
EXPOSE 8080
CMD java -jar sales_taxes.jar