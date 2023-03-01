package com.wallet;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class WalletDto { // POJO
    @Id
    private Integer id;
    private String name;
    private Double balance;
    // email, pWD, date of wallet creation

    public WalletDto() {
    }

    public WalletDto(Integer id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletDto walletDto = (WalletDto) o;
        return Objects.equals(id, walletDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
