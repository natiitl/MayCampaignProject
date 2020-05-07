import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Campaign.Campaign;
import Campaign.*;
import Campaign.Domain.Click;
import Campaign.Domain.Premium;

import Campaign.Domain.User.User;
import org.junit.jupiter.api.Test;

public class CampaignAcceptanceShould {
    @Test
    public void CampaignAcceptance(){
        User user = new User();
        Click click = new Click(user, Premium.PREMIUM);
        Budget budget = new Budget(2);
        Campaign campaign = new Campaign(user,budget);
        PaymentCampaign paymentCampaign = new PaymentCampaign();
        paymentCampaign.chargefor(campaign,click);





    }
}
