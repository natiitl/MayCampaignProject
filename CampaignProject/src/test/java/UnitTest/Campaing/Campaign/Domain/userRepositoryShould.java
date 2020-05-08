package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.User.UserID;
import Campaign.Domain.User.UserRepository;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class userRepositoryShould {
    @Test
    public void check_if_click_is_my_click() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date date = dateFormat.parse("2020-07-27 20:50:44");
        UserRepository userRepository = new UserRepository();
        UserID userID = new UserID();
        userRepository.addUser(userID);
        Click click = new Click(userID, Premium.PREMIUM,date);
        Click click2 = new Click(new UserID(),Premium.PREMIUM,date);

        assertEquals(true,userRepository.isMyClick(click,date));
        assertEquals(false,userRepository.isMyClick(click2,date));
    }



}
