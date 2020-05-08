package Campaign;

import Campaign.Domain.Ad.Ad;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Clicks.Click;

public class PaymentCampaign {

    public void chargedForOneClick(CampaignStandard campaignStandard, Click click) {
        campaignStandard.budgetReduction(click);
    }

    public void chargedFor(CampaignStandard campaignStandard, Ad ad){
        campaignStandard.chargedFor(ad);
    }
}
