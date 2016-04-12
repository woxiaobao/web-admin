grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    inherits("global") {}
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()

        mavenRepo "http://repo.qiyestore.com/repository/m2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        runtime('mysql:mysql-connector-java:5.1.27') {
            export = false
        }
    }

    plugins {
        build(":release:3.0.1",
              ":rest-client-builder:1.0.3",
              ":tomcat:7.0.55") {
            export = false
        }

        compile(":asset-pipeline:1.9.9") {
            export = false
        }

        compile("com.qiyestore.grails.plugin:openapi-client:0.11",
                ":shiro:1.2.1") {
        }

        runtime(":jquery:1.11.1",
                ":hibernate4:4.3.6.1") {
            export = false
        }

    }
}

grails.project.repos.qiyestoreRepo.url = "http://repo.qiyestore.com/repository/m2/"
grails.project.repos.qiyestoreRepo.type = "maven"
grails.project.repos.qiyestoreRepo.username = "qiyestore"
grails.project.repos.qiyestoreRepo.password = "qiyestore2015"
