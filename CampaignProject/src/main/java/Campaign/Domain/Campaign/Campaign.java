package Campaign.Domain.Campaign;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.User.UserID;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;

import java.util.Objects;

public class Campaign {
    private int idCampaign;
    private ClientID clientID;
    private Budget budget;
    private CampaignStatus campaignStatus;

    public Campaign(ClientID clientID, Budget budget) {
        this.clientID = clientID;
        this.budget = budget;
        this.campaignStatus = CampaignStatus.ACTIVE;
        this.idCampaign++;

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
