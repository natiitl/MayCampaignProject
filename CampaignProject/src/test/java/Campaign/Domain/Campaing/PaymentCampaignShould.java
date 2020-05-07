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
}
