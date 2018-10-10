# spring-cloud


## 一、erueka 客户端配置


1、Eureka 启禁用

eureka.client.enabled=true

2、Eureka 连接超时时间

eureka.client.eureka-server-connect-timeout-seconds=5

eureka.client.eureka-connection-idle-timeout-seconds=30

3、Eureka Server等待超时时间

eureka.client.eureka-server-read-timeout-seconds=8

4、拉取Eureka注册信息

eureka.client.fetch-registry=true

5、注册自身到Eureka

eureka.client.register-with-eureka=true

6、过滤状态存活的实例

eureka.client.filter-only-up-instances=true

7、注册实例的名称

eureka.instance.appname=unknown

8、健康检查相对路径

eureka.instance.health-check-url-path=/health

9、HOME页面相对路径

eureka.instance.home-page-url-path=/

10、服务续约到期时间

eureka.instance.lease-expiration-duration-in-seconds=90

11、服务续约间隔时间

eureka.instance.lease-renewal-interval-in-seconds=30

12、注册元数据
eureka.instance.metadata-map=

13、通过配置文件找到namespace，忽略springcloud的配置

eureka.instance.namespace=

14、注册是否显示IP地址（第一个非回环地址）

eureka.instance.prefer-ip-address=false

15、注册时使用的IP地址

eureka.instance.ip-address=

16、压缩Eureka Server的数据

eureka.client.g-zip-content=true

17、心跳执行器的线程池初始值

eureka.client.heartbeat-executor-thread-pool-size=2

18、最初复制实例信息到Eureka服务器所需的时间

eureka.client.initial-instance-info-replication-interval-seconds=40

19、同步实例变更信息到Eureka服务到周期

eureka.client.instance-info-replication-interval-seconds=30

20、从Eureka服务器拉取服务信息周期

eureka.client.registry-fetch-interval-seconds=30

21、Eureka Server地址

eureka.client.serviceUrl.defaultZone=xxx,xxx,xxx

## 二、eureka 服务端配置

1、注册实例

eureka.instance.registry.default-open-for-traffic-count=1

eureka.instance.registry.expected-number-of-renews-per-min=1

2、服务面板

eureka.dashborad.enabled=true

eureka.dashboard.path=/

3、自我保护

eureka.server.enable-self-preservation: true

4、限流

eureka.server.rate-limiter-enabled: false

eureka.server.rate-limiter-throttle-standard-clients: false

eureka.server.rate-limiter-burst-size: 10

eureka.server.rate-limiter-full-fetch-average-rate: 100 

eureka.server.rate-limiter-registry-fetch-average-rate: 500

5、相应缓存

eureka.server.response-cache-auto-expiration-in-seconds: 180
eureka.server.response-cache-update-interval-ms: 30000

eureka.server.use-read-only-response-cache: true

6、安全校验

#启用安全校验

security.basic.enabled=true

#授权用户名

security.user.name=root

#授权密码

security.user.password=123456

三、高可用配置

1、节点数据同步

eureka.server.batch-replication: false

eureka.enable-replicated-request-compression: false


