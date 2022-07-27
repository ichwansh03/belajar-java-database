package ichwan.sholihin.database.repository;

import ichwan.sholihin.database.entity.Comments;

import java.util.List;

public interface CommentsRepository {

    void insert(Comments comments);

    Comments findByIdComments(Integer id);

    List<Comments> findAllComments();
}
