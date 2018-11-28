package com.jascola.service;


import com.jascola.dao.AcgContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcgContentService {
    @Autowired
    private AcgContentDao dao;


}
