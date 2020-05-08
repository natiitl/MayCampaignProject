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

public class CampaignTopShould {
    UserID userID;
    CustomerID customerID;
    Budget budgetTop;
    CampaignStandard campaignTop;

    @BeforeEach
    public void init() {
        userID = new UserID();
        customerID = new CustomerID();
        budgetTop = FactoryBudget.getBudget(BudgetType.TOP);
        campaignTop = new CampaignStandard(customerID, budgetTop);
    }

    @Test
    public void check_that_two_campaign_do_not_have_the_same_id() {

        CampaignStandard campaignStandardB = new CampaignStandard(customerID, budgetTop);

        assertEquals(false, campaignStandardB.equals(campaignTop));
    }

    @Test
    public void raise_error_when_you_want_to_change_the_status_when_it_is_already_finished() {
        CampaignStandard campaignStandard = new CampaignStandard(customerID, budgetTop);
        campaignStandard.changeStatusToFinished();

        assertThrows(CampaignFinishedException.class, () -> campaignStandard.changeStatusToActive());
    }
    @Test
    public void check_that_status_changed_for_pause() {
        CampaignStandard campaignStandard = new CampaignStandard(customerID, budgetTop);
        campaignStandard.changeStatusToPause();

        assertEquals(true, campaignStandard.statusIsPause());
    }
    @Test
    public void check_that_status_changed_for_finished() {
        CampaignStandard campaignStandard = new CampaignStandard(customerID, budgetTop);
        campaignStandard.changeStatusToFinished();

        assertEquals(true, campaignStandard.statusIsFinished());
    }


}

