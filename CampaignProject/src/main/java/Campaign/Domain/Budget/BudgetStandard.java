package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.ClickRepository;

public class BudgetStandard extends Budget {

    public void chargedFor(ClickRepository clickRepository) {
        double budget=getBudget();
         budget-= clickRepository.priceStandardClicks();
         setBudget(budget);

    }

    public boolean budgetIsZero() {
        return getBudget() <= 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f", getBudget());
    }
}
