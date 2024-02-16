package myposapp.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myposapp.demo.entity.enums.MeasuringUnitType;

import javax.persistence.*;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
// We use data annotation on behalf of getters, setter and tostring
@Data

public class Item {

    @Id
    @Column(name = "itemid" , length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "itemname")
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "unittype", length = 100, nullable = false)
    // here we are assigning a set of pre-defined values as measuring units
    // for that, we use enums -- set of pre-defined constraints
    // MeasuringUnitType is an enum
    private MeasuringUnitType unitType;

    @Column(name = "balanceqty", length = 100, nullable = false)
    private double balQty;

    @Column(name = "supplierprice", length = 100, nullable = false)
    private double supplierPrice;

    @Column(name = "sellingprice", length = 100, nullable = false)
    private double sellingPrice;

    @Column(name = "active_status", columnDefinition = "TINYINT default 1")
    private boolean activeState;


}
