package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.ClickRepository;

public class BudgetDemo extends Budget {



    @Override
    public double getBudget() {
        return super.getBudget();
    }

    @Override
    public void setBudget(double budget) {
        super.setBudget(budget);
    }

    public void chargedFor(ClickRepository clickRepository) {
         double budget = getBudget();
        budget -= clickRepository.priceTopClicks();
        setBudget(budget);

    }

    public boolean budgetIsZero() {
        return getBudget() <= 0;
    }

    @Override
    public ClickRepository clickChargedList() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%.2f", getBudget());
    }
}
