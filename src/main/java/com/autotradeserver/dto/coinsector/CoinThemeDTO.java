package com.autotradeserver.dto.coinsector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity(name = "coins")
@AllArgsConstructor
@NoArgsConstructor
public class CoinThemeDTO {

    @EmbeddedId
    private CoinThemeDTOPK coinThemeDTOPK;
}
