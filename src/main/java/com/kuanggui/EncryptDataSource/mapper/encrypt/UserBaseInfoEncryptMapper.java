package com.kuanggui.EncryptDataSource.mapper.encrypt;

import com.kuanggui.EncryptDataSource.domain.UserBaseInfo;

import java.util.List;
import java.util.Map;

public interface UserBaseInfoEncryptMapper {

    UserBaseInfo findByPrimary(Long id);

    UserBaseInfo findSelective(Map<String, Object> paramMap);

    List<UserBaseInfo> listSelective(Map<String, Object> paramMap);

    int save(UserBaseInfo userBaseInfo);
}
