package socialnetwork.service;

import socialnetwork.domain.Friendship;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.User;
import socialnetwork.domain.validators.FriendRequestValidator;
import socialnetwork.domain.validators.FriendshipValidator;
import socialnetwork.domain.validators.UserValidator;
import socialnetwork.repository.Repository;

import java.time.LocalDate;
import java.time.Month;

public class FriendshipService {
    private final Repository<Tuple<Long, Long>, Friendship> repositoryFriendship;
    private final Repository<Long, User> repositoryUser;
    private UserValidator userValidator;

    /**
     * constructor with parameters @param repositoryUser, @param repositoryFriendship
     * @param repositoryUser
     * @param repositoryFriendship
     */
    public FriendshipService(Repository<Tuple<Long, Long>, Friendship> repositoryFriendship, Repository<Long, User> repositoryUser){

        this.repositoryFriendship = repositoryFriendship;
        this.repositoryUser = repositoryUser;
    }

    /**
     * adds a friendships @param id1, @param id2 based on id
     * @param id1
     * @param id2
     */
    public void addFriendship(long id1, long id2){
        User userLeft = repositoryUser.findOne(id1);
        User userRight = repositoryUser.findOne(id2);
        if(userLeft == null || userRight == null)
            System.out.println("Id not in file! ");
        else{
            LocalDate localDate = LocalDate.now();
            Friendship friendship = new Friendship(localDate);
            Tuple<Long, Long> tuple = new Tuple<>(id1, id2);
            friendship.setId(tuple);
            Friendship friendship1 = repositoryFriendship.save(friendship);
            if(friendship1 == null) {
                userLeft.getFriends().add(userRight.getId());
                userRight.getFriends().add(userLeft.getId());
                System.out.println("Friendship added!");
            }
        }

    }

    /**
     * adds a friendships based on ids
     * @param ids
     */
    public void addFriendshipRev(Tuple<Long, Long> ids){
        LocalDate localDate = LocalDate.now();
        Friendship friendship = new Friendship(localDate);
        friendship.setId(ids);
        Friendship friendship1 = repositoryFriendship.save(friendship);
        if(friendship1 == null) {
            User userLeft = repositoryUser.findOne(friendship.getId().getLeft());
            User userRight = repositoryUser.findOne(friendship.getId().getRight());
            userLeft.getFriends().add(userRight.getId());
            userRight.getFriends().add(userLeft.getId());
            System.out.println("Friendship added!");
        }
        else
            System.out.println("Ids not in file!");

    }

    /**
     * deletes a friendship based on @param ids
     * @param ids
     */
    public void deleteFriendship(Tuple<Long, Long> ids){
        Friendship friendship = repositoryFriendship.delete(ids);
        if(friendship == null)
            System.out.println("No friendship with that id!");
        else
            System.out.println("Friendship deleted!");
    }

    /**
     * returns all friendships
     * @return Iterable
     */
    public Iterable<Friendship> getAll(){
        return repositoryFriendship.findAll();
    }

    /**
     * show all friendships of user based on @param id
     * @param id
     */
    public void showFriendshipsOfUser(long id){
        try {
            User user = repositoryUser.findOne(id);
            if(user != null){
            user.getFriends().forEach((x) -> {
                User friend = repositoryUser.findOne(x);
                System.out.print(friend.getFirstName() + "|");
                System.out.print(friend.getLastName() + "|");
                Friendship friendship = repositoryFriendship.findOne(new Tuple<>(id, x));
                if (friendship == null) {
                    friendship = repositoryFriendship.findOne(new Tuple<>(x, id));
                }
                System.out.println(friendship.getDate());
            });
        }
        else
                System.out.println("Id not valid! ");
        }
        catch(NullPointerException nullPointerException){
            System.out.println(nullPointerException.getMessage());
        }
    }

    /**
     * show all friendships of user based on @param id, @param month
     * @param id
     * @param month
     */
    public void showFriendshipsOfUserMonth(long id, Month month){
        try {
            User user = repositoryUser.findOne(id);
            if(user != null){
            user.getFriends().forEach((x) -> {
                User friend = repositoryUser.findOne(x);

                Friendship friendship = repositoryFriendship.findOne(new Tuple<>(id, x));
                if (friendship == null) {
                    friendship = repositoryFriendship.findOne(new Tuple<>(x, id));
                }

                if (friendship.getDate().getMonth() == month) {
                    System.out.print(friend.getFirstName() + "|");
                    System.out.print(friend.getLastName() + "|");
                    System.out.println(friendship.getDate());
                }
            });
        }
            else
                System.out.println("Id not valid!");
        }
        catch(NullPointerException nullPointerException){
            System.out.println(nullPointerException.getMessage());
        }
    }

}