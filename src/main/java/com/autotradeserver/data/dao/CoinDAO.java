package com.autotradeserver.data.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name="coin_list")
public class CoinDAO {

    @Id
    @Column(name="coin_name")
    private String coinName;

    @Builder
    public CoinDAO(String coinName){
        this.coinName = coinName;
    }
}
