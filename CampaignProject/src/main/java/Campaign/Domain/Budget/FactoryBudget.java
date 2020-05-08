package Campaign.Domain.Budget;

public class FactoryBudget {
    public static Budget getBudget(BudgetType budgetType) {
        if (budgetType.equals(BudgetType.TOP)) {
            return new BudgetTop();
        }
        if (budgetType.equals(BudgetType.STANDARD)) {
            return new BudgetStandard();
        }
        if (budgetType.equals(BudgetType.DEMO)) {
            return new BudgetDemo();
        }
        return null;
    }
}
