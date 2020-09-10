package com.hero.heroa.webs.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.webs.entity.ConstructionType2mediaEntity;
import com.hero.heroa.webs.service.ConstructionType2mediaService;
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
@RequestMapping("webs/constructiontype2media")
public class ConstructionType2mediaController {
    @Autowired
    private ConstructionType2mediaService constructionType2mediaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("webs:constructiontype2media:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = constructionType2mediaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("webs:constructiontype2media:info")
    public R info(@PathVariable("id") Long id){
		ConstructionType2mediaEntity constructionType2media = constructionType2mediaService.getById(id);

        return R.ok().put("constructionType2media", constructionType2media);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("webs:constructiontype2media:save")
    public R save(@RequestBody ConstructionType2mediaEntity constructionType2media){
		constructionType2mediaService.save(constructionType2media);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("webs:constructiontype2media:update")
    public R update(@RequestBody ConstructionType2mediaEntity constructionType2media){
		constructionType2mediaService.updateById(constructionType2media);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("webs:constructiontype2media:delete")
    public R delete(@RequestBody Long[] ids){
		constructionType2mediaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
