package com.study.hu.rpc.controller;

import com.study.hu.rpc.config.LogFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author hudongdong
 * @Date 2019/12/20 16:22
 */
@RestController
@RequestMapping("/text")
public class TestController {

    @RequestMapping("/code/{id}")
    @LogFile(beanClass = TestController.class, method = "getMessage", params = "id")
    public String getMessage(@PathVariable Integer id) {
        System.out.println("hello world");
        return null;
    }

}
