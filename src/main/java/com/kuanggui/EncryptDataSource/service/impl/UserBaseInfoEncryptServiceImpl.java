package com.kuanggui.EncryptDataSource.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kuanggui.EncryptDataSource.domain.UserBaseInfo;
import com.kuanggui.EncryptDataSource.mapper.encrypt.UserBaseInfoEncryptMapper;
import com.kuanggui.EncryptDataSource.mapper.lingyongqian.UserBaseInfoMapper;
import com.kuanggui.EncryptDataSource.service.UserBaseInfoEncryptService;
import com.kuanggui.EncryptDataSource.service.UserBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userBaseInfoEncryptService")
public class UserBaseInfoEncryptServiceImpl implements UserBaseInfoEncryptService {
    @Autowired
    private UserBaseInfoEncryptMapper userBaseInfoEncryptMapper;

    @Override
    public Page<UserBaseInfo> selectPage(Map<String, Object> paramMap, Integer current, Integer pageSize) {
        PageHelper.startPage(current, pageSize);
        return (Page<UserBaseInfo>)userBaseInfoEncryptMapper.listSelective(paramMap);
    }

    @Override
    public UserBaseInfo findSelective(Map<String, Object> paramMap) {
        return userBaseInfoEncryptMapper.findSelective(paramMap);
    }
}
