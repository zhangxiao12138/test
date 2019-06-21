package com.firecontrol.domain.entity;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * Created by mariry on 2019/6/21.
 */
public class DemoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String itemName;
    private Double price;
    private Integer amount;
    private Integer df;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDf() {
        return df;
    }

    public void setDf(Integer df) {
        this.df = df;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
