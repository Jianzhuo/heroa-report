package com.hero.heroa.webs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hero.common.utils.PageUtils;
import com.hero.heroa.webs.entity.ConstructionType2mediaEntity;

import java.util.Map;

/**
 * 
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-08-26 11:26:47
 */
public interface ConstructionType2mediaService extends IService<ConstructionType2mediaEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

