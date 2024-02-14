package com.peterica.app.composition;

import imports.k8s.*;
import org.cdk8s.Names;
import software.constructs.Construct;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * cdk8s 공통 인수, prop 상속받는 코딩 연습용.
 * 레퍼런스: https://github.com/cdk8s-team/cdk8s-examples/java/cdk8s-composition
 */

public class WebService extends Construct {

    public WebService(final Construct scope, final String id){
        this(scope, id, null);
    }

    public WebService(final Construct scope, final String id, final WebServiceProps props){
        super(scope, id);

        Map<String, String> label = Collections.singletonMap("app", Names.toLabelValue(this));

        new KubeService(this, "service", KubeServiceProps.builder()
                .spec(ServiceSpec.builder()
                        .type("LoadBalancer")
                        .ports(List.of(ServicePort.builder()
                                .port(props.getPort())
                                .targetPort(IntOrString.fromNumber(props.getContainerPort()))
                                .build()))
                        .selector(label)
                        .build())
                .build());

        new KubeDeployment(this, "deployment", KubeDeploymentProps.builder()
                .spec(DeploymentSpec.builder()
                        .replicas(props.getReplicas())
                        .selector(LabelSelector.builder()
                                .matchLabels(label)
                                .build())
                        .template(PodTemplateSpec.builder()
                                .metadata(ObjectMeta.builder()
                                        .labels(label)
                                        .build())
                                .spec(PodSpec.builder()
                                        .containers(List.of(Container.builder()
                                                        .name("web")
                                                        .image(props.getImage())
                                                        .ports(List.of(ContainerPort.builder()
                                                                        .containerPort(props.getContainerPort())
                                                                .build()))
                                                .build()))
                                        .build())
                                .build())
                        .build())
                .build());
    }
}
