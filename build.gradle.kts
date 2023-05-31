import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.0.5"
  id("io.spring.dependency-management") version "1.1.0"
  //id("org.graalvm.buildtools.native") version "0.9.20"
  kotlin("jvm") version "1.7.22"
  kotlin("plugin.spring") version "1.7.22"
  kotlin("plugin.jpa") version "1.7.22"
}

group = "com.lwsoftware"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
  mavenCentral()
}

/*extra["springCloudGcpVersion"] = "4.1.4"
extra["springCloudVersion"] = "2022.0.2"

dependencyManagement {
  imports {
    mavenBom("com.google.cloud:spring-cloud-gcp-dependencies:${property("springCloudGcpVersion")}")
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}*/

dependencies {
  implementation("com.auth0:java-jwt:4.4.0")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  //implementation("com.google.cloud:spring-cloud-gcp-starter")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-web")


  developmentOnly("org.springframework.boot:spring-boot-devtools")

  //runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
  runtimeOnly("org.hsqldb:hsqldb")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(module = "mockito-core")
    exclude(module = "junit-vintage-engine")
  }
  /*testImplementation("org.springframework.security:spring-security-test") {
    exclude(module = "mockito-core")
  }*/
  testImplementation("com.ninja-squad:springmockk:4.0.2")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
