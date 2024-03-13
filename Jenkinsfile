/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'maven:3.9.6-eclipse-temurin-8-alpine' } }
    stages {
        stage('build') {
            steps {
                sh 'ls'
                sh 'java -jar target/messageApp-0.1.0.jar'
            }
        }
    }
}