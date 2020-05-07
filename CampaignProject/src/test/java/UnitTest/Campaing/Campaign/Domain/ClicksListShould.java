package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClicksList;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.User.UserID;
import Campaign.Exception.ClickNotValueException;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClicksListShould {

    UserID userID;
    Click clickA;

    @Test
    public void check_that_if_the_difference_between_clicks_is_greater_than_fifteen_seconds_the_click_not_added_to_the_list() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date parseDate = dateFormat.parse("2020-07-27 20:50:44");
        Date parseDate2 = dateFormat.parse("2020-07-27 20:50:45");
        Click clickA = new Click(userID, Premium.PREMIUM, parseDate);
        Click clickB = new Click(userID, Premium.PREMIUM, parseDate2);
        ClicksList clicksListActual = new ClicksList();
        ClicksList clicksListExpected = new ClicksList();
        clicksListActual.add(clickA);
        clicksListActual.add(clickB);
        clicksListExpected.add(clickA);

        assertEquals(clicksListExpected,clicksListActual);
    }
}
