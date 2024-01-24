package socialnetwork.service;

import socialnetwork.domain.Message;
import socialnetwork.domain.Friendship;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.User;
import socialnetwork.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService {
    private final Repository<Tuple<Long, Long>, Friendship> repositoryFriendship;
    private final Repository<Long, User> repositoryUser;
    private final Repository<Long, Message> repositoryMessage;
    private Long FreeId;

    /**
     *
     * constructor with parameters @param repositoryFriendship, @param repositoryUser, @param repositoryMessage
     * @param repositoryFriendship
     * @param repositoryUser
     * @param repositoryMessage
     */
    public MessageService(Repository<Tuple<Long, Long>, Friendship> repositoryFriendship, Repository<Long, User> repositoryUser, Repository<Long, Message> repositoryMessage) {
        this.repositoryFriendship = repositoryFriendship;
        this.repositoryUser = repositoryUser;
        this.repositoryMessage = repositoryMessage;
        FreeId = 0L;
    }

    /**
     * set the id used for save a massage with the first id possible
     */
    private void checkId(){
        FreeId = 0L;
        int number = 0;
        int size = 0;
        boolean ok = true;
        for(Message msg: repositoryMessage.findAll())
            size++;
        for(Message message: repositoryMessage.findAll()) {
            FreeId++;
            number++;
            if (!FreeId.equals(message.getId())) {
                ok = false;
                break;
            }
        }
        if(number == size && ok == true)
            FreeId++;

    }

    /**
     * send a message based on @param from, @param to, @param message, @param date
     * @param from
     * @param to
     * @param message
     * @param date
     */
    public void sendMessage(long from, List<Long> to, String message, LocalDateTime date){
        int ok = 0;
        if(repositoryUser.findOne(from) == null)
            ok = 1;
        for (Long allTo : to)
            if (repositoryUser.findOne(allTo) == null)
                ok = 1;

        if(ok == 1)
        {
            System.out.println("Id not valid!");
        }
        else
        {
            Message msg = new Message(from, message, date);
            msg.setTo(to);
            checkId();
            msg.setId(FreeId);
            repositoryMessage.save(msg);
            System.out.println("Message sent!");
        }
    }

    /**
     * delete a message based on @param id
     * @param id
     */
    public void deleteMessage(long id){
        Message message = repositoryMessage.findOne(id);
        if(message != null){
            repositoryMessage.delete(id);
            System.out.println("Message deleted!");
        }
        else
            System.out.println("Id not valid!");
        FreeId = 0L;
    }
    /**
     * gets all messages
     * @return an Iterable
     */
    public Iterable<Message> getAll(){
        return repositoryMessage.findAll();
    }

    /**
     * reply a message based on @idMessages, @param from, @param to, @param message, @param date
     * @param idMessage
     * @param from
     * @param to
     * @param message
     * @param date
     */
    public void sendReply(long idMessage, long from, List<Long> to, String message, LocalDateTime date){
        Message msg = repositoryMessage.findOne(idMessage);
        Long idReply = 0L;
        int ok = 0;
        if(repositoryUser.findOne(from) == null)
            ok = 1;
        for (Long allTo : to)
            if (repositoryUser.findOne(allTo) == null)
                ok = 1;

        if(ok == 1 && repositoryMessage.findOne(idMessage) == null)
        {
            System.out.println("Id message not valid!");
        }
        else if(!msg.getTo().contains(from) || !to.contains(msg.getFrom())){
            System.out.println("Id from or to not valid!");
        }
        else {
            idReply++;
            Message reply = new Message(from, message, date);
            checkId();
            reply.setId(FreeId);
            reply.setTo(to);
            reply.setReply(idReply);
            repositoryMessage.save(reply);
            System.out.println("Reply sent!");
        }
    }

    /**
     * show all messages between two users based on @param idUser1, @param idUser2
     * @param idUser1
     * @param idUser2
     */
    public void showMessagesBetweenTwoUsers(long idUser1, long idUser2){
        if(repositoryMessage.findOne(idUser1) == null || repositoryMessage.findOne(idUser2) == null)
            System.out.println("Id not valid!");
        repositoryMessage.findAll().forEach((x)->{
            if((x.getFrom() == idUser1 && x.getTo().contains(idUser2)) || (x.getFrom() == idUser2 && x.getTo().contains(idUser1))){
                System.out.print("User = " + x.getFrom() + ", message = " + x.getMessage() + ", date = " + x.getDate());
                System.out.println();
            }
        });
    }
}
