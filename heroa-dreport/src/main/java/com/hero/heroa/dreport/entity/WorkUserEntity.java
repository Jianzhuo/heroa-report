package com.hero.heroa.dreport.entity;

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
 * @date 2020-07-15 10:30:42
 */
@Data
@TableName("dreport_work_user")
public class WorkUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long workId;
	/**
	 * 
	 */
	private String userName;

}
