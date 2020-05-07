package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Campaign.CampaignStatus;
import Campaign.Domain.User.UserID;
import Campaign.Exception.CampaignFinishedException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CampaignShould {
    UserID userID;
    Budget budget;
    Campaign campaignA;

    @BeforeEach
    public void init() {
        userID = new UserID();
        budget = new Budget(9);
        campaignA = new Campaign(userID, budget);
    }

    @Test
    public void check_that_two_campaign_do_not_have_the_same_id() {

        Campaign campaignB = new Campaign(userID, budget);

        assertEquals(false, campaignB.equals(campaignA));
    }

    @Test
    public void raise_error_when_you_want_to_change_the_status_when_it_is_already_finished() {
        Campaign campaign = new Campaign(new UserID(), new Budget(9));
        campaign.changeStatusCampaign(CampaignStatus.FINISHED);

        assertThrows(CampaignFinishedException.class, () -> campaign.changeStatusCampaign(CampaignStatus.ACTIVE));
    }

}

