def label = "pod-${env.JOB_NAME}-${env.BUILD_NUMBER}".replace('_', '-').replace('/', '-')
podTemplate(label: label, containers: [
    containerTemplate(name: 'maven-docker', image: 'monicashinde3/jnlp-maven-docker', command: 'cat', ttyEnabled: true)],
    volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]
  ) {
    node(label) {
        stage('Build Docker Image'){
            container('maven-docker') {
                if (env.BRANCH_NAME == 'master'){
                    env.ENV="prod"
                }else{
                    env.ENV="dev"
                }
                git 'https://github.com/moshinde/kube-microservices-mongo.git'
                script {
                    dir('user-service'){
                        sh 'mvn clean install -DskipTests=false'
                        user_image=docker.build("monicashinde3/user-service-image:${env.BUILD_NUMBER}")    
                    }
                    dir('stock-service'){
                        sh 'mvn clean install -DskipTests=false'
                        stock_image=docker.build("monicashinde3/stock-service-image:${env.BUILD_NUMBER}")    
                    }
                                    
                }
            }
        }

        stage('Push to Docker hub'){
            container('maven-docker') {
                script {
                    withDockerRegistry([ credentialsId: "docker-hub-user", url: "" ]){
                        user_image.push()
                        stock_image.push()
                    }
                }
            }
        }

        stage('Deploy in container'){
            container('maven-docker') {
                script{
                    sh 'pwd'
                    kubernetesDeploy configs: 'mongo-pod.yml',
                        kubeConfig: [path: ''],
                        kubeconfigId: 'jenkins-service-acct-kubeconfig',
                        enableConfigSubstitution: true

                    dir('user-service'){
                        kubernetesDeploy configs: 'user-deployment.yml',
                            kubeConfig: [path: ''],
                            kubeconfigId: 'jenkins-service-acct-kubeconfig',
                            enableConfigSubstitution: true
                    }
                    dir('stock-service'){
                        kubernetesDeploy configs: 'stock-deployment.yml',
                            kubeConfig: [path: ''],
                            kubeconfigId: 'jenkins-service-acct-kubeconfig',
                            enableConfigSubstitution: true  
                    }
                    
                }
            }
        }
    }

}