package Campaign.Domain.User;

import Campaign.Domain.Clicks.Click;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository {
    List<UserID> userIDList;

    public UserRepository() {
        this.userIDList = new ArrayList<>();
    }

    public void addUser(UserID userID){
        if(!userIDList.contains(userID)) {
            userIDList.add(userID);
        }
    }

    public boolean isMyClick(Click click, Date date) {
        for (UserID userID:userIDList) {
            if ( click.isSameUserID(userID)){
                return true;
            }
            
        }                return true;

    }
}
