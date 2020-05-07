package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.User.UserID;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClickShould {
    UserID userID;
    Click clickA ;
    Date parseDate;
    Date parseDate2;

    @BeforeEach
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");

        parseDate = dateFormat.parse("2020-07-27 20:50:44");
        parseDate2 = dateFormat.parse("2020-07-23 20:56:80");

        userID = new UserID();
        clickA = new Click(userID, Premium.PREMIUM,parseDate);

    }
    @Test
    public void check_that_two_clicks_do_not_have_the_same_id(){
        Click clickB = new Click(userID, Premium.PREMIUM, parseDate);

        assertEquals(false,clickB.equals(clickA));
    }
    @Test
    public void check_that_the_difference_between_clicks_is_greater_than_fifteen_seconds() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        parseDate = dateFormat.parse("2020-07-27 20:50:44");
        parseDate2 = dateFormat.parse("2020-07-23 20:56:10");

        Click clickB = new Click(userID, Premium.PREMIUM, parseDate);
        Click clickC = new Click(userID, Premium.PREMIUM, parseDate2);

        assertEquals(true,clickB.differenceGreaterFifteenSeconds(clickC));


    }
}
