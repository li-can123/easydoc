//git凭证
def git_auth = "gitee"
//git 的 url
def git_url = "https://gitee.com/a-can-li-can/easydoc.git"
node {
    stage('拉取代码') {
        //checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
        checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
    }
    stage('编译打包') {
      sh "mvn clean package"
    }
    stage('编译打包') {
      sh "mvn clean package dockerfile:build"
    }
    stage('编译打包') {
      sh "docker run -p 8082:8082 ${program_name}"
    }
}