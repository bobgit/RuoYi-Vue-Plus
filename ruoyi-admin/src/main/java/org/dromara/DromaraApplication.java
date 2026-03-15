package org.dromara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.system.JavaVersion;
import org.springframework.core.SpringVersion;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author Lion Li
 */

@EnableScheduling // 关键：开启定时任务功能
@SpringBootApplication
public class DromaraApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DromaraApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  RuoYi-Vue-Plus启动成功   ლ(´ڡ`ლ)ﾞ"+"Spring Boot Version : " + SpringBootVersion.getVersion()+"Spring Framework Version: " + SpringVersion.getVersion()+"  Java Version: " +JavaVersion.getJavaVersion());
    }

}
