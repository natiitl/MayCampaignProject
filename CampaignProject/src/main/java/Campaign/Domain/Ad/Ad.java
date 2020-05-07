package Campaign.Domain.Ad;

import Campaign.Domain.Clicks.ClicksList;

public class Ad {
    private ClicksList clicksListForCharged;

    public Ad() {
        this.clicksListForCharged = new ClicksList();
    }
}
