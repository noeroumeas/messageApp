/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'maven:3.9.6-amazoncorretto-8-debian' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn compile'
                sh 'mvn package'
            }
        }
    }
}