package com.lihao.springboot.repositories;

import javax.persistence.LockModeType;

import com.lihao.springboot.models.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

/**
 * @author bencong.lh
 * @version $Id: UserBalanceRepository, v0.1 2017年11月24日 下午7:08 bencong.lh Exp $
 */

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {

    // 使用OPTIMISTIC和PESSIMISTIC_FORCE_INCREMENT都不行
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    UserBalance findByUser(String user);
}
