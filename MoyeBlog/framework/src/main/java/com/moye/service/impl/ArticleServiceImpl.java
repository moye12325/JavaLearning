package com.moye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moye.domain.ResponseResult;
import com.moye.domain.entity.Article;
import com.moye.mapper.ArticleMapper;
import com.moye.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
//        查询热门文章，封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        必须是正式文章
//        按照浏览量进行排序
//        最多只能查询十条
        queryWrapper.eq(Article::getStatus, 0);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1, 0);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();

        return ResponseResult.okResult(articles);
    }
}
