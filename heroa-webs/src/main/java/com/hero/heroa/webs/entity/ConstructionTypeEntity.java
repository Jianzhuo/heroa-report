package com.hero.heroa.webs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-08-26 11:26:47
 */
@Data
@TableName("construction_type")
public class ConstructionTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long typeId;
	/**
	 * 
	 */
	private String typeName;
	/**
	 * 
	 */
	private String bedroomNum;
	/**
	 * 
	 */
	private String bathroomNum;
	/**
	 * 
	 */
	private String area;
	/**
	 * 
	 */
	private String typeDes;

}
