pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        // stage('Unit Tests') {
        //     steps {
        //         sh "mvn clean test"
        //     }
        // }
        // stage('Publish Test Results') {
        //     steps {
        //         junit 'target/surefire-reports/*.xml'
        //     }
        // }
        // stage('Package') {
        //     steps {
        //         sh "mvn clean package"
        //     }
        // }
        // stage('Upload Artificat') {
        //     steps {
        //         sh "mvn clean deploy -s settings.xml"
        //     }
        // }
        stage('deploy') {
            steps {
                sh '''
                    set -e
                    # Variables
                    JAR_NAME=java-kubernetes-1.0.0.jar
                    TARGET_DIR=/home/vivek/javajar
                    SERVICE_NAME=java-kubernete
                    BACKUP_DATE=$(date +%Y-%m-%d_%H%M)
                    if sudo test -f "${TARGET_DIR}/${JAR_NAME}"; then
                        sudo mv "${TARGET_DIR}/${JAR_NAME}" "${TARGET_DIR}/${JAR_NAME%.jar}_${BACKUP_DATE}.jar"
                    fi
                    # Download the JAR
                    curl -u admin:Nandhu1234@ "http://34.56.145.239:8082/repository/maven-release/com/example/java-kubernetes/1.0.0/${JAR_NAME}" --output ${JAR_NAME}
            
                    # Ensure /opt/java-kubernetes exists
                    sudo mkdir -p ${TARGET_DIR}
            
                    # Copy the JAR to the service directory
                    sudo cp ${JAR_NAME} ${TARGET_DIR}/
            
                    # Set ownership to jenkins (or appropriate user)
                    sudo chown jenkins:jenkins ${TARGET_DIR}/${JAR_NAME}
            
                    # Restart the systemd service
                    sudo systemctl daemon-reload
                    sudo systemctl restart ${SERVICE_NAME}
            
                    # Check status
                    sudo systemctl status ${SERVICE_NAME} --no-pager

                '''  
            }
        }
    }
}
