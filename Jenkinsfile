
pipeline {
    agent any
    environment {
        // registry = 'hub.docker.com'
        credential = 'nemo_dockerhub'
        imageName = 'parakarock/soap_service_demo'
        version_tag = 'latest'
    }
    stages {
      
        stage('Build soap_demo') {
            steps {
                // Get some code from a GitHub repository
                git credentialsId: 'git_credential_soap_demo', url: 'https://github.com/parakarock/soap_service_demo'
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
                // sh "docker build -t soap_service_demo:latest -f Dockerfile ."
                script {
                    docker.withRegistry('', "${env.credential}") {
                    image = docker.build("" + "" + "${env.imageName}" + ":"+"${env.version_tag}", "-f ./Dockerfile .")
                }
                docker.withRegistry('', "${env.credential}") {
                    image.push()
                }
                }
                
                // sh "nohup java -jar target/*.jar &"
          
            }

            // post {
            //     // If Maven was able to run the tests, even if some of the test
            //     // failed, record the test results and archive the jar file.
                
            //     success {
            //         archiveArtifacts 'target/*.jar'
            //     }
            // }
        }
    //     stage('Build soap_demo_client') {
    //         steps {
    //             // Get some code from a GitHub repository
    //             git credentialsId: 'git_credential_soap_demo', url: 'https://github.com/parakarock/soap_service_demo_client.git'
    //             // Run Maven on a Unix agent.
    //             sh "mvn -Dmaven.test.failure.ignore=true clean package"
    //             sh "docker build -t soap_service_demo_client:latest -f Dockerfile ."
    //             // sh "nohup java -jar target/*.jar &"
          
    //         }

    //         // post {
    //         //     // If Maven was able to run the tests, even if some of the test
    //         //     // failed, record the test results and archive the jar file.
                
    //         //     success {
    //         //         archiveArtifacts 'target/*.jar'
    //         //     }
    //         // }
    //     }
        
    
    // stage('Automate Test') {
    //     steps {
    //         git credentialsId: '402f609b-1df3-4acb-9836-d32f68d758df', url: 'https://github.com/parakarock/soap_service_demo_cypress.git'
            
    //         sh "npm install"
    //         sh "envsubst < docker-compose_demo.yml > docker-compose.yml"
    //         sh "docker-compose up -d"
    //         sh "npm run test:demo"
    //         sh "docker-compose down"
    //     }
        
    //     // post {
    //     //         // If Maven was able to run the tests, even if some of the test
    //     //         // failed, record the test results and archive the jar file.
                
    //     //         success {
    //     //             archiveArtifacts 'cypress/videos/*.spec.js.mp4'
    //     //         }
    //     //     }
        
    // }
    
    //  stage('Deploy') {
    //      steps {
    //         sh "docker run -d --rm -p 8082:8082 --network=soap_service soap_service_demo:latest"
    //         sh "docker run -d --rm -p 8081:8081 --env ENDPOINT_SERVICE_COURSE=http://172.18.0.2:8082/ws --network=soap_service soap_service_demo_client:latest"
    //     }
    //  }
     stage('Deploy') {
        agent {
            label "mac"
        }
         steps {
             sh "scp -i ~/Documents/virtualbox/jenkins/.vagrant/machines/default/virtualbox/private_key vagrant@192.168.56.101:/var/lib/jenkins/workspace/soap_service_dockerhub/soap_service_demo_deployment.yml ."
             sh "scp -i ~/Documents/virtualbox/jenkins/.vagrant/machines/default/virtualbox/private_key vagrant@192.168.56.101:/var/lib/jenkins/workspace/soap_service_dockerhub/soap_service_demo_service.yml ."
            // git credentialsId: 'git_credential_soap_demo', url: 'https://github.com/parakarock/soap_service_demo'
            script {
                sh "cd ~/Documents/kubernetes/jenkins-k8s"
                sh "envsubst < soap_service_demo_deployment.yml > deployment.yml"
                    try{
                    sh "kubectl apply -f deployment.yml"
                    sh "kubectl apply -f soap_service_demo_service.yml"
                        }catch(error){
                    sh "kubectl create -f deployment.yml"
                    sh "kubectl create -f soap_service_demo_service.yml"
                }        
            }
        }
     }

    
        
    }  
}


