package com.codebear.xhome.controller;

import com.codebear.xhome.entity.KqUser;
import com.codebear.xhome.mapper.KqUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KqUserController {

    @Autowired
    private KqUserMapper kqUserMapper;

    @GetMapping("/kquser/{id}")
    public KqUser getKqUser(@PathVariable("id") String id) {
        return kqUserMapper.getKqUser(id);
    }

    @GetMapping("/kquser")
    public KqUser insertKqUser(KqUser kqUser){
        kqUserMapper.insertKqUser(kqUser);
        return kqUser;
    }

    @GetMapping("/deletekquser/{id}")
    public String deleteKqUser(@PathVariable("id") String id){
        kqUserMapper.deleteKqUser(id);
        return id;
    }

    @GetMapping("/updatekquser/{id}")
    public String updateKqUser(@PathVariable("id") String id){
        kqUserMapper.updateKqUser(id);
        return id;
    }

    @Transactional
    @GetMapping("/tan/{id}")
    public void tan(@PathVariable("id") String id){
        kqUserMapper.updateKqUser(id);
        int i = 10 / 0;
    }
}
