package user;

public class NewsCategories {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public NewsCategories(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NewsCategories() {
    }

    public NewsCategories(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
