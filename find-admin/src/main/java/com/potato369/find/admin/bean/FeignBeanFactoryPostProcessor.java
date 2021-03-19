package com.potato369.find.admin.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <pre>
 * @PackageName com.potato369.answer.basic.config.processor
 * @ClassName FeignBeanFactoryPostProcessor
 * @Desc 	出现异常：org.springframework.beans.factory.BeanCreationNotAllowedException: Error creating bean with name 'eurekaAutoServiceRegistration': Singleton bean creation not allowed while singletons of this factory are in destruction (Do not request a bean from a BeanFactory in a destroy method implementation!)
 * 		 	参考借鉴解决：https://blog.csdn.net/yudianxiaoxiao/article/details/93674293
 * 		  	问题描述：根本原因是当关闭ApplicationContext时，它会销毁所有单例bean，首先销毁eurekaAutoServiceRegistration，然后是feignContext。当破坏feignContext时，它将关闭与每个FeignClient关联的ApplicationContext。由于eurekaAutoServiceRegistration侦听ContextClosedEvent，因此这些事件将被发送到该bean。不幸的是因为它已被破坏，所以我们得到了上述异常（尝试在破坏中创建bean）。
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2020/11/22 14:11
 * @CreateBy IntelliJ IDEA 2019.3.4
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Component
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (containsBeanDefinition(beanFactory, "feignContext", "eurekaAutoServiceRegistration")) {
            BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
            bd.setDependsOn("eurekaAutoServiceRegistration");
        }
    }

    private boolean containsBeanDefinition(ConfigurableListableBeanFactory beanFactory, String... beans) {
        return Arrays.stream(beans).allMatch(b -> beanFactory.containsBeanDefinition(b));
    }
}
