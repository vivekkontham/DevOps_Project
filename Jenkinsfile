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
    }
}
