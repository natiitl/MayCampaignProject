package Campaign;

import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Clicks.Click;

public class PaymentCampaign {
    public void chargefor(Campaign campaign, Click click) {
        campaign.budgetReduction(click);

    }
}
