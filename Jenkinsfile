pipeline {
    agent any

    tools {
        maven "maven"
    }

    environment {
        JAR_NAME     = "java-kubernetes-1.0.0.jar"
        TARGET_DIR   = "/home/vivek/javajar"
        SERVICE_NAME = "java-kubernetes"
        BACKUP_DATE  = "${new Date().format('yyyy-MM-dd_HHmm', TimeZone.getTimeZone('IST'))}"
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

        stage('Upload Artifact') {
            steps {
                sh "mvn clean deploy -s settings.xml"
            }
        }

        stage('Deploy') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexuscred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh '''
                        set -e

                        echo "==> Checking if old JAR exists for backup"
                        if sudo test -f "$TARGET_DIR/$JAR_NAME"; then
                            echo "==> Backing up existing JAR"
                            sudo mv "$TARGET_DIR/$JAR_NAME" "$TARGET_DIR/${JAR_NAME%.jar}_$BACKUP_DATE.jar"
                        fi

                        echo "==> Downloading new JAR from Nexus"
                        curl -u $USERNAME:$PASSWORD "http://34.56.145.239:8082/repository/maven-release/com/example/java-kubernetes/1.0.0/$JAR_NAME" --output $JAR_NAME

                        echo "==> Creating target directory if it doesn't exist"
                        sudo mkdir -p $TARGET_DIR

                        echo "==> Copying new JAR to $TARGET_DIR"
                        sudo cp $JAR_NAME $TARGET_DIR/

                        echo "==> Updating ownership"
                        sudo chown jenkins:jenkins $TARGET_DIR/$JAR_NAME

                        echo "==> Restarting systemd service: $SERVICE_NAME"
                        sudo systemctl daemon-reload
                        sudo systemctl restart $SERVICE_NAME

                        echo "==> Verifying service status"
                        sudo systemctl status $SERVICE_NAME --no-pager
                    '''
                }
            }
        }
    }
}
