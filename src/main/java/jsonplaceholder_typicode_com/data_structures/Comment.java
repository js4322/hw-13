package jsonplaceholder_typicode_com.data_structures;

public class Comment{
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public String toString() {
        return "Comment{" +
                "\npostID=" + postId +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\nemail='" + email + '\'' +
                "\nbody='" + body + '\'' +
                "\n}";
    }
    public Comment(int postID, int id, String name, String email, String body){
        this.postId = postID;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
    public Comment(){
        this(-1,-1,"comment sample", "EMPTY", "EMPTY");
    }
}
