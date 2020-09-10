package com.hero.heroa.webs.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.webs.entity.ConstructionProject2mediaEntity;
import com.hero.heroa.webs.service.ConstructionProject2mediaService;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.R;



/**
 * 
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-08-26 11:26:47
 */
@RestController
@RequestMapping("webs/constructionproject2media")
public class ConstructionProject2mediaController {
    @Autowired
    private ConstructionProject2mediaService constructionProject2mediaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("webs:constructionproject2media:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = constructionProject2mediaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("webs:constructionproject2media:info")
    public R info(@PathVariable("id") Long id){
		ConstructionProject2mediaEntity constructionProject2media = constructionProject2mediaService.getById(id);

        return R.ok().put("constructionProject2media", constructionProject2media);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("webs:constructionproject2media:save")
    public R save(@RequestBody ConstructionProject2mediaEntity constructionProject2media){
		constructionProject2mediaService.save(constructionProject2media);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("webs:constructionproject2media:update")
    public R update(@RequestBody ConstructionProject2mediaEntity constructionProject2media){
		constructionProject2mediaService.updateById(constructionProject2media);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("webs:constructionproject2media:delete")
    public R delete(@RequestBody Long[] ids){
		constructionProject2mediaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
