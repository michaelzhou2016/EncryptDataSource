package com.kuanggui.EncryptDataSource.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户详细信息实体
 */
@Data
 public class UserBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 客户表 外键
	 */
	private Long userId;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 民族
	 */
	private String national;

	/**
	 * 证件号码
	 */
	private String idNo;

	/**
	 * 身份证地址
	 */
	private String idAddr;

	/**
	 * 自拍(人脸识别照片)
	 */
	private String livingImg;

	/**
	 * 身份证头像
	 */
	private String ocrImg;

	/**
	 * 身份证正面
	 */
	private String frontImg;

	/**
	 * 身份证反面
	 */
	private String backImg;

	/**
	 * 学历
	 */
	private String education;

	/**
	 * 婚姻状况
	 */
	private String marryState;

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 公司电话
	 */
	private String companyPhone;

	/**
	 * 公司地址
	 */
	private String companyAddr;

	/**
	 * 公司详细地址
	 */
	private String companyDetailAddr;

	/**
	 * 公司坐标(经度,纬度)
	 */
	private String companyCoordinate;

	/**
	 * 月薪范围
	 */
	private String salary;

	/**
	 * 工作年限
	 */
	private String workingYears;

	/**
	 * 工作照片
	 */
	private String workingImg;

	/**
	 * 居住时长
	 */
	private String liveTime;

	/**
	 * 居住地址
	 */
	private String liveAddr;

	/**
	 * 居住详细地址
	 */
	private String liveDetailAddr;

	/**
	 * 居住地坐标，(经度,纬度)
	 */
	private String liveCoordinate;

	/**
	 * 运营商服务密码
	 */
	private String phoneServerPwd;

	/**
	 * 注册地址
	 */
	private String registerAddr;

	/**
	 * 注册地坐标，(经度,纬度)
	 */
	private String registerCoordinate;

	/**
	 * 是否黑名单 ，10是20不是
	 */
	private String state;

	/**
	 * 拉黑原因
	 */
	private String blackReason;

	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 手机号+身份证号拼接生成的md5 供融360使用
	 */
	private String checkCode;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 催收评级:0-无,1-优良客户,2-一般客户,3-不良客户
	 */
	private Integer repayAssess;
	
	private Integer lastOverdueDay;

}