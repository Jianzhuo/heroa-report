package com.hero.heroa.dreport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hero.common.utils.PageUtils;
import com.hero.heroa.dreport.entity.WorkEntity;

import java.util.Map;

/**
 * 
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 13:02:26
 */
public interface WorkService extends IService<WorkEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

