package AcceptanceTest;

import Campaign.Domain.Budget.BudgetStandard;
import Campaign.Domain.Budget.BudgetTop;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.*;
import Campaign.Domain.Campaign.CampaignTop;
import Campaign.Domain.Clicks.ClickRepository;
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
                "yyyy-MM-dd hh:mm:ss");
        Date parseDate = dateFormat.parse("2020-07-27 20:50:44");
        Date parseDate2 = dateFormat.parse("2020-07-27 20:52:45");

        UserID userID = new UserID();
        CustomerID customerID = new CustomerID();
        ClickRepository clickRepository = new ClickRepository();

        Click clickPremium = new Click(userID, Premium.PREMIUM, parseDate);
        Click clickNoPremium = new Click(userID, Premium.NO_PREMIUM,parseDate2);
        clickRepository.add(clickPremium);
        clickRepository.add(clickNoPremium);

        BudgetStandard budgetStandard = new BudgetStandard(2);
        BudgetTop budgetTop = new BudgetTop(2);
        CampaignStandard campaignStandard = new CampaignStandard(customerID, budgetStandard);
        CampaignTop campaignTop = new CampaignTop(customerID,budgetTop);

        PaymentCampaign paymentCampaign = new PaymentCampaign();
        paymentCampaign.chargedFor(campaignStandard,clickRepository);
        paymentCampaign.chargedFor(campaignTop,clickRepository);


        assertEquals("1,94", budgetStandard.toString());
        assertEquals("1,70", budgetTop.toString());





    }
}
