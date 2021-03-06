package Campaign.Domain.Clicks;

import Campaign.Domain.User.UserRepository;

import java.util.ArrayList;
import java.util.Date;
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
       return clickList.get(clickList.size()-1).checkValidClick(newClick);

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


    public double priceStandardClicks() {
        double price=0;
        for (Click click:clickList) {
            price+=click.priceStandardClick();
        }
        return price;
    }
    public double priceTopClicks() {
        double price=0;
        for (Click click:clickList) {
            price+=click.priceTopClick();
        }
        return price;
    }

    public ClickRepository findFraudulentClicks(UserRepository userRepository, Date date) {
        ClickRepository clickRepositoryNEW = new ClickRepository();
        for (Click click: clickList) {
            click.dateIsEarlier(date);
            if(userRepository.isMyClick(click,date)){
                clickRepositoryNEW.add(click);
            }

        }
        return clickRepositoryNEW;
    }
}
