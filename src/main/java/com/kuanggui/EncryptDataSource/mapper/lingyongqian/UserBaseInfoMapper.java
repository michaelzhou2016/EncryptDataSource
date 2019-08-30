package com.kuanggui.EncryptDataSource.mapper.lingyongqian;

import com.kuanggui.EncryptDataSource.domain.UserBaseInfo;

import java.util.List;
import java.util.Map;

public interface UserBaseInfoMapper {

    UserBaseInfo findByPrimary(Long id);

    List<UserBaseInfo> listSelective(Map<String, Object> paramMap);
}
