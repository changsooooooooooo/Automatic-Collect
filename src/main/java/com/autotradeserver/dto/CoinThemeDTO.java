package com.autotradeserver.dto;

import com.autotradeserver.dto.CoinThemeDTOPK;
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
