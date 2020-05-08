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
    public void check_that_two_campaign_do_not_have_the_same_id() {
        CampaignStandard campaignStandardA = new CampaignStandard(customerID, budgetStandard);
        CampaignStandard campaignStandardB = new CampaignStandard(customerID, budgetStandard);

        assertEquals(false, campaignStandardB.equals(campaignStandardA));
    }

    @Test
    public void raise_error_when_you_want_to_change_the_status_when_it_is_already_finished() {
        CampaignStandard campaignStandard = new CampaignStandard(customerID, budgetStandard);
        campaignStandard.changeStatusToFinished();

        assertThrows(CampaignFinishedException.class, () -> campaignStandard.changeStatusToActive());
    }
    @Test
    public void check_that_status_changed_for_pause() {
        CampaignStandard campaignStandard = new CampaignStandard(customerID, budgetStandard);
        campaignStandard.changeStatusToPause();

        assertEquals(true, campaignStandard.statusIsPause());
    }
    @Test
    public void check_that_status_changed_for_finished() {
        CampaignStandard campaignStandard = new CampaignStandard(customerID, budgetStandard);
        campaignStandard.changeStatusToFinished();

        assertEquals(true, campaignStandard.statusIsFinished());
    }
    @Test
    public void check_that_the_campaign_clicks_are_loaded_correctly() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date date = dateFormat.parse("2020-07-27 20:50:44");
       Date date2 = dateFormat.parse("2020-07-27 20:52:45");
        ClickRepository clickRepository = new ClickRepository();
        budgetStandard.setBudget(2);
        Click clickPremium = new Click(userID, Premium.PREMIUM, date);
        Click clickNoPremium = new Click(userID, Premium.NO_PREMIUM, date2);
        clickRepository.add(clickNoPremium);
        clickRepository.add(clickPremium);
        campaignStandardA.chargedFor(clickRepository);
        Assertions.assertEquals("1,94",budgetStandard.toString());

    }

}

