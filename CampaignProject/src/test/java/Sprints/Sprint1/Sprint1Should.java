package Sprints.Sprint1;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Budget.BudgetType;
import Campaign.Domain.Budget.FactoryBudget;
import Campaign.Domain.Campaign.Campaign;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.User.UserID;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sprint1Should {
    Date date;
    Date date2;

    UserID userID;
    CustomerID customerID;
    Budget budget;
    ClickRepository clickRepository;
    CampaignStandard campaign;

    Click clickPremium;
    Click clickNoPremium;

    @Before
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        date = dateFormat.parse("2020-07-27 20:50:44");
        date2 = dateFormat.parse("2020-07-27 20:52:45");

        userID = new UserID();
        customerID = new CustomerID();
        budget = FactoryBudget.getBudget(BudgetType.STANDARD);
        budget.setBudget(2);
        clickRepository = new ClickRepository();
        campaign = new CampaignStandard(customerID, budget);

        clickPremium = new Click(userID, Premium.PREMIUM, date);
        clickNoPremium = new Click(userID, Premium.NO_PREMIUM, date2);
        clickRepository.add(clickNoPremium);
        clickRepository.add(clickPremium);
    }

    @Test
    public void check_that_the_campaign_status_is_correctly_modified() {
        campaign.changeStatusToPause();
        assertEquals(true, campaign.statusIsPause());
        campaign.changeStatusToActive();
        assertEquals(true, campaign.statusIsActive());
    }
    @Test
    public void check_that_the_campaign_clicks_are_loaded_correctly(){
        campaign.chargedFor(clickRepository);
        assertEquals("1,94",budget.toString());

    }
    @Test
    public void check_that_when_the_budget_is_zero_the_campaign_finished(){
        budget.setBudget(0.06);
        campaign.chargedFor(clickRepository);
        assertEquals(true,campaign.statusIsFinished());

    }



}
