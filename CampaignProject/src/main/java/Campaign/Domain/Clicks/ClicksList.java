package Campaign.Domain.Clicks;

import java.util.ArrayList;
import java.util.List;

public class ClicksList {
    private List<Click> clickList;

    public ClicksList() {
        clickList = new ArrayList<>();
    }


    public void add(Click newClick) {
        for (Click click : clickList) {

        }
        clickList.add(newClick);
    }

}
