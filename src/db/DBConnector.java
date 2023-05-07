package db;

import user.Comments;
import user.News;
import user.NewsCategories;
import user.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnector {
    private static Connection connection;


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/sprint-task",
                    "root",
                    "root");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Users getUser(String email) {
        Users users = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users where email = ?");

            statement.setString(1,email);

            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                users = new Users();
                users.setId(resultSet.getLong("id"));
                users.setEmail(resultSet.getString("email"));
                users.setPassword(resultSet.getString("password"));
                users.setFullName(resultSet.getString("full_name"));
                users.setRole_id(resultSet.getString("role_id"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;

    }
    public static void addUser(Users users) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(email, password, full_name, role_id)\n " +
                            "VALUES (?,?,?,?)");

            statement.setString(1,users.getEmail());
            statement.setString(2,users.getPassword());
            statement.setString(3,users.getFull_name());
            statement.setString(4,"1");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(Users users){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users\n" +
                            "SET email        = ?,\n" +
                            "    password      = ?,\n" +
                            "    full_name        = ?\n"+
                            "WHERE id = ?");
            statement.setString(1,users.getEmail());
            statement.setString(2,users.getPassword());
            statement.setString(3,users.getFull_name());
            statement.setLong(4,users.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<NewsCategories> getNewsCategories(){
        ArrayList<NewsCategories> newsCategories = new ArrayList<NewsCategories>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_categories");

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                NewsCategories news = new NewsCategories();
                news.setId(resultSet.getLong("id"));
                news.setName(resultSet.getString("name"));

                newsCategories.add(news);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsCategories;

    }
    public static void addNews(News news) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news(post_date, category_id, title, content,user_id)\n " +
                            "VALUES (NOW(),?,?,?,?)");

            statement.setLong(1,news.getNewsCategories().getId());
            statement.setString(2,news.getTitle());
            statement.setString(3,news.getContent());
            statement.setLong(4,news.getUsers().getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<News> getNews() {
        ArrayList<News> news = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM news\n" +
                            "    INNER JOIN news_categories c on news.category_id = c.id\n" +
                            "INNER JOIN users u on news.user_id = u.id");

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                News news1 = new News();
                NewsCategories n = new NewsCategories();
                n.setId(resultSet.getLong("c.id"));
                n.setName(resultSet.getString("c.name"));

                news1.setId(resultSet.getLong("id"));
                news1.setTimestamp(resultSet.getTimestamp("post_date"));
                news1.setNewsCategories(n);
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));
                news1.setUsers(new Users(resultSet.getLong("user_id"),resultSet.getString("full_name")));

                news.add(news1);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }
    public static News getNews(long id) {
        News news = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM news\n" +
                            "    INNER JOIN news_categories c on news.category_id = c.id\n" +
                            "    INNER JOIN users u on news.user_id = u.id\n" +
                            "WHERE news.id=?");

            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                news = new News();
                NewsCategories n = new NewsCategories();
                n.setId(resultSet.getLong("c.id"));
                n.setName(resultSet.getString("c.name"));

                news.setId(resultSet.getLong("id"));
                news.setTimestamp(resultSet.getTimestamp("post_date"));
                news.setNewsCategories(n);
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setUsers(new Users(resultSet.getLong("user_id"),resultSet.getString("full_name")));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }
    public static ArrayList<Comments> getAllComments() {
        ArrayList<Comments> comments = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM comments\n" +
                            "    INNER JOIN users u on comments.user_id = u.id");

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Comments com = new Comments();
                com.setId(resultSet.getLong("id"));
                com.setComment(resultSet.getString("comment"));
                com.setPost_date(resultSet.getTimestamp("post_date"));
                com.setUsers(new Users(resultSet.getLong("user_id"),(resultSet.getString("full_name"))));
                com.setNews_id(resultSet.getLong("news_id"));

                comments.add(com);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void addComment(Comments comments) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO comments(comment, post_date, user_id, news_id)\n " +
                            "VALUES (?,NOW(),?,?)");

            statement.setString(1, comments.getComment());
            statement.setLong(2, comments.getUsers().getId());
            statement.setLong(3, comments.getNews_id());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteComments(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM comments WHERE news_id = ?;");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteNews(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM news WHERE id = ?;");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateNews(News news) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE news\n" +
                            "SET post_date   = Now(),\n" +
                            "    category_id = ?,\n" +
                            "    title       = ?,\n" +
                            "    content     = ?\n" +
                            "WHERE id = ?;"
            );


            statement.setLong(1, news.getNewsCategories().getId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.setLong(4,news.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Users getUser(long id) {
        Users user = new Users();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users where id = ?");

            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRole_id(resultSet.getString("role_id"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }
}
