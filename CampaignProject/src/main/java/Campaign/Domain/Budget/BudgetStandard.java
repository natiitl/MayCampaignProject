package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.ClickRepository;

public class BudgetStandard extends Budget {

    public void chargedFor(ClickRepository clickRepository) {
        double budget=getBudget();
        if(budgetIsZero()){
            return;
        }
         budget-= clickRepository.priceStandardClicks();
         setBudget(budget);

    }

    public boolean budgetIsZero() {
        return getBudget() <= 0;
    }

    @Override
    public ClickRepository clickChargedList() {
        return getClickChargedRepository();
    }

    @Override
    public String toString() {
        return String.format("%.2f", getBudget());
    }
}
