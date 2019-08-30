package com.kuanggui.EncryptDataSource.config;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.encrypt.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.api.config.encrypt.EncryptRuleConfiguration;
import org.apache.shardingsphere.api.config.encrypt.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.api.config.encrypt.EncryptorRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlEncryptDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataBaseConfig {
    @Primary    // 主数据库
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource (){
        return DataSourceBuilder.create().build() ;
    }

    @Configuration
    @MapperScan(sqlSessionTemplateRef = "primarySqlSessionTemplate",
            basePackages = {"com.kuanggui.EncryptDataSource.mapper.lingyongqian"})
    public class mybatisPrimaryConfig {
        @Bean(name = "mybatisPrimarySqlSessionFactory")
        public SqlSessionFactory primarySqlSessionFactoryBean(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            //mybatis分页
            PageInterceptor pageInterceptor = getPageInterceptor();
            //添加插件
            sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/lingyongqian/*.xml"));

            return sqlSessionFactoryBean.getObject();
        }

        @Bean(name = "primaryTransactionManager")
        public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean(name = "primarySqlSessionTemplate")
        public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("mybatisPrimarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

    }

    private PageInterceptor getPageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties props = new Properties();
        props.setProperty("helperDialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(props);
        return pageInterceptor;
    }

    private static File getFile() {
        return new File(Thread.currentThread().getClass().getResource("/config/encrypt-databases.yaml").getFile());
    }

    //数据脱敏数据库
    @Bean(name = "encryptDataSource")
    public DataSource encryptDataSource () throws SQLException {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//        dataSourceBuilder.url("jdbc:mysql://47.101.33.86:3306/encrypt?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
//        dataSourceBuilder.username("root");
//        dataSourceBuilder.password("1qazxsw2");
//        dataSourceBuilder.type(HikariDataSource.class);
//        DataSource hakariDataSource = dataSourceBuilder.build();
//
//        // 配置脱敏规则
//        Properties props = new Properties();
//        props.setProperty("aes.key.value", "123456");
//        EncryptorRuleConfiguration encryptorConfig = new EncryptorRuleConfiguration("AES", props);
//        EncryptColumnRuleConfiguration columnPhoneConfig = new EncryptColumnRuleConfiguration(null, "phone_encrypt", null, "aes");
//        EncryptColumnRuleConfiguration columnIdNoConfig = new EncryptColumnRuleConfiguration(null, "id_no_encrypt", null, "aes");
//        Map<String, EncryptColumnRuleConfiguration> map = new HashMap<>(2);
//        map.put("phone", columnPhoneConfig);
//        map.put("id_no", columnIdNoConfig);
//        EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(map);
//        EncryptRuleConfiguration encryptRuleConfig = new EncryptRuleConfiguration();
//        encryptRuleConfig.getEncryptors().put("aes", encryptorConfig);
//        encryptRuleConfig.getTables().put("cl_user_base_info_encrypt", tableConfig);
//
//        Properties properties = new Properties();
//        properties.setProperty("query.with.cipher.column", "true");
//        properties.setProperty("sql.show", "true");
//        return EncryptDataSourceFactory.createDataSource(hakariDataSource, encryptRuleConfig, properties);
        return YamlEncryptDataSourceFactory.createDataSource(getFile());
    }

    @Configuration
    @MapperScan(sqlSessionTemplateRef = "encryptSqlSessionTemplate",
            basePackages = {"com.kuanggui.EncryptDataSource.mapper.encrypt"})
    public class mybatisEncryptConfig {
        @Bean(name = "mybatisEncryptSqlSessionFactory")
        public SqlSessionFactory encryptSqlSessionFactoryBean(@Qualifier("encryptDataSource") DataSource dataSource) throws Exception {

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            //mybatis分页
            PageInterceptor pageInterceptor = getPageInterceptor();
            //添加插件
            sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/encrypt/*.xml"));

            return sqlSessionFactoryBean.getObject();
        }

        @Bean(name = "encryptTransactionManager")
        public DataSourceTransactionManager encryptTransactionManager(@Qualifier("encryptDataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean(name = "encryptSqlSessionTemplate")
        public SqlSessionTemplate encryptSqlSessionTemplate(@Qualifier("mybatisEncryptSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

    }
}
