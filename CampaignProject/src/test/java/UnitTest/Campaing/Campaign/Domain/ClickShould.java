package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.User.UserID;
import Campaign.Service.TimeServer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClickShould {
    UserID userID;
    Click clickA ;
    TimeServer timeServer = new TimeServer();

    Timestamp timestamp;


    @BeforeEach
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss:SSS");

        Date parsedTimeStamp = dateFormat.parse("2020-07-23 20:56:44:080");

        timestamp = new Timestamp(parsedTimeStamp.getTime());

        userID = new UserID();
        clickA = new Click(userID, Premium.PREMIUM,timeServer.getDate());

    }
    @Test
    public void check_that_two_clicks_do_not_have_the_same_id(){
        Click clickB = new Click(userID, Premium.PREMIUM, timeServer.getDate());

        assertEquals(false,clickB.equals(clickA));
    }
}
