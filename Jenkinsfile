/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'maven:3.9.6-amazoncorretto-8-debian' } }
    stages {
        stage('SCM') {
            steps {
                checkout scm
            }
        }
        stage('build') {
            steps {
                sh 'mvn compile'
                sh 'mvn package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    def mvn = tool 'maven';
                    withSonarQubeEnv() {
                        sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=messageApp"
                    }
                }
            }
        }
    }
}