package user;

import java.sql.Timestamp;

public class News {
    private long id;
    private Timestamp timestamp;
    private NewsCategories newsCategories;
    private String title;
    private String content;
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public News() {
    }

    public News(NewsCategories newsCategories, String title, String content) {
        this.newsCategories = newsCategories;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public NewsCategories getNewsCategories() {
        return newsCategories;
    }

    public void setNewsCategories(NewsCategories newsCategories) {
        this.newsCategories = newsCategories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
