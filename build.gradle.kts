plugins {
    `java-library`
}

repositories {
    jcenter()
}

version = "1.0.0"
group = "com.natera"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation("commons-graph:commons-graph:0.8.1")
    implementation("org.apache.commons:commons-lang3:3.10")
    annotationProcessor("org.projectlombok:lombok:1.18.12")
    compileOnly("org.projectlombok:lombok:1.18.12")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.12")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
