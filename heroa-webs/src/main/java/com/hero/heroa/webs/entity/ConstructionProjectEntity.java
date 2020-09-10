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
@TableName("construction_project")
public class ConstructionProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long projectId;
	/**
	 * 
	 */
	private String projectName;
	/**
	 * 
	 */
	private String projectLocation;
	/**
	 * 
	 */
	private String projectBudget;
	/**
	 * 
	 */
	private String projectArea;
	/**
	 * 
	 */
	private String projectType;
	/**
	 * 
	 */
	private String projectCompleted;
	/**
	 * 
	 */
	private String projectShortdes;
	/**
	 * 
	 */
	private String projectDes;
	/**
	 * 
	 */
	private String projectBanner;
	/**
	 * 
	 */
	private String projectBrochure;
	/**
	 * 
	 */
	private Integer projectOnhome;
	/**
	 * 
	 */
	private String projectCate;
	/**
	 * 
	 */
	private Integer projectSort;

}
