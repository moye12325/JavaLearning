package com.moye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moye.domain.ResponseResult;
import com.moye.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author moye12325
 * @since 2023-09-09 16:54:03
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

