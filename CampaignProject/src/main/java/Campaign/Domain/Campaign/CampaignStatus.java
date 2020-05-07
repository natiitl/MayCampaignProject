package Campaign.Domain.Campaign;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum CampaignStatus {
    ACTIVE,
    PAUSE,
    FINISHED;

    public static class CampaignList {
        List<Campaign> campaignList = new ArrayList<>();
        public void add(Campaign campaign) {
            campaignList.add(campaign);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CampaignList that = (CampaignList) o;
            return Objects.equals(campaignList, that.campaignList);
        }

        @Override
        public int hashCode() {
            return Objects.hash(campaignList);
        }
    }
}
