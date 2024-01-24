package socialnetwork.domain.validators;

import socialnetwork.domain.FriendRequest;
import socialnetwork.domain.Message;

import java.util.List;

public class FriendRequestValidator implements Validator<FriendRequest>{

    /**
     * validate the friend request
     * @param entity
     * @throws ValidationException
     */
    @Override
    public void validate(FriendRequest entity) throws ValidationException {
        long left = entity.getId().getLeft();
        long right = entity.getId().getRight();
        String status = entity.getStatus();

        String errors = "";

        if(left < 0)
            errors += "Left ID can't be negative! ";
        if(right < 0)
            errors += "Right ID can't be negative! ";
        if(left == right)
            errors += "Left ID and Right ID can't be equal! ";

        if(errors.length() > 0)
            throw new ValidationException(errors);

    }
}

