package ichwan.sholihin.database;

import ichwan.sholihin.database.entity.Comments;
import ichwan.sholihin.database.repository.CommentsRepoImpl;
import ichwan.sholihin.database.repository.CommentsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryTest {

    private CommentsRepository commentsRepository;

    @BeforeEach
    void setUp(){
        commentsRepository = new CommentsRepoImpl();
    }

    @Test
    void testInsert(){
        Comments comments = new Comments("ichwan@test.com","hello world");
        commentsRepository.insert(comments);

        Assertions.assertNotNull(comments.getId());
        System.out.println(comments.getId());
    }

    @Test
    void testFindById(){
        Comments comments = commentsRepository.findByIdComments(10);
        Assertions.assertNotNull(comments);
        System.out.println(comments.getId());
        System.out.println(comments.getEmail());
        System.out.println(comments.getComment());
    }

    @Test
    void testFindByAll(){
        List<Comments> list = commentsRepository.findAllComments();
        System.out.println(list.size());
    }
}
