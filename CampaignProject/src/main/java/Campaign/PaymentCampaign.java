package Campaign;

import Campaign.Domain.Ad.Ad;
import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Clicks.Click;

public class PaymentCampaign {

    public void chargedForOneClick(Campaign campaign, Click click) {
        campaign.budgetReduction(click);
    }

    public void chargedFor(Campaign campaign, Ad ad){
        campaign.chargedFor(ad);
    }
}
