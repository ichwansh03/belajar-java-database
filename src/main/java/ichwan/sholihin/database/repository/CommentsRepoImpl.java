package ichwan.sholihin.database.repository;

import ichwan.sholihin.database.ConnectionUtil;
import ichwan.sholihin.database.entity.Comments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsRepoImpl implements CommentsRepository{
    @Override
    public void insert(Comments comments) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1, comments.getEmail());
                statement.setString(2, comments.getComment());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()){
                    if (resultSet.next()){
                        comments.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Comments findByIdComments(Integer id) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        return new Comments(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment"));
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Comments> findAllComments() {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments";
            try(Statement statement = connection.createStatement()){
                //kalo mau find by meail atau comment tinggal set string dari parameternya
                try(ResultSet resultSet = statement.executeQuery(sql)){
                    List<Comments> comments = new ArrayList<>();
                    while (resultSet.next()){
                        comments.add(new Comments(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }
                    return comments;
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
