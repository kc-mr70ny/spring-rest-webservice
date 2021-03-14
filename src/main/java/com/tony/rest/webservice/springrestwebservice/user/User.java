package com.tony.rest.webservice.springrestwebservice.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="All Details about user")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="Name should have at least 2 characters")
    @ApiModelProperty(notes="Name should have at least 2 characters")
    private String name;

    @Past(message="Date must be in past")
    @ApiModelProperty(notes="Date must be in past")
    private Date birthDate;

    // excluded because it's initialized
    @OneToMany(mappedBy = "user")
    private final List<Post> posts = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
