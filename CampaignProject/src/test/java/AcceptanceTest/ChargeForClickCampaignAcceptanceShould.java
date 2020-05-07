package AcceptanceTest;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Campaign.Campaign;
import Campaign.*;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;

import Campaign.Domain.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChargeForClickCampaignAcceptanceShould {
    @Test
    public void ChargeForClickCampaignAcceptance(){
        User user = new User();
        Click clickPremium = new Click(user, Premium.PREMIUM);
        Click clickNoPremium = new Click(user, Premium.NO_PREMIUM);
        Budget budget = new Budget(2);
        Campaign campaign = new Campaign(user,budget);
        PaymentCampaign paymentCampaign = new PaymentCampaign();

        paymentCampaign.chargefor(campaign,clickPremium);
        paymentCampaign.chargefor(campaign,clickNoPremium);

        assertEquals("1,94",budget.toString());





    }
}
