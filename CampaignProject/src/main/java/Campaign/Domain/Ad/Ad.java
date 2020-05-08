package Campaign.Domain.Ad;

import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;

public class Ad {
    private ClickRepository clickRepositoryForCharged;

    public Ad() {

        this.clickRepositoryForCharged = new ClickRepository();
    }

    public void addClick(Click click){
        clickRepositoryForCharged.add(click);
    }

    public void chargedAt(Budget budget) {
        clickRepositoryForCharged.chargedIn(budget);
    }
}
