package com.peterica.app;

import imports.k8s.*;
import software.constructs.Construct;

import org.cdk8s.App;
import org.cdk8s.Chart;
import org.cdk8s.ChartProps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main extends Chart
{

    public Main(final Construct scope, final String id, final String appLabel) {
        this(scope, id, null, appLabel);
    }

    public Main(final Construct scope, final String id, final ChartProps options, final String appLabel) {
        super(scope, id, options);

        Map<String, String> label = Collections.singletonMap("app", appLabel);

        KubeDeployment kubeDeployment = new KubeDeployment(this, "my-deployment", KubeDeploymentProps.builder()
                .spec(DeploymentSpec.builder()
                        .replicas(3)
                        .selector(LabelSelector.builder()
                                .matchLabels(label)
                                .build())
                        .template(PodTemplateSpec.builder()
                                .metadata(ObjectMeta.builder().labels(label)
                                        .build())
                                .spec(PodSpec.builder()
                                        .containers(List.of(Container.builder()
                                                .name("app-container-test")
                                                .image("nginx:1.19.10")
                                                .ports(List.of(ContainerPort.builder()
                                                        .containerPort(80)
                                                        .build()))
                                                .build()))
                                        .build())
                                .build())
                        .build())
                .build());

        // service 생성문 생성
        KubeService kubeService = new KubeService(this, "my-sevice", KubeServiceProps.builder()
                .spec(ServiceSpec.builder()
                        .type("LoadBalancer") // 아직 모를 수 있는데, enum이 있을 줄 알았는데 없어서 아쉬움.
                        .ports(List.of(ServicePort.builder()
                                .port(80)
                                .targetPort(IntOrString.fromNumber(80))
                                .build()))
                        .selector(label)
                        .build())
                .build());
    }

    public static void main(String[] args) {
        final App app = new App();
        new Main(app, "getting-started", "my-app");
        app.synth();
    }
}