package com.moye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moye.domain.ResponseResult;
import com.moye.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author moye12325
 * @since 2023-09-14 19:44:35
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);
}

