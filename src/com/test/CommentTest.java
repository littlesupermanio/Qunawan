package com.test;

import com.hhb.dao.CommentDao;
import com.hhb.dao.SequenceDao;
import com.hhb.entity.Comment;
import com.hhb.entity.Sequence;
import com.hhb.globle.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentTest extends BaseTest{
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private SequenceDao sequenceDao;

    @Test
    public void getComment() {
        Sequence sq_commented = sequenceDao.getSeqByKeyAndType(Constants.COMMENTED_ORDER_STATE, Constants.ORDER_TYPE);

        List<Comment> comments = commentDao.getCommentsPerPage(1, sq_commented.getId(), 1, Constants.COMMENT_MAX);

        System.out.println(comments);
    }

    @Test
    public void avgCount() {
        List<Float[]> data = commentDao.getCountByAvg(4);
        System.out.println(data);
        Float place = commentDao.getAvg_Score("place", 1);
        System.out.println(place);
    }
}
