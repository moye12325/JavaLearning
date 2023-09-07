package com.moye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moye.domain.entity.Article;
import com.moye.mapper.ArticleMapper;
import com.moye.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
}
