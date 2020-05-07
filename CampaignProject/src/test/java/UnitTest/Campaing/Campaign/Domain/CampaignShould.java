package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.User.UserID;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class CampaignShould {
    UserID userID;
    Budget budget ;

    @BeforeEach
    public void init(){
        userID = new UserID();
        budget = new Budget(9);

    }
    @Test
    public void check_that_two_campaign_do_not_have_the_same_id(){
        Campaign campaignA = new Campaign(userID,budget);
        Campaign campaignB = new Campaign(userID,budget);

        assertEquals(false,campaignA.equals(campaignB));
    }
}

