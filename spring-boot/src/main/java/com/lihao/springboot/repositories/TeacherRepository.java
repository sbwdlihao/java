package com.lihao.springboot.repositories;

import com.lihao.springboot.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bencong.lh
 * @version $Id: TeacherRepository, v0.1 2017年04月21日 下午6:54 bencong.lh Exp $
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

//    @Lock(value = LockModeType.WRITE)
    Teacher findByNo(String no);
}
