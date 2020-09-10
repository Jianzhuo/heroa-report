package com.hero.heroa.dreport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 13:02:26
 */
@Data
@TableName("dreport_work")
public class WorkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 工作id
	 */
	@TableId
	private Long workId;
	/**
	 * 工作开始时间
	 */
	private Time workStart;
	/**
	 * 工作结束时间
	 */
	private Time workEnd;
	/**
	 * 
	 */
	private String durationTime;
	/**
	 * 
	 */
	private String workLocation;
	/**
	 * 
	 */
	private String workContent;
	/**
	 * 
	 */
	private String workDetail;
	/**
	 * 
	 */
	private Integer workProgress;
	/**
	 * 
	 */
	private String workNote;

	@TableField(exist = false)
	private String workMate;

	@TableField(exist = false)
	private Long reportId;

	@TableField(exist = false)
	private List<String> workMates;

}
