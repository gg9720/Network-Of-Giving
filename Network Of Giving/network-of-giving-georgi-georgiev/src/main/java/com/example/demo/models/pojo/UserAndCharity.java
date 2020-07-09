package com.example.demo.models.pojo;

import com.example.demo.models.pojo.compKey.UserCharityKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//@RequiredArgsConstructor
@Table(name = "user_and_charities")
public class UserAndCharity {

    @EmbeddedId
    private UserCharityKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAndCharity that = (UserAndCharity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(charity, that.charity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, charity);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("charity_id")
    @JoinColumn(name = "charity_id")
    @ToString.Exclude
    private Charity charity;


}
