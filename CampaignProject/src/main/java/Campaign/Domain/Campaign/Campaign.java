package Campaign.Domain.Campaign;

import Campaign.Domain.Ad.Ad;
import Campaign.Domain.Clicks.Click;


public interface Campaign {
    void chargedFor(Click click);

    void campaignFinishedOrPause();

    void changeStatusToFinished();

    void changeStatusToPause();

    void changeStatusToActive();

    boolean statusIsFinished();

    boolean statusIsPause();

    void chargedFor(Ad ad);
}
