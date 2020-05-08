package Campaign;

import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Clicks.ClickRepository;

public class PaymentCampaignApp {

    public void chargedFor(Campaign campaign, ClickRepository clickRepository) {
        campaign.chargedFor(clickRepository);
    }

}
