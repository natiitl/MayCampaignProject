package Campaign.Service;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class TimeServer implements TimeService {

    @Override
    public Timestamp getDate() {
        return new Timestamp(new Date().getTime());
    }
}