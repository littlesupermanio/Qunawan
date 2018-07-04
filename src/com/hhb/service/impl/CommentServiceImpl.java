package com.hhb.service.impl;

import com.hhb.entity.Comment;
import com.hhb.entity.CommentPicture;
import com.hhb.entity.Sequence;
import com.hhb.service.CommentService;
import com.hhb.utils.Utils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public void saveComment(Comment comment, List<byte[]> byteArray, Sequence sq_commented) {

    }

    @Override
    public void initCommentPicture(Set<CommentPicture> pictures, String basePath) {
        if (pictures == null || pictures.size() == 0) {
            return;
        }
        for (CommentPicture cp : pictures) {
            String path = basePath + "image_cache\\" + cp.getName();
            if (!new File(path).exists()) {
                Utils.getFile(cp.getData(), path);
            }
        }
    }
}
