package com.fiberhome.filink.demo.common.configuration;

import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 15:01 2020/5/27
 */
@Configuration
public class MongoConfiguration {

    /**
     * MongoDB事务管理器
     *
     * @param factory
     * @return
     */
   /* @Bean
    public MongoTransactionManager transactionManager(MongoDbFactory factory) {
        return new MongoTransactionManager(factory);
    }*/


    /*@Bean
    public MongoTemplate getMongoTemplate(MongoDbFactory dbFactory, MappingMongoConverter converter) {
        return new MongoTemplate(dbFactory, converter);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context, BeanFactory beanFactory,CustomConversions conversions) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        //mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        //去掉默认mapper添加的_class
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        //添加自定义的转换器
        //mappingConverter.setCustomConversions(conversions);
        return mappingConverter;
    }*/

}
