package Campaign.Domain.Campaign;

import Campaign.Domain.Budget.BudgetStandard;
import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.Client.CustomerID;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;

import java.util.Objects;

public class CampaignStandard implements Campaign {
    private int idCampaign;
    private CustomerID customerID;
    private BudgetStandard budgetStandard;
    private CampaignStatus campaignStatus;

    public CampaignStandard(CustomerID customerID, BudgetStandard budgetStandard) {
        this.customerID = customerID;
        this.budgetStandard = budgetStandard;
        this.campaignStatus = CampaignStatus.ACTIVE;
        this.idCampaign++;

    }

    @Override
    public void chargedFor(ClickRepository clickRepository) {
        campaignFinishedOrPause();
        budgetStandard.chargedFor(clickRepository);
        if (budgetStandard.budgetIsZero()) {
            changeStatusToFinished();
        }
    }

    @Override
    public void campaignFinishedOrPause() {
        if (statusIsFinished()) {
            throw new CampaignFinishedException("This campaign is finished");
        }
        if (statusIsPause()) {
            throw new CampaignPauseException("This campaign is pause");
        }
    }

    @Override
    public boolean statusIsFinished() {
        return campaignStatus.equals(CampaignStatus.FINISHED);
    }

    @Override
    public void changeStatusToFinished() {
        if (statusIsFinished()) {
            throw new CampaignFinishedException("This campaign is finished.");
        }
        this.campaignStatus = CampaignStatus.FINISHED;
    }

    @Override
    public void changeStatusToPause() {
        if (statusIsFinished()) {
            throw new CampaignFinishedException("This campaign is finished.");
        }
        this.campaignStatus = CampaignStatus.PAUSE;
    }

    @Override
    public void changeStatusToActive() {
        if (statusIsFinished()) {
            throw new CampaignFinishedException("This campaign is finished.");
        }
        this.campaignStatus = CampaignStatus.ACTIVE;
    }

    @Override
    public boolean statusIsPause() {
        return campaignStatus.equals(CampaignStatus.PAUSE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignStandard campaignStandard = (CampaignStandard) o;
        return idCampaign == campaignStandard.idCampaign;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampaign);
    }
}
