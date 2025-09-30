package com.arka.currency_service.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DtoCurrency {

    private Double currency;
    private Integer price;
    private Double newPrice = currency * price ;

}
