/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'maven:3.9.6-amazoncorretto-8-debian' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn compile'
                sh 'mvn package'
                sh 'ls'
                sh 'java -jar target/messageApp-0.1.0.jar'
            }
        }
    }
}