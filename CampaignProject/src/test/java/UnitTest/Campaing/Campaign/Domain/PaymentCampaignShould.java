package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Budget.*;
import Campaign.Domain.Campaign.CampaignDemo;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Campaign.CampaignTop;
import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.Premium;
import Campaign.PaymentCampaignApp;
import Campaign.Domain.User.UserID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PaymentCampaignShould {
    CampaignStandard campaignStandard;
    CampaignTop campaignTop;
    CampaignDemo campaignDemo;
    Click click;
    Click clickA;
    ClickRepository clickRepository;
    PaymentCampaignApp paymentCampaignApp;
    Budget budgetStandard;
    Budget budgetTop;
    Budget budgetDemo;
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
        clickRepository = new ClickRepository();
        clickRepository.add(clickA);
        click = new Click(new UserID(), Premium.NO_PREMIUM, date2);
        clickRepository.add(click);
        budgetStandard = FactoryBudget.getBudget(BudgetType.STANDARD);
        budgetStandard.setBudget(9);
        budgetTop = FactoryBudget.getBudget(BudgetType.TOP);
        budgetTop.setBudget(9);

        campaignStandard = new CampaignStandard(new CustomerID(), budgetStandard);
        campaignTop = new CampaignTop(new CustomerID(), budgetTop);
        campaignDemo = new CampaignDemo(new CustomerID(),budgetDemo);
        paymentCampaignApp = new PaymentCampaignApp();

    }

    @Test
    public void check_charge_two_clicks_list_at_one_campaign_Standard() {
        paymentCampaignApp.chargedFor(campaignStandard, clickRepository);

        assertEquals("8,94", budgetStandard.toString());
    }

    @Test
    public void check_charge_one_click_list_at_one_campaign_top() {
        paymentCampaignApp.chargedFor(campaignTop, clickRepository);

        assertEquals("8,70", budgetTop.toString());
    }

    @Test
    public void check_charge_two_clicks_list__at_one_Campaign_top() {
        paymentCampaignApp.chargedFor(campaignTop, clickRepository);
        paymentCampaignApp.chargedFor(campaignTop, clickRepository);

        assertEquals("8,40", budgetTop.toString());
    }

    @Test
    public void check_that_no_charge_is_made_in_demo_campaigns(){
        paymentCampaignApp.chargedFor(campaignDemo, clickRepository);

        assertEquals("9,00", budgetTop.toString());
    }

}
