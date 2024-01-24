package socialnetwork.repository.file;

import socialnetwork.domain.Message;
import socialnetwork.domain.validators.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageFile extends AbstractFileRepository<Long, Message>{

    /**
     * constructor with parameters @param fileName, @param validator
     * @param fileName
     * @param validator
     */
    public MessageFile(String fileName, Validator<Message> validator) {
        super(fileName, validator);
    }

    /**
     * extractEntity
     * @param attributes
     * @return an entity of message having a specified list of @param attributes
     */
    @Override
    public Message extractEntity(List<String> attributes) {
        Message message = new Message(Long.parseLong(attributes.get(1)), attributes.get(3), LocalDateTime.parse(attributes.get(4)));
        message.setId(Long.parseLong(attributes.get(0)));

        List<Long> listTo = new ArrayList<>();
        String to = attributes.get(2);
        String aux;
        to = to.replaceAll("\\D+","");
        for(int i = 0; i < to.length(); i++){
            aux = String.valueOf(to.charAt(i));
            listTo.add(Long.parseLong(aux));
        }
        message.setTo(listTo);

        if(!attributes.get(5).equals("null"))
        {
            message.setReply(Long.parseLong(attributes.get(5)));
        }

        return message;
    }

    /**
     * createEntityAsString
     * @param entity
     * @return an entity of message as string
     */
    @Override
    protected String createEntityAsString(Message entity) {
        return entity.getId() + ";" + entity.getFrom() + ";" + entity.getTo() + ";" + entity.getMessage() + ";"+entity.getDate() + ";" + entity.getReply();
    }
}
