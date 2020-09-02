package API.pojo;

public class CommentPojo {

    private String self;
    private int id;
    private Author author;
    private String body;
    private UpdateAuthor updateAuthor;
    private String created;
    private String updated;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UpdateAuthor getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(UpdateAuthor updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
