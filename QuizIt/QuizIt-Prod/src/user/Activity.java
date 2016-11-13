package user;

/**
 * Created by scottparsons on 3/9/16.
 */
public class Activity {
    public int userId;
    public String type;
    public String date;
    public int linkId;

    public Activity(int userId, String type, String date, int linkId) {
        this.userId = userId;
        this.type = type;
        this.date = date;
        this.linkId = linkId;
    }
}
