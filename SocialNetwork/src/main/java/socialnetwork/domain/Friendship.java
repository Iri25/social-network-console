package socialnetwork.domain;

import java.time.LocalDate;


public class Friendship extends Entity<Tuple<Long,Long>> {
    LocalDate date;

    /**
     * Constructor implicit
     * @param date
     */
    public Friendship(LocalDate date) {
        this.date = date;
    }

    /**
     * getDate
     * @return the date when the friendship was created
     */
    public LocalDate getDate() {

        return date;
    }

    /**
     * toString
     * @return friendship object as string
     */
    public String toString(){
        return "Friendship " +
                "between " + super.getId().getLeft() +
                " and " + super.getId().getRight() +
                ", date: " + date;
    }

}