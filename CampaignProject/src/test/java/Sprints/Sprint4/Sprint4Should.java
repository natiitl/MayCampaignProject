package Sprints.Sprint4;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Budget.BudgetDemo;
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
import Campaign.Domain.User.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sprint4Should {
    @Test
    public void check_that_fraudulent_clicks_are_returned_correctly() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date date = dateFormat.parse("2020-07-27 20:50:44");
        Date date2 = dateFormat.parse("2020-07-27 20:52:45");

        ClickRepository clickRepository = new ClickRepository();
        UserRepository userRepository = new UserRepository();
        UserID userID = new UserID();
        UserID userID2 = new UserID();

        Budget budgetStandard = FactoryBudget.getBudget(BudgetType.STANDARD);
        Budget budgetDemo = FactoryBudget.getBudget(BudgetType.DEMO);
        Budget budgetTop = FactoryBudget.getBudget(BudgetType.TOP);

        userRepository.addUser(userID);
        userRepository.addUser(userID2);
        CampaignStandard campaignStandard = new CampaignStandard(new CustomerID(), budgetStandard);
        CampaignDemo campaignDemo = new CampaignDemo(new CustomerID(),budgetDemo);
        CampaignTop campaignTop = new CampaignTop(new CustomerID(),budgetTop);

        budgetStandard.setBudget(2);
        budgetDemo.setBudget(4);
        budgetTop.setBudget(1);

        Click clickPremium = new Click(userID, Premium.PREMIUM, date);
        Click clickPremium2 = new Click(userID2, Premium.NO_PREMIUM, date);
        Click clickNoPremium = new Click(userID, Premium.NO_PREMIUM, date);

        clickRepository.add(clickNoPremium);
        clickRepository.add(clickPremium);
        clickRepository.add(clickPremium2);

        campaignStandard.chargedFor(clickRepository);
        campaignDemo.chargedFor(clickRepository);
        campaignTop.chargedFor(clickRepository);
        ClickRepository clickRepositoryFraudulent = campaignStandard.findFraudulentClicks(userRepository, date);
        ClickRepository clickRepositoryFraudulentDemo = campaignStandard.findFraudulentClicks(userRepository, date);
        ClickRepository clickRepositoryFraudulentTop = campaignStandard.findFraudulentClicks(userRepository, date);

        campaignStandard.refundFor(clickRepositoryFraudulent);
        campaignDemo.refundFor(clickRepositoryFraudulentDemo);
        campaignTop.refundFor(clickRepositoryFraudulentTop);



        Assertions.assertEquals("2,00", budgetStandard.toString());
        Assertions.assertEquals("4,00", budgetDemo.toString());
        Assertions.assertEquals("0,80", budgetTop.toString());
    }
}
