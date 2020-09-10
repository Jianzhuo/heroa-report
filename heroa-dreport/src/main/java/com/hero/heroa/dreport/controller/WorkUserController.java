package com.hero.heroa.dreport.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.dreport.entity.WorkUserEntity;
import com.hero.heroa.dreport.service.WorkUserService;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.R;



/**
 * 
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 10:30:42
 */
@RestController
@RequestMapping("dreport/workuser")
public class WorkUserController {
    @Autowired
    private WorkUserService workUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("dreport:workuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("dreport:workuser:info")
    public R info(@PathVariable("id") Long id){
		WorkUserEntity workUser = workUserService.getById(id);

        return R.ok().put("workUser", workUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("dreport:workuser:save")
    public R save(@RequestBody WorkUserEntity workUser){
		workUserService.save(workUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("dreport:workuser:update")
    public R update(@RequestBody WorkUserEntity workUser){
		workUserService.updateById(workUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("dreport:workuser:delete")
    public R delete(@RequestBody Long[] ids){
		workUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
