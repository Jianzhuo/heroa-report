package com.hero.heroa.dreport.dao;

import com.hero.heroa.dreport.entity.ReportEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日报表
 * 
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 13:02:27
 */
@Mapper
public interface ReportDao extends BaseMapper<ReportEntity> {
	
}
