package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.Click;

public class BudgetStandard implements Budget {
    private double budget;

    public BudgetStandard(double budget) {
        this.budget = budget;
    }

    @Override
    public void chargedFor(Click click) {
        this.budget -= click.priceStandardClick();

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
