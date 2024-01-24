package socialnetwork.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Entity<Long>{
    private String firstName;
    private String lastName;
    private List<Long> friends;

    /**
     * Constructor with parameters
     * @param firstName
     * @param lastName
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        friends = new ArrayList<>();
    }

    /**
     * getFirstName
     * @return the first name of user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set the first name of user with @param firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getLastName
     * @return the last name of user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set the last name of user with @param lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getFriends
     * @return user's friends list
     */
    public List<Long> getFriends(){
        return friends;
    }

    /**
     * set user's friends list with @param friends
     * @param friends
     */
    public void setFriends(List<Long> friends) { this.friends = friends; }

    /**
     * toString
     * @return user object as string
     */
    @Override
    public String toString() {
        return "User: " +
                "id = " + getId()  +
                ", first name = " + firstName +
                ", last name = " + lastName +
                ", friends = " + friends;
    }

    /**
     * equals
     * @param object
     * @return a value of boolean if there are identical objects
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof User))
            return false;
        User that = (User) object;
        return getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getFriends().equals(that.getFriends());
    }

    /**
     * hashCode
     * @return the hashcode of object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getFriends());
    }
}