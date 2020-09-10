package com.hero.heroa.webs.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.webs.dao.ConstructionProject2mediaDao;
import com.hero.heroa.webs.entity.ConstructionProject2mediaEntity;
import com.hero.heroa.webs.service.ConstructionProject2mediaService;


@Service("constructionProject2mediaService")
public class ConstructionProject2mediaServiceImpl extends ServiceImpl<ConstructionProject2mediaDao, ConstructionProject2mediaEntity> implements ConstructionProject2mediaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConstructionProject2mediaEntity> page = this.page(
                new Query<ConstructionProject2mediaEntity>().getPage(params),
                new QueryWrapper<ConstructionProject2mediaEntity>()
        );

        return new PageUtils(page);
    }

}