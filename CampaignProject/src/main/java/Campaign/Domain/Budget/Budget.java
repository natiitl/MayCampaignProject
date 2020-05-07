package Campaign.Domain.Budget;

public class Budget {
    private double budget;
    public Budget(double budget) {
        this.budget = budget;
    }

    public void budgetReduction(double priceClick) {
        this.budget -=priceClick;

    }

    public boolean budgetIsZero() {
        return budget==0;
    }

    @Override
    public String toString() {
        return String.format("%.2f",budget);
    }
}
