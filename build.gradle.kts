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
    implementation("org.apache.commons:commons-lang3:3.10")
    implementation("org.apache.commons:commons-collections4:4.4")

    annotationProcessor("org.projectlombok:lombok:1.18.12")

    compileOnly("org.projectlombok:lombok:1.18.12")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.1")
    testImplementation("org.assertj:assertj-core:3.16.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testAnnotationProcessor("org.projectlombok:lombok:1.18.12")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
