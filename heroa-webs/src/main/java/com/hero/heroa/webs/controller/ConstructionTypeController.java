package com.hero.heroa.webs.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.webs.entity.ConstructionTypeEntity;
import com.hero.heroa.webs.service.ConstructionTypeService;
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
@RequestMapping("webs/constructiontype")
public class ConstructionTypeController {
    @Autowired
    private ConstructionTypeService constructionTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("webs:constructiontype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = constructionTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{typeId}")
    //@RequiresPermissions("webs:constructiontype:info")
    public R info(@PathVariable("typeId") Long typeId){
		ConstructionTypeEntity constructionType = constructionTypeService.getById(typeId);

        return R.ok().put("constructionType", constructionType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("webs:constructiontype:save")
    public R save(@RequestBody ConstructionTypeEntity constructionType){
		constructionTypeService.save(constructionType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("webs:constructiontype:update")
    public R update(@RequestBody ConstructionTypeEntity constructionType){
		constructionTypeService.updateById(constructionType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("webs:constructiontype:delete")
    public R delete(@RequestBody Long[] typeIds){
		constructionTypeService.removeByIds(Arrays.asList(typeIds));

        return R.ok();
    }

}
