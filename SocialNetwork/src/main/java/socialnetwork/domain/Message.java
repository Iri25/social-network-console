package socialnetwork.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Message extends Entity<Long>{
    private Long from;
    private List<Long> to;
    private String message;
    private LocalDateTime date;
    private Long reply;

    /**
     * Constructor with parameters
     * @param from
     * @param message
     * @param date
     */
    public Message(Long from, String message, LocalDateTime date){
        this.from = from;
        to = new ArrayList<>();
        this.message = message;
        this.date = date;
        reply = null;
    }


    /**
     * getFrom
     * @return the sender of the message
     */
    public Long getFrom() {
        return from;
    }

    /**
     * set the sender of the message with @param from
     * @param from
     */
    public void setFrom(Long from) {
        this.from = from;
    }

    /**
     * getTo
     * @return the list of recipients of the message
     */
    public List<Long> getTo() {
        return to;
    }

    /**
     * set the list of recipients of the message with @param to
     * @param to
     */
    public void setTo(List<Long> to) {
        this.to = to;
    }

    /**
     * getMessage
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * set the message with @param message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return the date when the message was sent
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * set the date when the message was sent with @param date
     * @param date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * getReply
     * @return the recipient's reply
     */
    public Long getReply() {
        return reply;
    }

    /**
     * set the reply with @param reply
     * @param reply
     */
    public void setReply(Long reply) {
        this.reply = reply;
    }

    /**
     * toString
     * @return message object as string
     */
    @Override
    public String toString() {
        return "Message: " + getId() +
                ", from = " + from +
                ", to = " + to +
                ", message = " + message +
                ", date = " + date +
                ", reply = " + reply;
    }

}
