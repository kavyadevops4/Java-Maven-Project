@Library('my-shared-lib') _
pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'java17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/yourusername/simple-java-app.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Parallel Stage') {
            parallel {

                stage('Unit Test') {
                    steps {
                        sh 'echo Running unit tests...'
                    }
                }

                stage('SonarQube Scan') {
                    steps {
                        withSonarQubeEnv('SonarQube') {
                            sh 'mvn sonar:sonar'
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Post-Build') {
            steps {
                echo "Build completed successfully."
            }
        }
    }
}


