package entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class Message {
    private int id;
    private int userTo;
    private int userFrom;
    private String message;
    private int localId;
    private String dateTime;


    public Message(int userTo, int userFrom, String message, int localId, String dateTime) {
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.message = message;
        this.localId = localId;
        this.dateTime = dateTime;
    }
}
