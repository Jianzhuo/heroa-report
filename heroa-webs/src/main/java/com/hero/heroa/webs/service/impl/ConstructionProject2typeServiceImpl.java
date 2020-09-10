package com.hero.heroa.webs.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.webs.dao.ConstructionProject2typeDao;
import com.hero.heroa.webs.entity.ConstructionProject2typeEntity;
import com.hero.heroa.webs.service.ConstructionProject2typeService;


@Service("constructionProject2typeService")
public class ConstructionProject2typeServiceImpl extends ServiceImpl<ConstructionProject2typeDao, ConstructionProject2typeEntity> implements ConstructionProject2typeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConstructionProject2typeEntity> page = this.page(
                new Query<ConstructionProject2typeEntity>().getPage(params),
                new QueryWrapper<ConstructionProject2typeEntity>()
        );

        return new PageUtils(page);
    }

}