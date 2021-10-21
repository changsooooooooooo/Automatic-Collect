package com.autotradeserver.data.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "coin_theme")
public class CoinThemeDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coin_name")
    private CoinDAO coinDAO;

    @Column(name="coin_category")
    private String coinCategory;

    @Builder
    public CoinThemeDAO(CoinDAO coinDAO, String coinCategory){
        this.coinDAO = coinDAO;
        this.coinCategory = coinCategory;
    }
}
