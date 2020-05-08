package Campaign.Domain.Clicks;

public enum Premium {
    PREMIUM (0.05, 0.2),
    NO_PREMIUM (0.01,0.1);

    public double priceStandard;
    public double priceTop;

    Premium(double priceStandard, double priceTop) {
        this.priceStandard = priceStandard;
        this.priceTop = priceTop;
    }

}
