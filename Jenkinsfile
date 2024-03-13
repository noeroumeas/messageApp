/* Requires the Docker Pipeline plugin */
node {
    agent { docker { image 'maven:3.9.6-amazoncorretto-8-debian' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn compile'
                sh 'mvn package'
            }
        }
    }
    stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=messageApp"
    }
  }
}