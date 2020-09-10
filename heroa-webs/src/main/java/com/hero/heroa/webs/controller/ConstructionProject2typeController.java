package com.hero.heroa.webs.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.webs.entity.ConstructionProject2typeEntity;
import com.hero.heroa.webs.service.ConstructionProject2typeService;
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
@RequestMapping("webs/constructionproject2type")
public class ConstructionProject2typeController {
    @Autowired
    private ConstructionProject2typeService constructionProject2typeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("webs:constructionproject2type:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = constructionProject2typeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("webs:constructionproject2type:info")
    public R info(@PathVariable("id") Long id){
		ConstructionProject2typeEntity constructionProject2type = constructionProject2typeService.getById(id);

        return R.ok().put("constructionProject2type", constructionProject2type);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("webs:constructionproject2type:save")
    public R save(@RequestBody ConstructionProject2typeEntity constructionProject2type){
		constructionProject2typeService.save(constructionProject2type);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("webs:constructionproject2type:update")
    public R update(@RequestBody ConstructionProject2typeEntity constructionProject2type){
		constructionProject2typeService.updateById(constructionProject2type);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("webs:constructionproject2type:delete")
    public R delete(@RequestBody Long[] ids){
		constructionProject2typeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
