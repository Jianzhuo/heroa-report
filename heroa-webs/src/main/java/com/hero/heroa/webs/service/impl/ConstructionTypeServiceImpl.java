package com.hero.heroa.webs.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.webs.dao.ConstructionTypeDao;
import com.hero.heroa.webs.entity.ConstructionTypeEntity;
import com.hero.heroa.webs.service.ConstructionTypeService;


@Service("constructionTypeService")
public class ConstructionTypeServiceImpl extends ServiceImpl<ConstructionTypeDao, ConstructionTypeEntity> implements ConstructionTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConstructionTypeEntity> page = this.page(
                new Query<ConstructionTypeEntity>().getPage(params),
                new QueryWrapper<ConstructionTypeEntity>()
        );

        return new PageUtils(page);
    }

}