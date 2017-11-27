package com.lihao.springboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author bencong.lh
 * @version $Id: UserBalance, v0.1 2017年11月24日 下午7:06 bencong.lh Exp $
 */

@Entity
public class UserBalance {

    @Id
    @GeneratedValue
    private Long id;

    private String user;

    private long balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
