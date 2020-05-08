package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.ClickRepository;

public abstract class Budget {
    private double budget;
    private double budgetInitial;

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
        setBudgetInitial(budget);
    }


    public abstract void chargedFor(ClickRepository clickRepository);

    public abstract boolean budgetIsZero();

    public abstract void refundFor(ClickRepository clickRepository);

    public double getBudgetInitial() {
        return budgetInitial;
    }

    public void setBudgetInitial(double budgetInitial) {
        this.budgetInitial = budgetInitial;
    }
}
