package com.hero.heroa.webs.controller;

import java.util.Arrays;
import java.util.Map;

import com.hero.heroa.webs.entity.ConstructionProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.webs.service.ConstructionProjectService;
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
@RequestMapping("webs/constructionproject")
public class ConstructionProjectController {
    @Autowired
    private ConstructionProjectService constructionProjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("webs:constructionproject:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = constructionProjectService.queryPage(params);
        System.out.println("hello");

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{projectId}")
    //@RequiresPermissions("webs:constructionproject:info")
    public R info(@PathVariable("projectId") Long projectId){
		ConstructionProjectEntity constructionProject = constructionProjectService.getById(projectId);

        return R.ok().put("constructionProject", constructionProject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("webs:constructionproject:save")
    public R save(@RequestBody ConstructionProjectEntity constructionProject){
		constructionProjectService.save(constructionProject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("webs:constructionproject:update")
    public R update(@RequestBody ConstructionProjectEntity constructionProject){
		constructionProjectService.updateById(constructionProject);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("webs:constructionproject:delete")
    public R delete(@RequestBody Long[] projectIds){
		constructionProjectService.removeByIds(Arrays.asList(projectIds));

        return R.ok();
    }

}
