//package com.kuanggui.EncryptDataSource;
//
//import com.kuanggui.EncryptDataSource.domain.UserBaseInfo;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.shardingsphere.api.config.encrypt.EncryptColumnRuleConfiguration;
//import org.apache.shardingsphere.api.config.encrypt.EncryptRuleConfiguration;
//import org.apache.shardingsphere.api.config.encrypt.EncryptTableRuleConfiguration;
//import org.apache.shardingsphere.api.config.encrypt.EncryptorRuleConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class EncryptDataSourceApplicationTests {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//	@Test
//	public void contextLoads() {
//        System.out.println(jdbcTemplate.queryForObject("select id_no from cl_user_base_info  where id = ?", String.class, 38671));
//    }
//
//    @Test
//    public void test(){
//
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
//        encryptRuleConfig.getTables().put("cl_user_base_info", tableConfig);
//
//        // 获取数据源对象
//        try {
//            DataSource encryptDataSource = EncryptDataSourceFactory.createDataSource(hakariDataSource, encryptRuleConfig, new Properties());
//            JdbcTemplate encryptTemplate = new JdbcTemplate(encryptDataSource);
//            BeanPropertyRowMapper<UserBaseInfo> userBaseInfoBeanPropertyRowMapper = new BeanPropertyRowMapper<>(UserBaseInfo.class);
//            List<UserBaseInfo> userBaseInfoList = jdbcTemplate.query("select `id`, `user_id`, `phone`,`real_name`,`age`,`sex`, `national`,`id_no`, `id_addr`,`living_img`,`ocr_img`,`front_img`,`back_img`, `education`, `marry_state`, `company_name`, `company_phone`,`company_addr`,`company_detail_addr`,`company_coordinate`,`salary`,`working_years`,`working_img`,`live_time`,`live_addr`,`live_detail_addr`,`live_coordinate`,`phone_server_pwd`,`register_addr`, `register_coordinate`,`state`,`last_overdue_day`,`max_overdue_day`, `repay_assess`,`black_reason`, `create_time`,`update_time`, `check_code`, `update_user_id` from `cl_user_base_info` order by id asc limit 100", userBaseInfoBeanPropertyRowMapper);
////            System.out.println(encryptTemplate.queryForObject("select id_no from cl_user_base_info  where id = ?", String.class, 38671));
//            userBaseInfoList.stream().forEach(encryptDataSource.);
//            String insertSQL = "insert into `cl_user_base_info` (`id`,`user_id`,`phone`,`real_name`,`age`,`sex`,`national`,`id_no`,`id_addr`,`living_img`,`ocr_img`,`front_img`,`back_img`,`education`,`marry_state`,`company_name`,`company_phone`,`company_addr`,`company_detail_addr`,`company_coordinate`,`salary`,`working_years`,`working_img`,`live_time`,`live_addr`,`live_detail_addr`,`live_coordinate`,`phone_server_pwd`,`register_addr`,`register_coordinate`,`state`,`last_overdue_day`,`max_overdue_day`,`repay_assess`,`black_reason`,`create_time`,`update_time`,`check_code`,`update_user_id`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,<company_coordinate>,<salary>,<working_years>,<working_img>,<live_time>,<live_addr>,<live_detail_addr>,<live_coordinate>,<phone_server_pwd>,<register_addr>,<register_coordinate>,<state>,<last_overdue_day>,<max_overdue_day>,<repay_assess>,<lack_reason>,<create_time>,<update_time>,<check_code>,<update_user_id>)";
//            encryptTemplate.batchUpdate()
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private List<Object[]> transformFlowCarReportDayBoToObjects(List<UserBaseInfo> userBaseInfoList) {
//
//        List<Object[]> list = new ArrayList<>();
//
//        Object[] object = null;
//        for(UserBaseInfo userBaseInfo :userBaseInfoList){
//            object = new Object[]{
//                    userBaseInfo.getId(),
//                    flowCarReportDayBo.getPark_number(),
//                    flowCarReportDayBo.getPark_name(),
//                    flowCarReportDayBo.getStart_time(),
//                    flowCarReportDayBo.getNature_sum_count(),
//                    flowCarReportDayBo.getTemp_car_count(),
//                    flowCarReportDayBo.getVip_car_count(),
//                    flowCarReportDayBo.getIn_car_count(),
//                    flowCarReportDayBo.getOut_car_count(),
//                    flowCarReportDayBo.getCharge_sum_count(),
//                    flowCarReportDayBo.getCharge_car_count(),
//                    flowCarReportDayBo.getFree_car_count(),
//                    flowCarReportDayBo.getDiscount_sum_count(),
//                    flowCarReportDayBo.getDiscount_local_car_count(),
//                    flowCarReportDayBo.getDiscount_bussiness_car_count(),
//                    flowCarReportDayBo.getVisit_in_car_count(),
//                    flowCarReportDayBo.getVisit_out_car_count(),
//                    flowCarReportDayBo.getBlack_in_car_count(),
//                    flowCarReportDayBo.getBlack_out_car_count(),
//            };
//            list.add(object);
//        }
//
//        return list ;
//    }
//
//}
