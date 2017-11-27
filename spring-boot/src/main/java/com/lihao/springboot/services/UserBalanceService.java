package com.lihao.springboot.services;

import com.lihao.springboot.models.UserBalance;
import com.lihao.springboot.repositories.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bencong.lh
 * @version $Id: UserBalanceService, v0.1 2017年11月24日 下午7:09 bencong.lh Exp $
 */

@Service
public class UserBalanceService {

    private UserBalanceRepository userBalanceRepository;

    @Transactional
    public void addOne(String user) {
        UserBalance userBalance = findByName(user);
        System.out.println("修改前： " + userBalance.getBalance());
        userBalance.setBalance(userBalance.getBalance() + 1);
        save(userBalance);
        System.out.println("修改后： " + userBalance.getBalance());
    }

    public UserBalance findByName(String user) {
        return userBalanceRepository.findByUser(user);
    }

    public void save(UserBalance userBalance) {
        userBalanceRepository.save(userBalance);
    }

    @Autowired
    public void setUserBalanceRepository(UserBalanceRepository userBalanceRepository) {
        this.userBalanceRepository = userBalanceRepository;
    }
}
