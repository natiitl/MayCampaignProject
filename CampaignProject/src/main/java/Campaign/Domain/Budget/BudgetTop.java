package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.Click;

public class BudgetTop implements Budget {
    private double budget;

    public BudgetTop(double budget) {
        this.budget = budget;
    }

    @Override
    public void chargedFor(Click click) {

        this.budget -= click.priceTopClick();

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
