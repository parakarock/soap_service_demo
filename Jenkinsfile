
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
             
            git credentialsId: 'git_credential_soap_demo', url: 'https://github.com/parakarock/soap_service_demo'
            script {
                    withKubeConfig([credentialsId: '402f609b-1df3-4acb-9836-d32f68d758df',
                    caCertificate: '',
                    serverUrl: 'https://hub.docker.com/repository/docker/parakarock/soap_service_demo',
                    contextName: '',
                    clusterName: '',
                    namespace: 'jenkins'
                    ]) {
                        try{
                    sh "kubectl apply -f ."
                        }catch(error){
                    sh "kubectl create -f ."
                }
                    }
                
            }
        }
     }

    
        
    }  
}


