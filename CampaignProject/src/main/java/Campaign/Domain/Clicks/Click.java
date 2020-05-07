package Campaign.Domain.Clicks;

import Campaign.Domain.User.UserID;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Click {
    private  int idClick;
    private final UserID userID;
    private final Premium premium;
    private Date date;


    public Click(UserID userID, Premium premium, Date date) {
        this.userID = userID;
        this.premium = premium;
        this.idClick++;
        this.date = date;

    }

    public double priceClick() {
        return premium.price;
    }

    public boolean differenceGreaterFifteenSeconds(Click click) {
        long diff = date.getTime() - click.date.getTime();
        long diffSeconds = Math.abs(TimeUnit.SECONDS.toSeconds(diff));
        if(diffSeconds<=15){
            return false;
        }
        return true;

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


}
