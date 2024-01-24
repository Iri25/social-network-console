package socialnetwork.service;

import socialnetwork.domain.FriendRequest;
import socialnetwork.domain.Friendship;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.User;
import socialnetwork.repository.Repository;

import java.time.LocalDate;

public class FriendRequestService {
    private final Repository<Tuple<Long,Long>, FriendRequest> repositoryFriendRequest;
    private final Repository<Tuple<Long,Long>, Friendship> repositoryFriendship;
    private final Repository<Long, User> repositoryUser;

    /**
     *
     * constructor with parameters @param repositoryFriendRequest, @param repositoryFriendship, @param repositoryUser
     * @param repositoryFriendRequest
     * @param repositoryFriendship
     * @param repositoryUser
     */
    public FriendRequestService(Repository<Tuple<Long, Long>, FriendRequest> repositoryFriendRequest, Repository<Tuple<Long, Long>, Friendship> repositoryFriendship, Repository<Long, User> repositoryUser) {
        this.repositoryFriendRequest = repositoryFriendRequest;
        this.repositoryFriendship = repositoryFriendship;
        this.repositoryUser = repositoryUser;
    }

    /**
     * send a friend request based on @param ids with an answer @param status
     * @param ids
     * @param status
     */
    public void sendFriendRequest(Tuple<Long, Long> ids, String status){
        if(repositoryUser.findOne(ids.getLeft()) == null || repositoryUser.findOne(ids.getRight()) == null) {
            System.out.println("Id not valid!");
        }
        else{
            FriendRequest friendRequest = new FriendRequest(status);
            friendRequest.setId(ids);
            repositoryFriendRequest.save(friendRequest);
            System.out.println("Friend request send!");
        }
    }

    /**
     * respond to friend request based on @param ids with an answer @param response
     * @param ids
     * @param response
     */
    public void respondToFriendRequest(Tuple<Long,Long> ids, String response){
        if(repositoryUser.findOne(ids.getLeft()) == null || repositoryUser.findOne(ids.getRight()) == null || ids.getLeft().equals(ids.getRight())) {
            System.out.println("Id not valid!");
        }
        else {
            repositoryFriendRequest.delete(new Tuple<> (ids.getRight(), ids.getLeft()));
            if (response.equals("yes")) {
                FriendRequest friendRequest = new FriendRequest("approved");
                friendRequest.setId(ids);
                repositoryFriendRequest.save(friendRequest);

                LocalDate localDate = LocalDate.now();
                Friendship friendship = new Friendship(localDate);
                friendship.setId(ids);
                repositoryFriendship.save(friendship);
                System.out.println("Friendship accepted!");
            }
            else if (response.equals("no")) {
                FriendRequest friendRequest = new FriendRequest("rejected");
                friendRequest.setId(ids);
                repositoryFriendRequest.save(friendRequest);
                System.out.println("Friendship rejected!");
            }
            else System.out.println("Response not valid!");
        }
    }

    /**
     * gets all friend request
     * @return an Iterable
     */
    public Iterable<FriendRequest> getAll(){
        return repositoryFriendRequest.findAll();
    }
}
