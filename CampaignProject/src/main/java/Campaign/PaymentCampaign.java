package Campaign;

import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Click;

public class PaymentCampaign {
    public void chargefor(Campaign campaign, Click click) {
        if(click.isPremium()){
            campaign.budgetReduction(0.05);

        }

    }
}
