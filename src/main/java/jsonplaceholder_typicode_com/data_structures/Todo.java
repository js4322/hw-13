package jsonplaceholder_typicode_com.data_structures;

public class Todo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
    public Todo(int userId, int id, String title, boolean completed){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
    public Todo(){
        this(0,0,"To do SAMPLE", false);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "\nuserId=" + userId +
                "\nid=" + id +
                "\ntitle='" + title + '\'' +
                "\ncompleted=" + completed +
                "\n}\n";
    }

    public boolean isCompleted() {
        return completed;
    }
}
