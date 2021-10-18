package com.autotradeserver.data.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="coin_list")
public class CoinDAO {

    @Id
    @Column(name="coin_name")
    private String coinName;

    @OneToMany(mappedBy = "coinDAO")
    private List<CoinThemeDAO> coinThemeDAOList = new ArrayList<>();

}
