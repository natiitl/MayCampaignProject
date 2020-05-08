package Campaign.Domain.Clicks;

import Campaign.Domain.User.UserID;
import java.util.Date;
import java.util.Objects;


public class Click {
    private int idClick;
    private final UserID userID;
    private Premium premium;
    private Date date;


    public Click(UserID userID, Premium premium, Date date) {
        this.userID = userID;
        this.premium = premium;
        this.idClick = (int)( Math.random()*10000);
        this.date = date;

    }

    public double priceTopClick() {
        return premium.priceTop;
    }
    public double priceStandardClick() {
        return premium.priceStandard;
    }

    public boolean differenceGreaterFifteenSeconds(Click click) {


        long diff = date.getTime() - click.date.getTime();
        if (diff != 0) {
            diff = diff / 1000;
        }
        if (Math.abs(diff) <= 15) {
            return false;
        }
        return true;

    }

    public boolean isSameUser(Click click) {
        return this.userID.equals(click.userID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Click click = (Click) o;
        return idClick == click.idClick;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClick);
    }


    public boolean checkValidClick(Click click) {

        return !(isSameUser(click)&&!differenceGreaterFifteenSeconds(click));
    }

    public boolean isSameUserID(UserID userID) {
        return this.userID.equals(userID);
    }

    public boolean dateIsEarlier(Date date) {
        long diff = date.getTime() - this.date.getTime();
        if(diff<=0){
            return true;
        }
        return false;
    }
}
