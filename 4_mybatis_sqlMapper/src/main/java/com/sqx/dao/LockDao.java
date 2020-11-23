package com.sqx.dao;

import com.sqx.bean.Lock;

/**
 * @author SQX
 * @date 2020/11/11 - 16:06
 */
public interface LockDao {

    //查锁的时候将所有钥匙也查出来
    public Lock getLockById(Integer id);


    public Lock getLockByIdSimple(Integer id);


    public Lock getLockByIdStep(Integer id);




}
