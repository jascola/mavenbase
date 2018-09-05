package com.jascola.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pic")
public class PictureController {
    @Value("#{prop['resource.realpath']}")
    private String realpath;

    @Value("#{prop['resource.virpath']}")
    private String virpath;

    @Value("#{prop['resource.indexpath']}")
    private String indexpath;

    @Value("#{prop['resource.indexrealpath']}")
    private String indexrealpath;
}
