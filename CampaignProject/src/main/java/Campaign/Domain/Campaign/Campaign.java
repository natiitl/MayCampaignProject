package Campaign.Domain.Campaign;

import Campaign.Domain.Clicks.ClickRepository;


public interface Campaign {
    void chargedFor(ClickRepository clickRepository);

    void campaignFinishedOrPause();

    void changeStatusToFinished();

    void changeStatusToPause();

    void changeStatusToActive();

    boolean statusIsFinished();

    boolean statusIsPause();

}
