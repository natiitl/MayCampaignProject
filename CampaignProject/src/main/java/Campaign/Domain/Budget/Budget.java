package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;

public interface Budget {

    void chargedFor(ClickRepository clickRepository);

    boolean budgetIsZero();

}
