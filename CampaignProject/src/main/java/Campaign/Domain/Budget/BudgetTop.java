package Campaign.Domain.Budget;

import Campaign.Domain.Clicks.ClickRepository;

public class BudgetTop extends Budget {



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

    private boolean isMoreAt5PercentToInitial(double priceTopClicks) {
        return getBudgetInitial()*0.95 >=priceTopClicks;
    }

    public boolean budgetIsZero() {
        return getBudget() <= 0;
    }

    @Override
    public void refundFor(ClickRepository clickRepository) {
        double budget=getBudget();
        if(!isMoreAt5PercentToInitial(clickRepository.priceTopClicks())) {

            budget += clickRepository.priceStandardClicks();
        }
        setBudget(budget);

    }

    @Override
    public String toString() {
        return String.format("%.2f", getBudget());
    }
}
