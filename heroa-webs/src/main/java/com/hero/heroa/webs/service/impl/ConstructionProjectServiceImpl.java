package com.hero.heroa.webs.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.webs.dao.ConstructionProjectDao;
import com.hero.heroa.webs.entity.ConstructionProjectEntity;
import com.hero.heroa.webs.service.ConstructionProjectService;


@Service("constructionProjectService")
public class ConstructionProjectServiceImpl extends ServiceImpl<ConstructionProjectDao, ConstructionProjectEntity> implements ConstructionProjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConstructionProjectEntity> page = this.page(
                new Query<ConstructionProjectEntity>().getPage(params),
                new QueryWrapper<ConstructionProjectEntity>()
        );

        return new PageUtils(page);
    }

}