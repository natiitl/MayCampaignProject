package AcceptanceTest;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Campaign.Campaign;
import Campaign.*;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;

import Campaign.Domain.User.UserID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChargeForClickCampaignAcceptanceShould {
    @Test
    public void ChargeForClickCampaignAcceptance(){
        UserID userID = new UserID();
        CustomerID customerID = new CustomerID();

        Click clickPremium = new Click(userID, Premium.PREMIUM);
        Click clickNoPremium = new Click(userID, Premium.NO_PREMIUM);

        Budget budget = new Budget(2);
        Campaign campaign = new Campaign(customerID,budget);

        PaymentCampaign paymentCampaign = new PaymentCampaign();

        paymentCampaign.chargefor(campaign,clickPremium);
        paymentCampaign.chargefor(campaign,clickNoPremium);

        assertEquals("1,94",budget.toString());





    }
}
