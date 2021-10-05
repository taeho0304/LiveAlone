pipeline {
        agent none
        //gitlab 소스 체크 후 다운
        options { skipDefaultCheckout(false) }
        stages {
                stage('Docker build') {
                        agent any
                        steps {
                                sh 'docker build -t front ./frontend'
                                sh 'docker build -t back ./backend'
                        }
                }
                stage('Docker run') {
                        agent any
                        steps {

                                sh 'docker ps -f name=front -q \
                                        | xargs --no-run-if-empty docker container stop'

                                sh 'docker ps -f name=back -q \
                                        | xargs --no-run-if-empty docker container stop'

                                sh 'docker container ls -a -f name=front -q \
                                        | xargs -r docker container rm'

                                sh 'docker container ls -a -f name=back -q \
                                        | xargs -r docker container rm'
                                sh 'docker images -f "dangling=true" -q \
                                        | xargs -r docker rmi'

                                sh 'docker run -d --name front -p 80:80 -p 443:443 -v /home/ubuntu/cert:/usr/share/nginx/html/homepage/cert -v /home/ubuntu/images:/images --network alone front'
                                sh 'docker run -d --name back -p 8080:8080 -v /home/ubuntu/images:/images --network alone back'
                        }
                }
        }
}
