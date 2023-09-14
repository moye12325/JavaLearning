package com.moye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moye.constants.SystemConstants;
import com.moye.domain.ResponseResult;
import com.moye.domain.entity.Comment;
import com.moye.domain.vo.CommentVo;
import com.moye.domain.vo.PageVo;
import com.moye.mapper.CommentMapper;
import com.moye.service.CommentService;
import com.moye.service.UserService;
import com.moye.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author moye12325
 * @since 2023-09-14 19:44:36
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;
    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {

//        查询对应文章的根评论
//        对articleId进行判断
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,articleId);
//        根评论rootId为-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOT);
//        分页查询
        Page<Comment> page = new Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());


        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(list, CommentVo.class);
//        遍历集合
//        需要根据commentVo类里面的createBy字段，然后
//    用createBy字段去查询user表的nickname字段(子评论的用户昵称)
//        nickname字段(发这条子评论的用户昵称)的数据赋值给commentVo类的username字段
        for (CommentVo commentVo : commentVoList) {
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            
            if (commentVo.getToCommentId()!=-1){
                String toCommentUserName = userService.getById(commentVo.getToCommentId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVoList;
    }
}
