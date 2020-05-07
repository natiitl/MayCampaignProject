package Campaign.Domain.Campaign;

import Campaign.Domain.Ad.AdList;
import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Client.CustomerID;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;

import java.util.Objects;

public class Campaign {
    private int idCampaign;
    private CustomerID customerID;
    private Budget budget;
    private CampaignStatus campaignStatus;
    private AdList adList;

    public Campaign(CustomerID customerID, Budget budget) {
        this.customerID = customerID;
        this.budget = budget;
        this.campaignStatus = CampaignStatus.ACTIVE;
        this.idCampaign++;
        this.adList = new AdList();

    }

    public void budgetReduction(Click click) {
        campaignFinishedOrPause();
        budget.budgetReduction(click);
        if(budget.budgetIsZero()){
            changeStatusCampaign(CampaignStatus.FINISHED);
        }
    }
    public void campaignFinishedOrPause(){
        if(campaignStatus.equals(CampaignStatus.FINISHED)){
            throw new CampaignFinishedException("This campaign is finished");
        }
        if(campaignStatus.equals(CampaignStatus.PAUSE)){
            throw new CampaignPauseException("This campaign is pause");
        }

    }
    public void changeStatusCampaign(CampaignStatus campaignStatus){
        if(this.campaignStatus.equals(CampaignStatus.FINISHED)){
            throw new CampaignFinishedException("This campaign is finished.");
        }
        this.campaignStatus = campaignStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return idCampaign == campaign.idCampaign;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampaign);
    }
}
