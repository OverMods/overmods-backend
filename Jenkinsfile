node {
    def app

    stage('Clone') {
        checkout scm
    }

    stage('Build') {
        withEnv(['DOCKER_BUILDKIT=1']) {
            app = docker.build('mykola2312/overmods-backend')
        }
    }

    stage('Push') {
        docker.withRegistry('https://registry.hub.docker.com', 'a2aa5264-dce1-4054-8828-8db95e3c6c3c') {
            app.push('v0.1.0')
        }
    }

    stage('Rollout') {
        sh('k3s kubectl apply -f k8s/')
        sh('k3s kubectl rollout restart deployment backend -n overmods')
    }
}
