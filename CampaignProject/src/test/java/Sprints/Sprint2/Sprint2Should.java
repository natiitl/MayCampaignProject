package Sprints.Sprint2;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Budget.BudgetType;
import Campaign.Domain.Budget.FactoryBudget;
import Campaign.Domain.Campaign.CampaignStandard;
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

public class Sprint2Should {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date date = dateFormat.parse("2020-07-27 20:50:44");
        Date date2 = dateFormat.parse("2020-07-27 20:52:45");
        ClickRepository clickRepository = new ClickRepository();
        budget.setBudget(2);
        Click clickPremium = new Click(userID, Premium.PREMIUM, date);
        Click clickNoPremium = new Click(userID, Premium.NO_PREMIUM, date);
        clickRepository.add(clickNoPremium);
        clickRepository.add(clickPremium);
        campaign.chargedFor(clickRepository);
        Assertions.assertEquals("1,99",budget.toString());

    }
    @Test
    public void check_that_charged_two_clicks_at_same_time_but_diff_users() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date date = dateFormat.parse("2020-07-27 20:50:44");

        ClickRepository clickRepository = new ClickRepository();
        budget.setBudget(2);
        Click clickPremium = new Click(userID, Premium.PREMIUM, date);
        Click clickNoPremium = new Click(new UserID(), Premium.NO_PREMIUM, date);
        clickRepository.add(clickNoPremium);
        clickRepository.add(clickPremium);
        campaign.chargedFor(clickRepository);
        Assertions.assertEquals("1,94",budget.toString());

    }
}
