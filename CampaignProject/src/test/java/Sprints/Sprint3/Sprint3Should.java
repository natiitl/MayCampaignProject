package Sprints.Sprint3;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Budget.BudgetType;
import Campaign.Domain.Budget.FactoryBudget;
import Campaign.Domain.Campaign.CampaignDemo;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Campaign.CampaignTop;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.User.UserID;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sprint3Should {
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
        clickNoPremium = new Click(userID, Premium.NO_PREMIUM, date);
        clickRepository.add(clickNoPremium);
        clickRepository.add(clickPremium);
    }

    @Test
    public void check_that_not_duplicate_clicks() throws ParseException {
        ClickRepository clickRepository = new ClickRepository();
        Budget budgetDemo = FactoryBudget.getBudget(BudgetType.DEMO);
        Budget budgetStandard = FactoryBudget.getBudget(BudgetType.STANDARD);
        Budget budgetTop = FactoryBudget.getBudget(BudgetType.TOP);
        Click firstClickPremium = new Click(userID, Premium.PREMIUM,date);
        Click firstClickNoPremium = new Click(userID, Premium.NO_PREMIUM,date2);

        clickRepository.add(firstClickPremium);
        clickRepository.add(firstClickNoPremium);

        budgetDemo.setBudget(3);
        budgetStandard.setBudget(3);
        budgetTop.setBudget(3);

        CampaignDemo campaignDemo = new CampaignDemo(customerID,budgetDemo);
        CampaignStandard campaignStandard = new CampaignStandard(customerID,budgetStandard);
        CampaignTop campaignTop = new CampaignTop(customerID,budgetTop);
        campaignDemo.chargedFor(clickRepository);
        campaignStandard.chargedFor(clickRepository);
        campaignTop.chargedFor(clickRepository);

        assertEquals("3,00",budgetDemo.toString());
        assertEquals("2,94",budgetStandard.toString());
        assertEquals("2,70",budgetTop.toString());


    }

}
