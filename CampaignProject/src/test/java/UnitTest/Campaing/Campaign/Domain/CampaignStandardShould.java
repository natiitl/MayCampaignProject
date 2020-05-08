package UnitTest.Campaing.Campaign.Domain;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Budget.BudgetType;
import Campaign.Domain.Budget.FactoryBudget;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.User.UserID;
import Campaign.Exception.CampaignFinishedException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CampaignStandardShould {
    UserID userID;
    CustomerID customerID;
    Budget budgetStandard;
    CampaignStandard campaignStandardA;


    @BeforeEach
    public void init() {
        userID = new UserID();
        customerID = new CustomerID();
        budgetStandard = FactoryBudget.getBudget(BudgetType.STANDARD);
        campaignStandardA = new CampaignStandard(customerID, budgetStandard);
    }

    @Test
    public void check_that_two_campaign_do_not_have_the_same_id() {

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


}

