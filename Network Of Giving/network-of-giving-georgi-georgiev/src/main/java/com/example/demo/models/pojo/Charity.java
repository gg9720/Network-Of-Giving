package com.example.demo.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Builder
@Table(name = "Charity")
public class Charity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long charity_id;

    @Length(min = 4, max = 30)
    @Column
    private String charity_name;
    @Length(min = 40, max = 400)
    @Column
    private String charity_description;
    @Column
    private int charity_participants_needed;
    @Column
    private String charity_creator;
    @Column
    private int charity_budget;
    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int amountCollected;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "charity")
    @JsonIgnore
    @ToString.Exclude
    private Set<UserAndCharity> allUsersInTheCharity = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Charity charity = (Charity) o;
        return Objects.equals(charity_id, charity.charity_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(charity_id);
    }

    //    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "charitiesOfTheUser")
//    @JsonIgnoreProperties
//    Set<User> usersInThisCharity;
}
