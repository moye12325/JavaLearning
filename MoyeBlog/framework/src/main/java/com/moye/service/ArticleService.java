package com.moye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moye.domain.ResponseResult;
import com.moye.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
