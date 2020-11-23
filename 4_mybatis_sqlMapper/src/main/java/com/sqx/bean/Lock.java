package com.sqx.bean;

import java.util.List;

/**
 * @author SQX
 * @date 2020/11/10 - 19:07
 */
public class Lock {
    private Integer id;
    private String lockName;
    //查询锁的时候把所有的钥匙查出来
    private List<Key> keys;
    //1-1关联  1-n关联  n-n关联
    //1-n:外键放在多的一端
    //n-n:中间表来存储对应关系

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }



    @Override
    public String toString() {
        return "Lock{" +
                "id=" + id +
                ", lockName='" + lockName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }
}
