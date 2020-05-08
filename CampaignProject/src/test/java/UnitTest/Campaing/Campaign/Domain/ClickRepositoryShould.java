package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.User.UserID;
import Campaign.Domain.User.UserRepository;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClickRepositoryShould {

    @Test
    public void check_that_if_the_difference_between_clicks_is_greater_than_fifteen_seconds_the_click_not_added_to_the_list() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date parseDate = dateFormat.parse("2020-07-27 20:50:44");
        Date parseDate2 = dateFormat.parse("2020-07-27 20:50:45");
        UserID userID = new UserID();

        Click clickA = new Click(userID, Premium.PREMIUM, parseDate);
        Click clickB = new Click(userID, Premium.PREMIUM, parseDate2);
        ClickRepository clickRepositoryActual = new ClickRepository();
        ClickRepository clickRepositoryExpected = new ClickRepository();
        clickRepositoryActual.add(clickA);
        clickRepositoryActual.add(clickB);
        clickRepositoryExpected.add(clickA);

        assertEquals(clickRepositoryExpected, clickRepositoryActual);
    }

    @Test
    public void check_if_find_fraudulentClicks_with_userlist_and_one_date() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");


        Date date = dateFormat.parse("2020-07-27 20:50:44");

        UserRepository userRepository = new UserRepository();
        UserID userID = new UserID();
        userRepository.addUser(userID);
        Click click = new Click(userID, Premium.PREMIUM, date);
        ClickRepository  clickRepository = new ClickRepository();
        clickRepository.add(click);

        ClickRepository clickRepositoryActual = new ClickRepository();
        clickRepositoryActual = clickRepository.findFraudulentClicks(userRepository,date);
        ClickRepository clickRepositoryExpected = new ClickRepository();
        clickRepositoryExpected.add(click);

        assertEquals(clickRepositoryExpected,clickRepositoryActual);

    }

}
