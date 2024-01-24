package socialnetwork.userinterface;

import socialnetwork.domain.Tuple;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.service.FriendRequestService;
import socialnetwork.service.MessageService;
import socialnetwork.service.FriendshipService;
import socialnetwork.service.UserService;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final MessageService messageService;
    private final FriendRequestService friendRequestService;

    /**
     * constructor with parameters @param userService, @param friendshipService, @param messageService, @param friendRequestService
     * @param userService
     * @param friendshipService
     * @param messageService
     * @param friendRequestService
     */
    public UserInterface(UserService userService, FriendshipService friendshipService, MessageService messageService, FriendRequestService friendRequestService){

        this.userService = userService;
        this.friendshipService = friendshipService;
        this.messageService = messageService;
        this.friendRequestService = friendRequestService;
    }

    /**
     * show menu
     */
    public void showMenu(){
        System.out.println();
        System.out.println("--------------------------MENU--------------------------");
        System.out.println("|   1. Add user                                        |");
        System.out.println("|   2. Delete user                                     |");
        System.out.println("|   3. Show users                                      |");
        System.out.println("|   4. Add friendship                                  |");
        System.out.println("|   5. Delete friendship                               |");
        System.out.println("|   6. Show friendships                                |");
        System.out.println("|   7. Show friendships of user                        |");
        System.out.println("|   8. Show friendships of user by month               |");
        System.out.println("|   9. Send message                                    |");
        System.out.println("|   10. Reply message                                  |");
        System.out.println("|   11. Delete message                                 |");
        System.out.println("|   12. Show messages                                  |");
        System.out.println("|   13. Show messages between two users                |");
        System.out.println("|   14. Send friend request                            |");
        System.out.println("|   15. Respond to friend requests                     |");
        System.out.println("|   16. Show friend requests                           |");
        System.out.println("|   0. Exit                                            |");
        System.out.println("--------------------------------------------------------");
        System.out.println();
    }

    /**
     * add a user
     */
    public void addUser()  {
        try {
            Scanner in = new Scanner(System.in);

            System.out.println("Insert first name: ");
            String firstName = in.nextLine();
            System.out.println("Insert last name: ");
            String lastName = in.nextLine();
            userService.addUser(firstName, lastName);
            System.out.println("User added!");
        }
        catch(ValidationException validationException){
            System.out.println(validationException.getLocalizedMessage());
        }
        catch (InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * delete a user
     */
    public void deleteUser(){
        try {
            showUsers();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert an id: ");
            long id = in.nextLong();
            userService.deleteUserCascade(id);
        }
        catch(InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * show all users
     */
    public void showUsers(){

        userService.getAll().forEach(System.out::println);
    }

    /**
     * add a friendship
     */
    public void addFriendship(){
        try {
            showUsers();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert first id: ");
            long id1 = in.nextLong();
            System.out.println("Insert second id: ");
            long id2 = in.nextLong();
            friendshipService.addFriendship(id1, id2);
        }
        catch(InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * delete a friendship
     */
    public void deleteFriendship(){
        try {
            showFriendships();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert first id: ");
            long id1 = in.nextLong();
            System.out.println("Insert second id: ");
            long id2 = in.nextLong();
            Tuple<Long, Long> tuple = new Tuple<>(id1, id2);
            friendshipService.deleteFriendship(tuple);
        }
        catch(InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * show all friendships
     */
    public void showFriendships(){
        friendshipService.getAll().forEach(System.out::println);
    }

    /**
     * show all friendships of user
     */
    public void showFriendshipsOfUser(){
        try {
            showUsers();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert the id of user: ");
            long id = in.nextLong();

            friendshipService.showFriendshipsOfUser(id);
        }
        catch(InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * show all friendships of user by month
     */
    public void showFriendshipsOfUserMonth(){
        try {
            showUsers();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert the id of user: ");
            long id = in.nextLong();
            System.out.println("Insert month (1 - 12): ");
            int month = in.nextInt();
            friendshipService.showFriendshipsOfUserMonth(id, Month.of(month));
        }
        catch(InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
        catch (DateTimeException dateTimeException){
            System.out.println("Invalided month type!");
        }
    }

    /**
     * send a message
     */
    public void sendMessage(){
        try {
            showUsers();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert id from: ");
            long from = in.nextLong();
            in.nextLine();
            List<Long> to = new ArrayList<>();
            System.out.println("Insert the number for to: ");
            int number = in.nextInt();
            in.nextLine();
            System.out.println("Insert to: ");
            for (int i = 0; i < number; i++) {
                long ids = in.nextLong();
                to.add(ids);
                in.nextLine();
            }
            System.out.println("Insert message: ");
            String message = in.nextLine();
            LocalDateTime dateTime = LocalDateTime.now();
            messageService.sendMessage(from, to, message, dateTime);
        }
        catch (InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * send reply a message
     */
    public void sendReply(){
        try {
            showMessages();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert id message: ");
            long idMessage = in.nextLong();
            in.nextLine();
            System.out.println("Insert from: ");
            long from = in.nextLong();
            in.nextLine();
            List<Long> to = new ArrayList<>();
            System.out.println("Insert id to: ");
            long idP = in.nextLong();
            to.add(idP);
            in.nextLine();
            System.out.println("Insert message: ");
            String message = in.nextLine();
            LocalDateTime dateTime = LocalDateTime.now();
            messageService.sendReply(idMessage, from, to, message, dateTime);
        }
        catch(InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }

    }

    /**
     * delete a message
     */
    public void deleteMessage(){
        try {
            showMessages();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert id message: ");
            long id = in.nextLong();
            in.nextLine();
            messageService.deleteMessage(id);
        }
        catch (InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * show all messages between two users
     */
    public void showMessagesBetweenTwoUsers(){
        try {
            showUsers();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert to: ");
            long to = in.nextLong();
            in.nextLine();
            System.out.println("Insert from: ");
            long from = in.nextLong();
            in.nextLine();
            messageService.showMessagesBetweenTwoUsers(to, from);
        }
        catch (InputMismatchException inputMismatchException)
        {
            System.out.println("Invalided date type!");
        }
    }

    /**
     * show all messages
     */
    public void showMessages(){
        messageService.getAll().forEach(System.out::println);
    }

    public void sendFriendRequest(){
        try {
            showUsers();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert to: ");
            long to = in.nextLong();
            in.nextLine();
            System.out.println("Insert from: ");
            long from = in.nextLong();
            in.nextLine();
            Tuple<Long, Long> ids = new Tuple<>(to, from);
            String status = "pending";
            friendRequestService.sendFriendRequest(ids, status);
        }
        catch (InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * respond to friend request
     */
    public void respondToFriendRequest(){
        try{
            showFriendRequest();
            System.out.println();
            Scanner in = new Scanner(System.in);

            System.out.println("Insert to: ");
            long to = in.nextLong();
            in.nextLine();
            System.out.println("Insert from: ");
            long from = in.nextLong();
            in.nextLine();
            System.out.println("Insert the answer (yes/no): ");
            String response = in.next();
            in.nextLine();
            friendRequestService.respondToFriendRequest(new Tuple<>(to,from), response);
        }
        catch (InputMismatchException inputMismatchException){
            System.out.println("Invalided date type!");
        }
    }

    /**
     * show all friend requests
     */
    public void showFriendRequest(){
        friendRequestService.getAll().forEach(System.out::println);
    }

    /**
     * console run
     */
    public void run(){
        boolean ok = true;
        while(ok){
            showMenu();
            Scanner in = new Scanner(System.in);
            System.out.println("Insert the number of option: ");
            int option = in.nextInt();
            System.out.println();
            switch(option){
                case 1:
                    addUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    showUsers();
                    break;
                case 4:
                    addFriendship();
                    break;
                case 5:
                    deleteFriendship();
                    break;
                case 6:
                    showFriendships();
                    break;
                case 7:
                    showFriendshipsOfUser();
                    break;
                case 8:
                    showFriendshipsOfUserMonth();
                    break;
                case 9:
                    sendMessage();
                    break;
                case 10:
                    sendReply();
                    break;
                case 11:
                    deleteMessage();
                    break;
                case 12:
                    showMessages();
                    break;
                case 13:
                    showMessagesBetweenTwoUsers();
                    break;
                case 14:
                    sendFriendRequest();
                    break;
                case 15:
                    respondToFriendRequest();
                    break;
                case 16:
                    showFriendRequest();
                    break;
                case 0:
                    ok = false;
                    break;
                default:
                    System.out.println("The option is invalid!!");

            }

        }
    }
}
