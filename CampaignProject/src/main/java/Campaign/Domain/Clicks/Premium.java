package Campaign.Domain.Clicks;

public enum Premium {
    PREMIUM (0.05),
    NO_PREMIUM (0.01);

    public double price;
    Premium(double price){
    this.price = price;
    }
}
