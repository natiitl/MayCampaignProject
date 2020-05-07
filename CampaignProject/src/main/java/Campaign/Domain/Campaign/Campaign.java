package Campaign.Domain.Campaign;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.User.User;
import Campaign.Exception.CampaignFinishedException;

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

    public void budgetReduction(double priceClick) {
        campaignFinished();
        budget.budgetReduction(priceClick);
        if(budget.budgetIsZero()){
            changeStatusCampaign(CampaignStatus.FINISHED);
        }
    }
    public void campaignFinished(){
        if(campaignStatus.equals(CampaignStatus.FINISHED)){
            throw new CampaignFinishedException("This campaign is finished");
        }
    }
    public void changeStatusCampaign(CampaignStatus campaignStatus){
        this.campaignStatus = campaignStatus;
    }
}
