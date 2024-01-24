package socialnetwork.repository.file;

import socialnetwork.domain.User;
import socialnetwork.domain.validators.Validator;

import java.util.List;

public class UserFile extends AbstractFileRepository<Long, User>{

    /**
     * constructor with parameters @param fileName, @param validator
     * @param fileName
     * @param validator
     */
    public UserFile(String fileName, Validator<User> validator) {
        super(fileName, validator);
    }

    /**
     * extractEntity
     * @param attributes
     * @return an entity of user having a specified list of @param attributes
     */
    @Override
    public User extractEntity(List<String> attributes) {
        User user = new User(attributes.get(1), attributes.get(2));
        user.setId(Long.parseLong(attributes.get(0)));

        return user;
    }

    /**
     * createEntityAsString
     * @param entity
     * @return an entity of user as string
     */
    @Override
    protected String createEntityAsString(User entity) {
        return entity.getId() + ";" + entity.getFirstName() + ";" + entity.getLastName();
    }
}
