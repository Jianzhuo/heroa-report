package com.hero.heroa.dreport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hero.common.utils.PageUtils;
import com.hero.heroa.dreport.entity.WorkUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 10:30:42
 */
public interface WorkUserService extends IService<WorkUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

