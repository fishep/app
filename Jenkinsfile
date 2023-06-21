pipeline {
// 仅供本地搭建的环境使用
    agent { node { label 'windows' } }
    stages {
        stage('Prepare') {
            steps {
                bat "echo %NODE_NAME%"
                bat "echo branch: ${params.branch}"
                bat "echo profile: ${params.profile}"
            }
        }
        stage('Git Pull') {
            steps {
                git branch: "${params.branch}", url: 'https://github.com/fishep/app.git'
                bat "echo %cd%"
            }
        }
        stage('Maven Build') {
            steps {
                bat "mvn --version"
                bat "mvn clean package -DskipTests -P${params.profile}"
            }
        }
        stage('Docker Build'){
            steps {
                bat "docker --version"
                bat "docker-compose --version"
                bat "docker-compose -f docker-compose-base.yml up"
                bat "docker-compose -f docker-compose.yml up"
            }
        }
    }
}
