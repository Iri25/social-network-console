package socialnetwork.repository.file;

import socialnetwork.domain.Friendship;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.User;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository;

import java.time.LocalDate;
import java.util.List;

public class FriendshipFile extends AbstractFileRepository<Tuple<Long, Long>, Friendship> {
    private final Repository<Long, User> repositoryUser;

    /**
     * constructor with paramaters @param fileName, @param validator, @param repositoryUser
     * @param fileName
     * @param validator
     * @param repositoryUser
     */
    public FriendshipFile(String fileName, Validator<Friendship> validator, Repository<Long, User> repositoryUser) {

        super(fileName, validator);
        this.repositoryUser = repositoryUser;
        friendList();
    }

    /**
     * extractEntity
     * @param attributes
     * @return an entity of friendships having a specified list of @param attributes
     */
    @Override
    public Friendship extractEntity(List<String> attributes) {
        Friendship friendship = new Friendship(LocalDate.parse(attributes.get(2)));
        friendship.setId(new Tuple<Long, Long>(Long.parseLong(attributes.get(0)), Long.parseLong(attributes.get(1))));

        return friendship;
    }

    /**
     * createEntityAsString
     * @param entity
     * @return an entity of friendship as string
     */
    @Override
    protected String createEntityAsString(Friendship entity) {

        return entity.getId().getLeft() + ";" + entity.getId().getRight() + ";" + entity.getDate();
    }

    /**
     * list of friendships of user when load data
     */
    private void friendList(){
        entities.forEach((id, e)->{
            User userLeft = repositoryUser.findOne(id.getLeft());
            User userRight = repositoryUser.findOne(id.getRight());
            userLeft.getFriends().add(userRight.getId());
            userRight.getFriends().add(userLeft.getId());
        });
    }

}