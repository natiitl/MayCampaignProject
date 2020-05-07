package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.Click;

public class Budget {
    private double budget;
    public Budget(double budget) {
        this.budget = budget;
    }

    public void budgetReduction(Click click) {
        this.budget -=click.priceClick();

    }

    public boolean budgetIsZero() {
        return budget<=0 ;
    }

    @Override
    public String toString() {
        return String.format("%.2f",budget);
    }
}
