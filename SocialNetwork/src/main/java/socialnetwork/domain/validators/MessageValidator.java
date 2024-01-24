package socialnetwork.domain.validators;

import socialnetwork.domain.Message;

import java.util.List;

public class MessageValidator implements Validator<Message>{

    /**
     * validate the message
     * @param entity
     * @throws ValidationException
     */
    @Override
    public void validate(Message entity) throws ValidationException {
        long from = entity.getFrom();
        List<Long> to = entity.getTo();

        String errors = "";

        if(to.contains(from))
            errors += "To ID and From ID can't be equal! ";

        if(errors.length() > 0)
            throw new ValidationException(errors);

    }
}


