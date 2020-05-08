package Campaign.Domain.Clicks;

import Campaign.Domain.Budget.Budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClickRepository {
    private List<Click> clickList;

    public ClickRepository() {
        clickList = new ArrayList<>();
    }


    public void add(Click newClick) {
        if(differenceGreaterFifteenSeconds(newClick)) {
            clickList.add(newClick);
        }

    }
    private boolean differenceGreaterFifteenSeconds(Click newClick){

        if(clickList.size()==0){
            return true;
        }
       return clickList.get(clickList.size()-1).differenceGreaterFifteenSeconds(newClick);

    }
    public void chargedIn(Budget budget) {
        for (Click click:clickList) {
            budget.budgetReduction(click);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClickRepository that = (ClickRepository) o;
        return Objects.equals(clickList, that.clickList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clickList);
    }


}