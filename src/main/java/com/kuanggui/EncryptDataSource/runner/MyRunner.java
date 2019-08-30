package com.kuanggui.EncryptDataSource.runner;

import com.github.pagehelper.Page;
import com.kuanggui.EncryptDataSource.domain.UserBaseInfo;
import com.kuanggui.EncryptDataSource.mapper.encrypt.UserBaseInfoEncryptMapper;
import com.kuanggui.EncryptDataSource.mapper.lingyongqian.UserBaseInfoMapper;
import com.kuanggui.EncryptDataSource.service.UserBaseInfoEncryptService;
import com.kuanggui.EncryptDataSource.service.UserBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    private UserBaseInfoService userBaseInfoService;
    @Autowired
    private UserBaseInfoEncryptService userBaseInfoEncryptService;
    @Autowired
    private UserBaseInfoEncryptMapper userBaseInfoEncryptMapper;


    @Override
    public void run(String... args) throws Exception {
//        List<UserBaseInfo> userBaseInfoList = userBaseInfoMapper.listSelective(new HashMap<>());
//        Page<UserBaseInfo> userBaseInfoList = userBaseInfoService.selectPage(null, 1, 1000);
//        userBaseInfoList.forEach(userBaseInfo -> userBaseInfoEncryptMapper.save(userBaseInfo));
//        List<UserBaseInfo> userBaseInfoList = userBaseInfoEncryptMapper.listSelective(new HashMap<>());
//        userBaseInfoList.stream().map(UserBaseInfo::getIdNo).sorted().forEach(System.out::println);
//        Page<UserBaseInfo> userBaseInfoPage = userBaseInfoEncryptService.selectPage(null, 2, 500);
//        System.out.println(userBaseInfoPage.getTotal());
//        userBaseInfoPage.getResult().stream().map(UserBaseInfo::getIdNo).forEach(System.out::println);
        Map<String, Object> params = new HashMap<>(2);
        params.put("id_no", "441502199902183015");
        UserBaseInfo userBaseInfo = userBaseInfoEncryptService.findSelective(params);
        System.out.println(userBaseInfo);
    }
}
