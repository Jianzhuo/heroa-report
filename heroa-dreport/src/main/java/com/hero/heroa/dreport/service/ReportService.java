package com.hero.heroa.dreport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hero.common.utils.PageUtils;
import com.hero.heroa.dreport.entity.ReportEntity;

import java.util.Map;

/**
 * 日报表
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 13:02:27
 */
public interface ReportService extends IService<ReportEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

