package com.autotradeserver.data.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "coin_theme")
@IdClass(CoinThemeDAOPK.class)
public class CoinThemeDAO {

    @Id
    @ManyToOne
    @JoinColumn(name="coin_name")
    private CoinDAO coinDAO;

    @Id
    @Column(name="coin_category")
    private String coinCategory;
}
