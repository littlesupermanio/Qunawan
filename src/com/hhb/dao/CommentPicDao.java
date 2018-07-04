package com.hhb.dao;

import com.hhb.entity.CommentPicture;

import java.util.Set;

public interface CommentPicDao {
    /**
     * 存储评论图片
     */
    Integer save(CommentPicture cp);

    /**
     * 根据评论id获取评论图集
     */
    Set<CommentPicture> getCommentPics(Integer commentId);
}
