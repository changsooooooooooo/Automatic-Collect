package com.autotradeserver.dto.coinsector;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CoinThemeDTOPK implements Serializable {

    @Column(name="coin_name")
    private String coinName;

    @Column(name="coin_category")
    private String coinCategory;

}
