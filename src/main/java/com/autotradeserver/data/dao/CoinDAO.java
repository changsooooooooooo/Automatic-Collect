package com.autotradeserver.data.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name="coin_list")
public class CoinDAO {

    @Id
    @Column(name="coin_name")
    private String coinName;

    @OneToMany(mappedBy = "coinDAO", cascade = CascadeType.ALL)
    private final List<CoinThemeDAO> coinThemeDAOList = new ArrayList<>();

    @Builder
    public CoinDAO(String coinName){
        this.coinName = coinName;
    }
}
