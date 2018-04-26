package server.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Message {

    public static ArrayList<Message> allMessages = new ArrayList<>();
    private static int LAST_ID;

    private int id;
    private String text;
    private Date postDate;
    private String author;

    public Message(String text, String author) {
        LAST_ID++;
        this.id = LAST_ID;
        this.text = text;
        this.postDate = new Date();
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getAuthor() {
        return author;
    }
}
