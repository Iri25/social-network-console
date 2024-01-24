package socialnetwork;

import socialnetwork.config.ApplicationContext;
import socialnetwork.domain.*;
import socialnetwork.domain.validators.FriendRequestValidator;
import socialnetwork.domain.validators.MessageValidator;
import socialnetwork.domain.validators.FriendshipValidator;
import socialnetwork.domain.validators.UserValidator;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.FriendRequestFile;
import socialnetwork.repository.file.MessageFile;
import socialnetwork.repository.file.FriendshipFile;
import socialnetwork.repository.file.UserFile;
import socialnetwork.service.FriendRequestService;
import socialnetwork.service.MessageService;
import socialnetwork.service.FriendshipService;
import socialnetwork.service.UserService;
import socialnetwork.userinterface.UserInterface;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
//        String fileNameUsers = ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.users");
//        String fileNameFriendships = ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.friendships");
//        String fileNameMessages = ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.messages");
//        String fileNameFriendRequests = ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.friendRequests");
        String fileNameUsers = "data/users.csv";
        String fileNameFriendships = "data/friendships.csv";
        String fileNameMessages = "data/messages.csv";
        String fileNameFriendRequests = "data/friendRequests.csv";

        Repository<Long, User> userFileRepository = new UserFile(fileNameUsers, new UserValidator());
        Repository<Tuple<Long, Long>, Friendship> friendshipFileRepository = new FriendshipFile(fileNameFriendships, new FriendshipValidator(), userFileRepository);
        Repository<Long, Message> messageFileRepository = new MessageFile(fileNameMessages, new MessageValidator());
        Repository<Tuple<Long, Long>, FriendRequest> friendRequestFileRepository = new FriendRequestFile(fileNameFriendRequests, new FriendRequestValidator());

        UserService userService = new UserService(userFileRepository, friendshipFileRepository);
        FriendshipService friendshipService = new FriendshipService(friendshipFileRepository, userFileRepository);
        MessageService messageService = new MessageService(friendshipFileRepository, userFileRepository, messageFileRepository);
        FriendRequestService friendRequestService = new FriendRequestService(friendRequestFileRepository, friendshipFileRepository, userFileRepository);

        UserInterface userInterface = new UserInterface(userService, friendshipService, messageService, friendRequestService);
        userInterface.run();

    }
}


