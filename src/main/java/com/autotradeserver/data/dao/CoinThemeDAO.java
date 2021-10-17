package com.autotradeserver.data.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity(name = "coin_theme")
@AllArgsConstructor
@NoArgsConstructor
public class CoinThemeDAO {

    @EmbeddedId
    private CoinThemeDAOPK coinThemeDAOPK;
}
