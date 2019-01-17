package com.jack.winter.config.datasource;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
@Configuration
@MapperScan(basePackages = {"com.jack.winter.rest.dao.tom"}, sqlSessionTemplateRef = "tomSqlSessionTemplate")
public class TomDB {
    private static final Logger logger = LoggerFactory.getLogger(TomDB.class);
    @Bean("tomDataSource")
    public DataSource tomDataSource(TomConfig config) throws SQLException {
        String json= JSONObject.toJSONString(config);
        logger.info("tomConfig：{}",json);
        MysqlXADataSource tomXaDataSource = new MysqlXADataSource();
        tomXaDataSource.setURL(config.getUrl());
        tomXaDataSource.setPassword(config.getPassword());
        tomXaDataSource.setUser(config.getUsername());
        tomXaDataSource.setPinGlobalTxToPhysicalConnection(config.isPinGlobalTxToPhysicalConnection());
        /**
         * 设置分布式-- 体检系统数据源
         */
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(tomXaDataSource);
        xaDataSource.setUniqueResourceName("tomDataSource");
        xaDataSource.setMinPoolSize(config.getMinPoolSize());
        xaDataSource.setMaxPoolSize(config.getMaxPoolSize());
        xaDataSource.setMaxLifetime(config.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(config.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(config.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(config.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(config.getMaxIdleTime());
        xaDataSource.setTestQuery(config.getTestQuery());
        logger.info("Tom数据源注入成功");
        return xaDataSource;
    }

    @Bean("tomSqlSessionTemplate")
    public SqlSessionTemplate tomSqlSessionTemplate(@Qualifier("tomSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
    @Bean("tomSqlSessionFactory")
    public SqlSessionFactory tomSqlSessionFactory(@Qualifier("tomDataSource") DataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/tom/**/*.xml"));
            //开启驼峰命名转换
            bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
