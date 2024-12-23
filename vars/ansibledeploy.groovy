def call(String dockerRegistry, String dockerImageTag, String helmChartName, String awsCredID, String awsRegion, String eksClusterName, String kubernetesNamespace = 'default') {

    // Determine which values file to use based on the namespace
    if (kubernetesNamespace == "dev") {
        valuesFile = "DEV.yaml"
    } else if (kubernetesNamespace == "uat") {
        valuesFile = "UAT.yaml"
    }

    // Deploy using Helm
    sh "helm upgrade --install ${helmChartName} helm/ --namespace ${kubernetesNamespace} --create-namespace --set image.repository=${dockerRegistry} --set image.tag=${dockerImageTag} -f helm/values/${valuesFile}"
}


