package com.kuanggui.EncryptDataSource.service;

import com.github.pagehelper.Page;
import com.kuanggui.EncryptDataSource.domain.UserBaseInfo;

import java.util.Map;

public interface UserBaseInfoEncryptService {

    Page<UserBaseInfo> selectPage(Map<String, Object> paramMap, Integer current, Integer pageSize);

    UserBaseInfo findSelective(Map<String, Object> paramMap);
}
