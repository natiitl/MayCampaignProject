package Campaign;


import Campaign.Domain.Budget.Budget;
import Campaign.Domain.Budget.BudgetType;
import Campaign.Domain.Budget.FactoryBudget;
import Campaign.Domain.Campaign.CampaignDemo;
import Campaign.Domain.Campaign.CampaignStandard;
import Campaign.Domain.Campaign.CampaignTop;
import Campaign.Domain.Clicks.Click;
import Campaign.Domain.Clicks.ClickRepository;
import Campaign.Domain.Clicks.Premium;
import Campaign.Domain.Client.CustomerID;
import Campaign.Domain.User.UserID;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentCampaignApp {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date firstDate = dateFormat.parse("2020-05-08 20:50:44");
        Date secondDate = dateFormat.parse("2020-05-08 20:52:45");

        CustomerID customerID = new CustomerID();
        UserID userID = new UserID();
        ClickRepository clickRepository = new ClickRepository();
        Budget budgetDemo = FactoryBudget.getBudget(BudgetType.DEMO);
        Budget budgetStandard = FactoryBudget.getBudget(BudgetType.STANDARD);
        Budget budgetTop = FactoryBudget.getBudget(BudgetType.TOP);
        Click firstClickPremium = new Click(userID, Premium.PREMIUM,firstDate);
        Click firstClickNoPremium = new Click(userID, Premium.NO_PREMIUM,secondDate);

        clickRepository.add(firstClickPremium);
        clickRepository.add(firstClickNoPremium);

        budgetDemo.setBudget(3);
        budgetStandard.setBudget(3);
        budgetTop.setBudget(3);
        CampaignDemo campaignDemo = new CampaignDemo(customerID,budgetDemo);
        CampaignStandard campaignStandard = new CampaignStandard(customerID,budgetStandard);
        CampaignTop campaignTop = new CampaignTop(customerID,budgetTop);

        System.out.println("DEMO");
        System.out.println("We create a demo campaign with budget " + budgetDemo.toString() + "€");

        campaignDemo.chargedFor(clickRepository);

        System.out.println("After charged two clicks, one premium and one standard, the budget is " + budgetDemo.toString()+ "€");

        System.out.println("Standard");
        System.out.println("We create a standard campaign with budget " + budgetStandard.toString() + "€");

        campaignStandard.chargedFor(clickRepository);

        System.out.println("After charged two clicks, one premium(0,01€) and one standard(0,05€), the budget is " + budgetStandard.toString()+ "€");

        System.out.println("TOP");
        System.out.println("We create a top campaign with budget " + budgetTop.toString() + "€");

        campaignTop.chargedFor(clickRepository);

        System.out.println("After charged two clicks, one premium(0,1€) and one standard(0,2€), the budget is " + budgetTop
                .toString()+ "€");
    }


}
