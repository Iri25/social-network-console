package socialnetwork.domain.validators;

import socialnetwork.domain.Friendship;

public class FriendshipValidator implements Validator<Friendship> {

    /**
     * validate the friendships
     * @param entity
     * @throws ValidationException
     */
    @Override
    public void validate(Friendship entity) throws ValidationException {
        long left = entity.getId().getLeft();
        long right = entity.getId().getRight();

        String errors = "";

        if(left < 0)
            errors += "Left ID can't be negative! ";
        if(right < 0)
            errors += "Right ID can't be negative!";
        if(left == right)
            errors += "Left ID and Right ID can't be equal! ";

        if(errors.length() > 0)
            throw new ValidationException(errors);

    }
}