package AcceptanceTest;

import Campaign.Domain.Budget.BudgetStandard;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.*;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;

import Campaign.Domain.User.UserID;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChargeForClickCampaignStandardAcceptanceShould {

    @Test
    public void ChargeForClickCampaignAcceptance() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss:SSS");

        Date parsedTimeStamp = dateFormat.parse("2020-07-23 20:56:44:080");

        UserID userID = new UserID();
        CustomerID customerID = new CustomerID();

        Click clickPremium = new Click(userID, Premium.PREMIUM, parsedTimeStamp);
        Click clickNoPremium = new Click(userID, Premium.NO_PREMIUM,parsedTimeStamp);

        BudgetStandard budgetStandard = new BudgetStandard(2);
        CampaignStandard campaignStandard = new CampaignStandard(customerID, budgetStandard);

        PaymentCampaign paymentCampaign = new PaymentCampaign();

        paymentCampaign.chargedForOneClick(campaignStandard,clickPremium);
        paymentCampaign.chargedForOneClick(campaignStandard,clickNoPremium);

        assertEquals("1,94", budgetStandard.toString());





    }
}