package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Budget.BudgetType;
import Campaign.Domain.Budget.FactoryBudget;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.User.UserID;
import Campaign.Domain.User.UserRepository;
import Campaign.Exception.CampaignFinishedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CampaignStandardShould {
    UserID userID;
    CustomerID customerID;
    Budget budgetStandard;
    CampaignStandard campaignStandardA;


    @Before
    public void init() {
        userID = new UserID();
        customerID = new CustomerID();
        budgetStandard = FactoryBudget.getBudget(BudgetType.STANDARD);
        campaignStandardA = new CampaignStandard(customerID, budgetStandard);
    }


    @Test
    public void check_that_not_duplicate_clicks() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date date = dateFormat.parse("2020-07-27 20:50:44");
        Date date2 = dateFormat.parse("2020-07-27 20:52:45");
        ClickRepository clickRepository = new ClickRepository();
        budgetStandard.setBudget(2);
        Click clickPremium = new Click(userID, Premium.PREMIUM, date);
        Click clickNoPremium = new Click(userID, Premium.NO_PREMIUM, date);
        clickRepository.add(clickNoPremium);
        clickRepository.add(clickPremium);
        campaignStandardA.chargedFor(clickRepository);
        Assertions.assertEquals("1,99",budgetStandard.toString());

    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date date = dateFormat.parse("2020-07-27 20:50:44");
        Date date2 = dateFormat.parse("2020-07-27 20:52:45");
        ClickRepository clickRepository = new ClickRepository();
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(userID);
        budgetStandard.setBudget(2);
        Click clickPremium = new Click(userID, Premium.PREMIUM, date);
        Click clickNoPremium = new Click(userID, Premium.NO_PREMIUM, date);
        clickRepository.add(clickNoPremium);
        clickRepository.add(clickPremium);
        campaignStandardA.chargedFor(clickRepository);
        ClickRepository clickRepositoryFraudulent = campaignStandardA.findFraudulentClicks(userRepository,date);
        campaignStandardA.refundFor(clickRepositoryFraudulent);

        Assertions.assertEquals("2,00",budgetStandard.toString());

    }

}

