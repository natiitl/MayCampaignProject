package Campaign;

import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;

public class PaymentCampaign {

    public void chargedFor(Campaign campaign, ClickRepository clickRepository) {
        campaign.chargedFor(clickRepository);
    }

}
