package Campaign;

import Campaign.Domain.Ad.Ad;
import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Clicks.Click;

public class PaymentCampaign {

    public void chargedForOneClick(Campaign campaign, Click click) {
        campaign.chargedFor(click);
    }

    public void chargedFor(CampaignStandard campaignStandard, Ad ad){
        campaignStandard.chargedFor(ad);
    }
}
