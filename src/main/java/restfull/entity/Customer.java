package restfull.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//    @OneToMany( mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Orders> orders;

    private String name;

    private String country;

//    public void putOrders(Orders orders){
//        this.orders.add(orders);
//    }

    private String address;

    private String phone;
}
