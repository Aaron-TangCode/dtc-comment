package com.datoucai.entity;

import com.sun.scenario.effect.impl.prism.PrImage;
import lombok.Data;

import java.security.PrivateKey;
import java.util.Date;

/**
 * 评论实体类
 */
@Data
public class CommentEntity {

    private Long id;

    private Long userId;

    private Integer module;

    private Integer status;

    private Long resourceId;

    private String content;

    private Integer starNum;

    private Integer score;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

}
