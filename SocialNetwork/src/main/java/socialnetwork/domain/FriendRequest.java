package socialnetwork.domain;

public class FriendRequest extends Entity<Tuple<Long,Long>> {
    String status;

    /**
     * Constructor implicit
     * @param status
     */
    public FriendRequest(String status) {
        this.status = status;
    }

    /**
     * getStatus
     * @return the status of the friend request
     */
    public String getStatus() {
        return status;
    }

    /**
     * set the status of the friend request with @param status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * toString
     * @return friendshi request object as string
     */
    @Override
    public String toString() {
        return "Friend request: " +
                "to = " + super.getId().getLeft() +
                " from = "+ super.getId().getRight() +
                ", status = " + status;
    }
}
