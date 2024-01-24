package socialnetwork.service;

import socialnetwork.domain.Friendship;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.User;
import socialnetwork.domain.validators.FriendRequestValidator;
import socialnetwork.domain.validators.UserValidator;
import socialnetwork.repository.Repository;

public class UserService {
    private final Repository<Long, User> repositoryUser;
    private final Repository<Tuple<Long, Long>, Friendship> repositoryFriendship;
    private Long FreeId;

    /**
     * constructor with parameters @param repositoryUser, @param repositoryFriendship
     * @param repositoryUser
     * @param repositoryFriendship
     */
    public UserService(Repository<Long, User> repositoryUser, Repository<Tuple<Long, Long>, Friendship> repositoryFriendship) {

        this.repositoryUser = repositoryUser;
        this.repositoryFriendship = repositoryFriendship;
        FreeId = 0L;
    }
    /**
     * set the id used for save a user with the first id possible
     */
    private void checkId(){
        FreeId = 0L;
        int number = 0;
        int size = 0;
        boolean ok = true;
        for(User u: repositoryUser.findAll())
            size++;
        for(User user: repositoryUser.findAll()) {
            FreeId++;
            number++;
            if (!FreeId.equals(user.getId())) {
                ok = false;
                break;
            }
        }
        if(number == size && ok == true)
            FreeId++;

    }

    /**
     * adds a user @param firstName, @param lastName
     * @param firstName
     * @param lastName
     */
    public void addUser(String firstName, String lastName){
        User user = new User(firstName, lastName);
        checkId();
        user.setId(FreeId);
        repositoryUser.save(user);
    }

    /**
     * deletes a user based on @param id and all the associated friendships
     * @param id
     */
    public void deleteUserCascade(long id) {
        User user = repositoryUser.delete(id);
        FreeId = 0L;
        if (user != null) {
            user.getFriends().forEach((x) -> {
                if (repositoryFriendship.findOne(new Tuple<>(x, id)) != null) {
                    repositoryFriendship.delete(new Tuple<>(x, id));
                    FreeId = 0L;
                }

                if (repositoryFriendship.findOne(new Tuple<>(id, x)) != null) {
                    repositoryFriendship.delete(new Tuple<>(id, x));
                    FreeId = 0L;
                }
            });
            for(User userAll: repositoryUser.findAll())
            {
                if(userAll.getFriends().contains(id))
                    userAll.getFriends().remove(id);
            }
            System.out.println("User deleted!");
        }
        else
            System.out.println("User does not exist!");
    }

    /**
     * gets all users
     * @return an Iterable
     */
    public Iterable<User> getAll(){

        return repositoryUser.findAll();

    }

}