package com.example.demo.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//enum Gender{
//    MALE,
//    FEMALE
//}

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Length(min = 5, max = 16, message = "Username should be between 5 and 16 characters")
    @NotBlank
    @Column
    private String username;

    @NotBlank
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Length(min = 3, max = 30)
    @NotBlank
    @Column
    private String name;

    @Column
    private int age;

    @NotBlank
    @Column
    private String gender;

    @Column
    private int money;


    @Length(min = 4, max = 15)
    @NotBlank
    @Column
    private String town;

    @Column
    private int donations;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private Set<UserAndCharity> allCharitiesOfTheUser = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user_id, user.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "user_and_charities",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "charity_id"))
//    @JsonIgnoreProperties
//    Set<Charity> charitiesOfTheUser = new HashSet<>();

}
