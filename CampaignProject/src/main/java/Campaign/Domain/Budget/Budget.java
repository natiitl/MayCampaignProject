package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.ClickRepository;

public abstract class Budget {
    private double budget;
    private ClickRepository clickChargedRepository;

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }


    public abstract void chargedFor(ClickRepository clickRepository);

    public abstract boolean budgetIsZero();

    public abstract ClickRepository clickChargedList();

    public ClickRepository getClickChargedRepository() {
        return clickChargedRepository;
    }

    public void setClickChargedRepository(ClickRepository clickChargedRepository) {
        this.clickChargedRepository = clickChargedRepository;
    }
}
