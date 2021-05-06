package com.example.caigou_alpha.controller;

import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.entity.Menu;
import com.example.caigou_alpha.entity.UserOrder;
import com.example.caigou_alpha.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/menu")//给访问链接加个前缀
public class MenuController {
    @Resource
    private MenuService menuService;

    @GetMapping("/findAll/{pageNum}")
    public Result<Page<Menu>> findAll(@PathVariable Integer pageNum){
        return Result.success(menuService.findPage(pageNum,5));
    }

    /**
     * 模糊分页查询
     * @param pageNum
     * @param name
     * @return
     */
    @GetMapping("/findLike")
    public Result<Page<Menu>> findMenuLike( @RequestParam(required = true)Integer pageNum,@RequestParam(required = true)String name){
        return Result.success(menuService.findLike(pageNum,5,name));
    }

    @PostMapping("/addMenu")
    public Result add(@RequestBody Menu menu){
        menuService.save(menu);
        return Result.success();
    }

    @PutMapping("/updateMenu")
    public Result update(@RequestBody Menu menu){
        menuService.save(menu);
        return Result.success();
    }

    @DeleteMapping("/deleteMenu/{id}")
    public Result delete(@PathVariable Integer id){
        menuService.del(id);
        return Result.success();
    }
}
