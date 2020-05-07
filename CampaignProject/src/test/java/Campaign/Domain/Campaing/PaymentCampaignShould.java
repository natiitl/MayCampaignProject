package Campaign.Domain.Campaing;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Campaign.CampaignStatus;
import Campaign.Domain.Click;
import Campaign.Domain.Premium;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;
import Campaign.PaymentCampaign;
import Campaign.Domain.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentCampaignShould {


    @Test
    public void raise_error_when_campaign_status_is_finished(){

        Click click = new Click(new User(), Premium.PREMIUM);
        Campaign campaign = new Campaign(new User(),new Budget(9));
        PaymentCampaign paymentCampaign = new PaymentCampaign();
        campaign.changeStatusCampaign(CampaignStatus.FINISHED);

        assertThrows(CampaignFinishedException.class, ()->paymentCampaign.chargefor(campaign,click));
    }
    @Test
    public void raise_error_when_campaign_status_is_pause(){

        Click click = new Click(new User(), Premium.PREMIUM);
        Campaign campaign = new Campaign(new User(),new Budget(9));
        PaymentCampaign paymentCampaign = new PaymentCampaign();
        campaign.changeStatusCampaign(CampaignStatus.PAUSE);

        assertThrows(CampaignPauseException.class, ()->paymentCampaign.chargefor(campaign,click));
    }
    @Test
    public void check_charge_two_clicks_premiums_at_one_campaign(){
        Click click = new Click(new User(), Premium.PREMIUM);
        Budget budget = new Budget(9);
        Campaign campaign = new Campaign(new User(),budget);
        PaymentCampaign paymentCampaign = new PaymentCampaign();

        paymentCampaign.chargefor(campaign,click);
        paymentCampaign.chargefor(campaign,click);

        assertEquals("8,90",budget.toString());
    }
}
