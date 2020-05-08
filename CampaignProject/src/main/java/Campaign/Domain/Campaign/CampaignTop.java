package Campaign.Domain.Campaign;

import Campaign.Domain.Ad.Ad;
import Campaign.Domain.Budget.BudgetTop;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Client.CustomerID;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;

import java.util.Objects;

public class CampaignTop implements Campaign {
    private int idCampaign;
    private CustomerID customerID;
    private BudgetTop budgetTop;
    private CampaignStatus campaignStatus;

    public CampaignTop(CustomerID customerID, BudgetTop budgetTop) {
        this.customerID = customerID;
        this.budgetTop = budgetTop;
        this.campaignStatus = CampaignStatus.ACTIVE;
        this.idCampaign++;

    }

    @Override
    public void chargedFor(Click click) {
        campaignFinishedOrPause();
        budgetTop.chargedFor(click);
        if (budgetTop.budgetIsZero()) {
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
    public void chargedFor(Ad ad) {
        ad.chargedAt(budgetTop);
    }

    @Override
    public boolean statusIsPause() {
        return campaignStatus.equals(CampaignStatus.PAUSE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignTop campaignStandard = (CampaignTop) o;
        return idCampaign == campaignStandard.idCampaign;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampaign);
    }
}
