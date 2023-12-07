package site.shoplist.Slist.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "pharmacy")
public class Pharmacy {

    private String name;
    private int status;
    private int count;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
