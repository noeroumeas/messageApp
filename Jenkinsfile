/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'maven:3.6.3-jdk-8' } }
    stages {
        stage('SCM') {
            steps {
                checkout scm
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

/* pipeline {
    agent { docker { image 'maven:3.9.6-eclipse-temurin-8-alpine' } }
    stages {
        stage('SCM') {
            steps {
                checkout scm
            }
        }
        stage('build') {
            steps {
                sh 'ls a'
                sh 'mvn compile'
                sh 'mvn package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    def mvn = tool '/usr/share/maven';
                    withSonarQubeEnv() {
                        sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=messageApp"
                    }
                }
            }
        }
    }
} */