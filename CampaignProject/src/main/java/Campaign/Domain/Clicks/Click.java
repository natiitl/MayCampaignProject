package Campaign.Domain.Clicks;

import Campaign.Domain.User.UserID;
import Campaign.Service.TimeServer;

import java.sql.Timestamp;
import java.util.Objects;

public class Click {
    private  int idClick;
    private final UserID userID;
    private final Premium premium;
    private Timestamp time;


    public Click(UserID userID, Premium premium, Timestamp timestamp) {
        this.userID = userID;
        this.premium = premium;
        this.time = timestamp;
        this.idClick++;
    }

    public double priceClick() {
        return premium.price;
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
