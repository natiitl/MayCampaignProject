package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.Click;

public interface Budget {

    void chargedFor(Click click);

    boolean budgetIsZero();

}
