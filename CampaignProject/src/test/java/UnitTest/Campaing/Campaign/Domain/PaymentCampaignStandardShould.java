package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Ad.Ad;
import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;
import Campaign.PaymentCampaign;
import Campaign.Domain.User.UserID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentCampaignStandardShould {
    CampaignStandard campaignStandard;
    Click clickA;
    PaymentCampaign paymentCampaign;
    Budget budget;
    UserID userID;
    Date date;
    Date date2;


    @BeforeEach
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        date = dateFormat.parse("2020-07-23 20:56:44");
        date2 = dateFormat.parse("2020-07-23 20:57:44");


        userID = new UserID();
        clickA = new Click(userID, Premium.PREMIUM, date);
        budget = new Budget(9);
        campaignStandard = new CampaignStandard(new CustomerID(), budget);
        paymentCampaign = new PaymentCampaign();
    }



    @Test
    public void check_charge_two_clicks_premiums_at_one_campaign() {
        Click click = new Click(new UserID(), Premium.PREMIUM, date);

        paymentCampaign.chargedForOneClick(campaignStandard, click);
        paymentCampaign.chargedForOneClick(campaignStandard, click);

        assertEquals("8,90", budget.toString());
    }

    @Test
    public void check_charge_one_clicks_NO_premium_at_oneCampaign() {
        Click click = new Click(new UserID(), Premium.NO_PREMIUM, date);

        paymentCampaign.chargedForOneClick(campaignStandard, click);

        assertEquals("8,99", budget.toString());
    }

    @Test
    public void check_charge_two_clicks_NO_premium_at_campaign_for_ad() {
        Click click = new Click(new UserID(), Premium.NO_PREMIUM, date);
        Click click2 = new Click(new UserID(), Premium.NO_PREMIUM, date2);
        Ad ad = new Ad();

        ad.addClick(click);
        ad.addClick(click2);

        paymentCampaign.chargedFor(campaignStandard,ad);

        assertEquals("8,98",budget.toString());

    }

}
