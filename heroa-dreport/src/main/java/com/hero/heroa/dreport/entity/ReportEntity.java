package com.hero.heroa.dreport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 日报表
 * 
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 13:02:27
 */
@Data
@TableName("dreport_report")
public class ReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日报id
	 */
	@TableId
	private Long reportId;
	/**
	 * 日报提交时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date reportTime;
	/**
	 * 日报提交者用户id
	 */
	private String reportUserName;
	/**
	 * 
	 */
	private String reportNote;
	/**
	 * 
	 */
	private Integer reportRate;

	@TableField(exist = false)
	private List<WorkEntity> works;
}
