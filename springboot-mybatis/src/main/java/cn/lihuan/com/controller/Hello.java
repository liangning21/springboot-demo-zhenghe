package cn.lihuan.com.controller;

import cn.lihuan.com.entity.ZjClass;
import cn.lihuan.com.mapper.ZjClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/2/8 11:05
 * @Created by Dell
 */
@RestController
public class Hello {
    @Resource
    private ZjClassMapper zjClassMapper;

    @GetMapping("/getOne/{id}")
    public ZjClass getOne(@PathVariable("id") String id){
        ZjClass one = zjClassMapper.getOne(id);
        System.out.println(one.toString());
        return one;
    }

}
