package com.moye.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mo_article")
@Accessors(chain = true)
/**
 * 文章表(Article)表实体类
 *
 * @author moye12325
 * @since 2023-09-07 20:27:17
 */
public class Article {
    @TableId
    private Long id;
//标题
    private String title;
//文章内容
    private String content;
//文章摘要
    private String summary;
//所属分类id
    private Long categoryId;

    @TableField(exist = false)
    private String categoryName;
//缩略图
    private String thumbnail;
//是否置顶（0否，1是）
    private String isTop;
//状态（0已发布，1草稿）
    private String status;
//访问量
    private Long viewCount;
//是否允许评论 1是，0否
    private String isComment;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
//删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



}

