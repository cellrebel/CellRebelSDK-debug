node {
    
    def gradleHome = tool 'GRADLE-6'

        stage('Preparation') {
        checkout([
                $class: 'GitSCM',
                branches: [[name: 'artifactory-jenkins']],
                doGenerateSubmoduleConfigurations: false,
                extensions: [],
                submoduleCfg: [],
                userRemoteConfigs: [[
                        credentialsId: 'vova-github',
                        url: 'https://github.com/cellrebel/CellRebelAndroid.git'
                ]]
        ])
    }
    
        stage('Build & Publish App to Artifactory') {
            
        rtGradleResolver(
            id: 'cellrebel-gradle-resolver',
            serverId: 'cellrebel-artifactory-prod'
        )
        
        rtGradleDeployer(
            id: 'cellrebel-gradle-deployer',
            serverId: 'cellrebel-artifactory-prod',
        )
        
        rtGradleRun(
            tool: 'GRADLE-6',
            usesPlugin: true,
            useWrapper: false,
            rootDir: 'sdk/',
            buildFile: 'build.gradle',
            resolverId: 'cellrebel-gradle-resolver',
            deployerId: 'cellrebel-gradle-deployer',
            tasks: 'clean assemble artifactoryPublish'
        )
        
    }
    
}
