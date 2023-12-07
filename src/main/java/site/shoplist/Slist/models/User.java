package site.shoplist.Slist.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "users")
public class User {

    private String email;
    private String password;
    private int role;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
