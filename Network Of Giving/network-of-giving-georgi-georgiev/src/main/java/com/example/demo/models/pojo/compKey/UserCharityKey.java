package com.example.demo.models.pojo.compKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_and_charities")
@Embeddable
public class UserCharityKey implements Serializable {

    @Column(name = "user_id")
    Long user_id;

    @Column(name = "charity_id")
    Long charity_id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCharityKey that = (UserCharityKey) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(charity_id, that.charity_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, charity_id);
    }
}
