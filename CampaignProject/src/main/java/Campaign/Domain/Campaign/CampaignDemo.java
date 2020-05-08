package Campaign.Domain.Campaign;

import Campaign.Domain.Ad.Ad;
import Campaign.Domain.Budget.BudgetStandard;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Client.CustomerID;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;

import java.util.Objects;

public class CampaignDemo implements Campaign {
    private int idCampaign;
    private CustomerID customerID;
    private CampaignStatus campaignStatus;

    public CampaignDemo(CustomerID customerID, BudgetStandard budgetStandard) {
        this.customerID = customerID;
        this.campaignStatus = CampaignStatus.ACTIVE;
        this.idCampaign++;

    }

    @Override
    public void chargedFor(Click click) {
        campaignFinishedOrPause();
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
    public void chargedFor(Ad ad) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignDemo campaignStandard = (CampaignDemo) o;
        return idCampaign == campaignStandard.idCampaign;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampaign);
    }
}