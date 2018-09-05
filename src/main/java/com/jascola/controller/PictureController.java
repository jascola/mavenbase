package com.jascola.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pic")
public class PictureController {
    @Value("#{prop.realpath}")
    private String realpath;

    @Value("#{prop.virpath}")
    private String virpath;

    @Value("#{prop.indexpath}")
    private String indexpath;

    @Value("#{prop.indexrealpath}")
    private String indexrealpath;
}
