package com.autotradeserver.data.dao;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CoinThemeDAOPK implements Serializable {

    @ManyToOne
    @JoinColumn(name="coin_name")
    private CoinDAO coinDAO;

    @Column(name="coin_category")
    private String coinCategory;

}
