@Library('my-shared-lib') _
pipeline {
    agent any

    tools {
        maven 'maven3.9.12'
        jdk 'java17'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/kavyadevops4/Java-Maven-Project.git'
                
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
                        withSonarQubeEnv('sonarqube') {
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






