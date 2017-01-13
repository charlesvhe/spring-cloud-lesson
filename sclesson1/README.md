### 第1课
微服务的核心是服务的注册于发现

第一步先开启eureka 8100 访问http://localhost:8100/ 查看注册中心状态

开启provider 再次查看注册中心状态

开启consumer 这次我们不用ip端口访问provider服务了，可以直接使用服务名即可（注意在Application类中声明的@LoadBalanced RestTemplate）http://localhost:8120/consumer

然后我们来看一下微服务的正统用法Feign http://localhost:8120/consumer/feign

思考：consumer工程里面的provider包的内容可以由服务提供者提供API jar包给消费者调用，feign接口的方法签名跟提供者的实现类一致，可以在provider端直接实现接口，移除所有restful相关代码（需要修正spring不支持方法参数注解继承的bug，参见framework相关代码），“服务”更加纯粹。API包还可以引入Swagger自动产生文档。

开启provider集群，测试Ribbon重试策略，详细讲解hystrix作用。

开启zuul，测试http://localhost:8080/provider/user 路径中用服务名调用(注释Application类中preFilter postFilter)

测试zuul权限

开放访问 http://localhost:8080/provider/privilege/pb

授权访问 http://localhost:8080/provider/privilege/pt/1 delete方法 header Authorization: abc123

加密结果 http://localhost:8080/provider/privilege/pv/1 header Authorization: abc123

扩展：压测zuul，验证200并发

######hystrix config oauth2 高级研发单独培训

