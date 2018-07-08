package com.hhb.service;

import com.hhb.entity.Comment;
import com.hhb.entity.CommentPicture;
import com.hhb.entity.Sequence;

import java.util.List;
import java.util.Set;

public interface CommentService {
        /**
         * 添加新评论及评论图片
         */
        void saveComment(Comment comment, List<byte[]> byteArray, Sequence sq_commented);

        /**
         * 检查是否存在评论的缓存图片，没如果不存在则创建图片
         * @param pictures
         * @param basePath
         */
        void initCommentPicture(Set<CommentPicture> pictures, String basePath);


}
