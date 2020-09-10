package com.hero.heroa.webs.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.webs.dao.ConstructionType2mediaDao;
import com.hero.heroa.webs.entity.ConstructionType2mediaEntity;
import com.hero.heroa.webs.service.ConstructionType2mediaService;


@Service("constructionType2mediaService")
public class ConstructionType2mediaServiceImpl extends ServiceImpl<ConstructionType2mediaDao, ConstructionType2mediaEntity> implements ConstructionType2mediaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConstructionType2mediaEntity> page = this.page(
                new Query<ConstructionType2mediaEntity>().getPage(params),
                new QueryWrapper<ConstructionType2mediaEntity>()
        );

        return new PageUtils(page);
    }

}