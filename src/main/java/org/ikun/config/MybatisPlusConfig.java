//仰晨study 创建时间2023/6/8 1:06 星期四
package org.ikun.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置mybatisPlus的分页插件
 */
@Slf4j
@Configuration
public class MybatisPlusConfig {

    /**
     * 创建一个mybatisPlus拦截器，在里面添加分页功能----并交给了IOC容器管理
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        log.info("开始启动分页插件");
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
