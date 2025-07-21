pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9'  // Must match name in Jenkins > Global Tool Configuration
    }

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Cross-Browser Tests') {
            steps {
                echo "Running Cross-Browser Tests with TestNG XML"
                    bat 'mvn clean test'
              
            }
        }

        stage('Publish Test Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'test-output/reports/chrome',
                    reportFiles: 'index.html',
                    reportName: 'Chrome Browser Report',
                    allowMissing: true,
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
                publishHTML(target: [
                    reportDir: 'test-output/reports/edge',
                    reportFiles: 'index.html',
                    reportName: 'Edge Browser Report',
                    allowMissing: true,
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
        failure {
            echo 'Build failed. Please check errors above.'
        }
    }
}
