# cdk8s란?
cdk8s는 객체 지향 익숙한 프로그래밍 언어를 사용하여 Kubernetes 객체 yaml을 생성하기 위한 프레임워크입니다. cdk8s는 순수한 Kubernetes YAML을 생성합니다. cdk8s를 사용하여 어디에서나 실행되는 모든 Kubernetes 클러스터에 대한 애플리케이션을 정의할 수 있습니다.

블로그 글: [cdk8s란 무엇인가요?](https://peterica.tistory.com/523)

# CDK8S 특징
* 친숙한 프로그래밍 언어로 쿠버네티스 애플리케이션을 정의할 수 있습니다.   
  * JavaScript, TypeScript. Python, Java를 지원하면 Go, .NET과 같은 언어들을 지원할 예정입니다.  
* 쿠버네티스 애플리케이션을 코드로 정의하고 그것들을 npm, maven과 같은 코드 라이브러리로 공유하고 재사용이 가능합니다.  
  * 라이브러리는 템플릿보다 업데이트하고 유지보수를 하기가 쉽습니다.  
  * 여러분은 이런 라이브러리를 통해서 사용자가 어떻게 쿠버네티스 애플리케이션들을 정의하는지 표준화할 수 있습니다.  
* 애플리케이션을 개발하는 언어와 같은 툴을 사용하여 CDK8s 코드를 정의하고 빌드할 수 있습니다.  
  * CDK8s를 GitOps 같은 배포 방법론과 함께 사용하면 그것들을 코드로 관리하고 CI/CD 파이프라인을 통해서 여러분의 쿠버네티스 클러스터에 배포가 가능합니다.
* CDK8s로 만들어진 쿠버네티스 매니페스트 파일들은 어디서든 실행 가능합니다.
  * 클라우드 환경 혹은 온 프레미스 등 어디서나 실행되는 쿠버네티스 클러스터에 대한 애플리케이션 정의가 가능하기 때문에 표준화의 도구로도 사용이 가능합니다.

# 사용 느낌
자바코드를 사용하는 사람이라면 접근성은 어렵지 않았다.    
kubectl의 자동완성기능을 사용하듯 객체와 빌더의 조합으로 코드를 이어나가는 방식이었다.
Helm 같은 도구는 value.yaml에 파라메터값을 주면 원하는 인프라자원을 얻을 수 있어서 편리했는데,   
cdk8s는 프로그래밍 언어로 사용한다는 점에서 매력적이었고,  
더욱이 kubecli sdk를 이용하여 시스템을 모니터링하고  
때에 따라 YAML을 생성하는 기능을 만들 때 유용할 것 같다.