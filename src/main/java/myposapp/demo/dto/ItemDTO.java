package myposapp.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myposapp.demo.entity.enums.MeasuringUnitType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String itemName;
    private MeasuringUnitType unitType;
    private double balQty;
    private double supplierPrice;
    private double sellingPrice;

}

//A DTO only saves the data which are transferred from the frontend
