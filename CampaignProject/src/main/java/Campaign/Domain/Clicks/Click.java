package Campaign.Domain.Clicks;

import Campaign.Domain.User.User;

public class Click {
    private final User user;
    private final Premium premium;

    public Click(User user, Premium premium) {
        this.user = user;
        this.premium = premium;
    }

    public boolean isPremium() {
        return this.premium.equals(Premium.PREMIUM);
    }

    public double priceClick() {
        return premium.price;
    }
}
