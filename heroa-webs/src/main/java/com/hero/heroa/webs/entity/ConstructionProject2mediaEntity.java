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
@TableName("construction_project2media")
public class ConstructionProject2mediaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long projectId;
	/**
	 * 
	 */
	private String mediaUrl;
	/**
	 * 
	 */
	private String mediaType;
	/**
	 * 
	 */
	private Integer sort;

}
