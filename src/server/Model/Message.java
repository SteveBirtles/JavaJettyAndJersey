package server.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Message {

    public static ArrayList<Message> allMessages = new ArrayList<>();
    private static int LAST_ID = 0;

    private int id;
    private String text;
    private String postDate;
    private String author;

    public Message(int id, String text, String postDate, String author) {
        if (id > LAST_ID) {
            LAST_ID = id;
        } else {
            LAST_ID++;
            id = LAST_ID;
        }
        this.id = id;
        this.text = text;
        if (postDate == null) {
            SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date now = new Date();
            postDate = simpleDate.format(now);
        }
        this.postDate = postDate;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getAuthor() {
        return author;
    }
}
