package com.autotradeserver.data.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="coin_list")
public class CoinDAO {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="coin_name")
    @OneToMany(mappedBy = "coinDAO")
    private List<CoinThemeDAO> coinThemeDAOList;
}
