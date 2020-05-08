package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;

public class BudgetTop implements Budget {
    private double budget;

    public BudgetTop(double budget) {
        this.budget = budget;
    }

    public void chargedFor(ClickRepository clickRepository) {
        this.budget -= clickRepository.priceTopClicks();

    }

    @Override
    public boolean budgetIsZero() {
        return budget <= 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f", budget);
    }
}
