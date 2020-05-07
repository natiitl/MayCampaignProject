package Campaign.Domain.Campaign;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.User.User;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;

public class Campaign {
    private int idCampaign;
    private User user;
    private Budget budget;
    private CampaignStatus campaignStatus;

    public Campaign(User user, Budget budget) {
        this.user = user;
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
        this.campaignStatus = campaignStatus;
    }
}
