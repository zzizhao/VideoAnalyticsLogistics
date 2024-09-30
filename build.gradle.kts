plugins {
    java
    kotlin("jvm") version "1.9.0"  // Add this if you're using Kotlin
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://aws.oss.sonatype.org/content/repositories/snapshots") }
    // CodeGuru-Profiler java agent repository
    maven { url = uri("https://d1osg35nybn3tt.cloudfront.net") }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.google.code.gson:gson:2.10.1")  // Gson = 2.x
    implementation("software.amazon.awssdk:s3:2.20.26")  // AwsJavaSdk-S3 = 2.0
    implementation("software.amazon.awssdk:iotdataplane:2.20.26")  // AwsJavaSdk-IoT = 2.0
    implementation("software.amazon.awssdk:dynamodb-enhanced:2.20.26")  // AwsJavaSdk-DynamoDb-Enhanced = 2.0
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")  // log4j = 2.x
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")  // Log4j-core = 2.x
    implementation("org.apache.logging.log4j:log4j-1.2-api:2.20.0")  // Log4j-1_2-api = 2.x
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")  // Maven-com-amazonaws_aws-lambda-java-core = 1.x
    implementation("com.amazonaws:aws-lambda-java-log4j2:1.5.1")  // Maven-com-amazonaws_aws-lambda-java-log4j2 = 1.6.x
    implementation("org.apache.logging.log4j:log4j-layout-template-json:2.20.0")  // Maven-org-apache-logging-log4j_log4j-layout-template-json = 2.x
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.7.1")  // Jackson-databind = 2.12.x
    implementation("org.apache.commons:commons-collections4:4.4")  // JakartaCommons-collections4 = 4.x
    implementation("software.amazon.awssdk:cloudwatchlogs:2.20.26")  // Maven-software-amazon-cloudwatchlogs_aws-embedded-metrics = 4.x
    implementation("com.jayway.jsonpath:json-path:2.8.0")  // JsonPath = 2.x
    implementation("com.google.auto:auto-common:1.2.1")  // Maven-com-google-auto_auto-common = 1.x
    implementation("software.amazon.awssdk:kinesisvideo:2.20.26")  // AwsJavaSdk-KinesisVideo = 2.0
    implementation("software.amazon.awssdk:sts:2.20.26")  // AwsJavaSdk-Sts = 2.0
    implementation("software.amazon.awssdk:auth-crt:2.20.26")  // AwsJavaSdk-Core-AuthCrt = 2.0
    implementation("software.amazon.awssdk:s3-transfer-manager:2.20.26")  // AwsJavaSdk-S3-TransferManager = 2.0
    implementation("com.google.dagger:dagger:2.48")  // DaggerRuntime = 2.48.x
    implementation("com.google.dagger:dagger-compiler:2.48")  // DaggerBuildTool = 2.48.x
    implementation("org.projectlombok:lombok:1.18.26")  // Lombok = 1.18.x
    annotationProcessor("org.projectlombok:lombok:1.18.26")  // Lombok = 1.18.x
    implementation("com.amazonaws:aws-lambda-java-events:3.11.1")  // Maven-com-amazonaws_aws-lambda-java-events = 3.x
    implementation("org.opensearch.client:opensearch-rest-high-level-client:2.6.0")  // Maven-org-opensearch-client_opensearch-rest-high-level-client = 2.x
    implementation("software.amazon.awssdk:dynamodb-enhanced:2.20.26")  // AwsJavaSdk-DynamoDb-Enhanced = 2.0
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.12.470")  // AWSPersistenceJavaClient = 1.12.x
    implementation("software.amazon.cryptography:aws-database-encryption-sdk-dynamodb:3.1.0")  // Maven-software-amazon-cryptography_aws-database-encryption-sdk-dynamodb = 3.x
    implementation("software.amazon.awssdk:kinesisvideoarchivedmedia:2.20.26")  // AwsJavaSdk-KinesisVideoArchivedMedia = 2.0
    implementation("software.amazon.awssdk:kinesisvideosignaling:2.20.26")  // AwsJavaSdk-KinesisVideoSignaling = 2.0
    implementation("software.amazon.awssdk:url-connection-client:2.20.26")  // AwsJavaSdk-HttpClient-UrlConnectionClient = 2.0
    implementation("com.amazonaws:aws-encryption-sdk-java:2.4.0")  // Maven-com-amazonaws_aws-encryption-sdk-java = 2.x
    implementation("com.networknt:json-schema-validator:1.0.83")  // Maven-com-networknt_json-schema-validator = 1.0.83
    implementation("software.amazon.awssdk:kinesis:2.20.26")  // AwsJavaSdk-Kinesis = 2.0
    implementation("software.amazon.awssdk:sfn:2.20.26")  // AwsJavaSdk-Sfn = 2.0
    implementation("software.amazon.awssdk:kms:2.20.26")  // AWSKeyManagementJavaClient
    implementation("com.amazonaws:aws-java-sdk-core:1.12.470")  // AWSJavaClientRuntime
    implementation("software.amazon.awssdk:apache-client:2.20.26")  // AWS SDK Apache HTTP Client
    implementation("com.google.auto.service:auto-service:1.0.1")  // Related to auto-common
    implementation("org.apache.ant:ant:1.10.12")  // ApacheAnt = 1.10.x
    
    // Test dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")  // JUnit5 = 5.x
    testImplementation("com.github.stefanbirkner:system-rules:1.19.0")  // Maven-com-github-stefanbirkner_system-rules = 1.x
    testImplementation("org.mockito:mockito-core:5.4.0")  // Mockito = 5.x
    testImplementation("com.fasterxml.jackson.core:jackson-core:2.12.7")  // Jackson-core = 2.12.x
    testImplementation("software.amazon.awssdk:dynamodb-enhanced:2.20.26")  // DynamoDbEnhancedLocal = 1.2_1.21.x
    
    // Runtime dependencies
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")  // Log4j-slf4j = 2.x
    // Add FFmpeg dependency
    implementation("org.bytedeco:javacv-platform:1.5.7")  // FFmpeg = 4.x_libx264
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("cleanBuildCache") {
    doLast {
        project.buildDir.deleteRecursively()
        File(System.getProperty("user.home")).resolve(".gradle/caches").deleteRecursively()
        println("Build cache cleaned.")
    }
}