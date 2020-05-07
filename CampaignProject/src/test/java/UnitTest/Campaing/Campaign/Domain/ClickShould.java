package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.User.UserID;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class ClickShould {
    UserID userID;
    Click clickA ;

    @BeforeEach
    public void init(){
        userID = new UserID();
        clickA = new Click(userID, Premium.PREMIUM);

    }
    @Test
    public void check_that_two_clicks_do_not_have_the_same_id(){
        Click clickB = new Click(userID, Premium.PREMIUM);

        assertEquals(false,clickB.equals(clickA));
    }
}
