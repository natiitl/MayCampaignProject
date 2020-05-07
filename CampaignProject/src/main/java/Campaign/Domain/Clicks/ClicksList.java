package Campaign.Domain.Clicks;

import java.util.ArrayList;
import java.util.List;

public class ClicksList {
    private List<Click> clickList;

    public ClicksList() {
        clickList = new ArrayList<>();
    }


    public void add(Click newClick) {
        clickList.add(newClick);
    }
    private void compareTimestamps(Click newClick){
       // return newClick.compareTimes(clickList.get(clickList.size()));


    }

}
