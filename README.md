# spring-boot-grace-shutdown

## 此脚手架基于SpringBoot-2.0.5.RELEASE版本开发,1.5.X版本未测试

## 使用方法

* 1 . clone-Git-Master,Maven打包到本地仓库或者发布到私服中
* 2 . POM文件增加依赖

```xml
<dependency>
    <groupId>pro.chenggang</groupId>
    <artifactId>spring-boot-grace-shutdown</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

* 3 . 在启动类上增加注解`@EnableGraceShutdownEndpoint`
* 4 . 在配置文件中增加相关配置

```yaml
management:
    shutdown:
        enabled: true #是否开启优雅停机
        shutdown-time: 30 #强制停机时间(默认60)
        shutdown-time-unit: SECONDS   #强制停机时间单位(默认SECONDS)
        health-wait-time: 10 #关闭前等待健康监测时间(默认10),即在请求shutdown接口时,会延时这个时间进行Shutdown操作
        health-wait-time-unit: SECONDS   #关闭前等待健康监测时间单位(默认SECONDS),即在请求shutdown接口时,会延时这个时间进行Shutdown操作
```

* 5 . 应用启动后请求接口`POST:/actuator/shutdown`即可
* 6 . `ShutdownJob`接口提供一个方法,供在容器关闭前执行特定业务逻辑,可自定义实现类,并配置成Bean,在容器关闭前会自动加载执行