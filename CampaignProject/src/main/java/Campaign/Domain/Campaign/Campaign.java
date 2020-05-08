package Campaign.Domain.Campaign;

import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.User.UserRepository;

import java.util.Date;


public interface Campaign {

    void chargedFor(ClickRepository clickRepository);

    void campaignFinishedOrPause();

    boolean statusIsActive();

    void changeStatusToFinished();

    void changeStatusToPause();

    void changeStatusToActive();


    boolean statusIsFinished();

    ClickRepository findFraudulentClicks(UserRepository userRepository, Date date);

    boolean statusIsPause();

}
