package Campaign.Domain.Campaign;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Budget.BudgetStandard;
import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.User.UserRepository;
import Campaign.Exception.CampaignFinishedException;
import Campaign.Exception.CampaignPauseException;

import java.util.Date;
import java.util.Objects;

public class CampaignStandard implements Campaign {
    private int idCampaign;
    private CustomerID customerID;
    private Budget budgetStandard;
    private CampaignStatus campaignStatus;
    private ClickRepository clickRepository;

    public CampaignStandard(CustomerID customerID, Budget budgetStandard) {
        this.customerID = customerID;
        this.budgetStandard = budgetStandard;
        this.campaignStatus = CampaignStatus.ACTIVE;
        this.clickRepository = new ClickRepository();
        this.idCampaign = (int)( Math.random()*10000);

    }
    @Override
public ClickRepository findFraudulentClicks(UserRepository userRepository, Date date){


        return clickRepository.findFraudulentClicks(userRepository,date);
}

    @Override
    public void chargedFor(ClickRepository clickRepository) {
        campaignFinishedOrPause();
        budgetStandard.chargedFor(clickRepository);
        if (budgetStandard.budgetIsZero()) {
            changeStatusToFinished();
        }
        this.clickRepository= clickRepository;
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
    public boolean statusIsActive() {
        return campaignStatus.equals(CampaignStatus.ACTIVE);
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

    @Override
    public void refundFor(ClickRepository clickRepositoryFraudulent) {
        campaignFinishedOrPause();
        budgetStandard.refundFor(clickRepository);
        if (budgetStandard.budgetIsZero()) {
            changeStatusToFinished();
        }
        this.clickRepository= clickRepository;
    }
}
