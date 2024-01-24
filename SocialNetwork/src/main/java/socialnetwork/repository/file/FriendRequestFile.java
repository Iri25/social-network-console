package socialnetwork.repository.file;

import socialnetwork.domain.FriendRequest;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.validators.Validator;

import java.util.List;

public class FriendRequestFile  extends AbstractFileRepository<Tuple<Long, Long>, FriendRequest>{

    /**
     * constructor with parameters @param fileName, @param validator
     * @param fileName
     * @param validator
     */
    public FriendRequestFile(String fileName, Validator<FriendRequest> validator) {
        super(fileName, validator);
    }

    /**
     * extractEntity
     * @param attributes
     * @return an entity of friend request having a specified list of @param attributes
     */
    @Override
    public FriendRequest extractEntity(List<String> attributes) {
        FriendRequest friendRequest = new FriendRequest(attributes.get(2));
        friendRequest.setId(new Tuple<Long, Long>(Long.parseLong(attributes.get(0)), Long.parseLong(attributes.get(1))));

        return friendRequest;
    }

    /**
     * createEntityAsString
     * @param entity
     * @return an entity of friend request as string
     */
    @Override
    protected String createEntityAsString(FriendRequest entity) {
        return entity.getId().getLeft() + ";" + entity.getId().getRight() + ";" + entity.getStatus();
    }
}
