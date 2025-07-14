pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('Unit Tests') {
            steps {
                sh "mvn clean test"
            }
        }
        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('Package') {
            steps {
                sh "mvn clean package"
            }
        }
        stage('Upload Artificat') {
            steps {
                sh "mvn clean deploy -s settings.xml"
            }
        }
        stage('deploy') {
            steps {
                sh '''
                cd target
                java -jar java-kubernetes-1.0.0.jar --server.port=8085
                '''  
            }
        }
    }
}
