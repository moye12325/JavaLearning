package com.moye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moye.domain.ResponseResult;
import com.moye.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author moye12325
 * @since 2023-09-11 16:12:40
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

