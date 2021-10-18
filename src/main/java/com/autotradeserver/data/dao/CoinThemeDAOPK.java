package com.autotradeserver.data.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class CoinThemeDAOPK implements Serializable {

    private CoinDAO coinDAO;
    private String coinCategory;

}
