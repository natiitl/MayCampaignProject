package Campaign.Domain.User;

import java.util.Objects;

public class UserID {
    private int idUser;

    public UserID() {
        this.idUser = (int)( Math.random()*10000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID = (UserID) o;
        return idUser == userID.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }
}
