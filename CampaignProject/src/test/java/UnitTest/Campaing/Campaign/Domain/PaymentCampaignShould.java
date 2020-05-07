package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Campaign.CampaignStatus;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;
import Campaign.PaymentCampaign;
import Campaign.Domain.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentCampaignShould {
    Campaign campaign;
    PaymentCampaign paymentCampaign;
    Budget budget;


    @BeforeEach
    public void init() {
        budget = new Budget(9);
        campaign = new Campaign(new User(), budget);
        paymentCampaign = new PaymentCampaign();
    }

    @Test
    public void raise_error_when_campaign_status_is_finished() {
        Click click = new Click(new User(), Premium.PREMIUM);

        campaign.changeStatusCampaign(CampaignStatus.FINISHED);

        assertThrows(CampaignFinishedException.class, () -> paymentCampaign.chargefor(campaign, click));
    }

    @Test
    public void raise_error_when_campaign_status_is_pause() {
        Click click = new Click(new User(), Premium.PREMIUM);

        campaign.changeStatusCampaign(CampaignStatus.PAUSE);

        assertThrows(CampaignPauseException.class, () -> paymentCampaign.chargefor(campaign, click));
    }

    @Test
    public void check_charge_two_clicks_premiums_at_one_campaign() {
        Click click = new Click(new User(), Premium.PREMIUM);

        paymentCampaign.chargefor(campaign, click);
        paymentCampaign.chargefor(campaign, click);

        assertEquals("8,90", budget.toString());
    }

    @Test
    void check_charge_one_clicks_NO_premium_at_oneCampaign() {
        Click click = new Click(new User(), Premium.NO_PREMIUM);

        paymentCampaign.chargefor(campaign, click);

        assertEquals("8,99", budget.toString());
    }
}
