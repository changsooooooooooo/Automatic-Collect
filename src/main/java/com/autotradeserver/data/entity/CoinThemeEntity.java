package com.autotradeserver.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "coin_theme")
public class CoinThemeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coin_name")
    private CoinEntity coinEntity;

    @Column(name="coin_category")
    private String coinCategory;

    @Builder
    public CoinThemeEntity(CoinEntity coinEntity, String coinCategory){
        this.coinEntity = coinEntity;
        this.coinCategory = coinCategory;
    }
}
