package com.sqx.dao;

import com.sqx.bean.Key;

import java.util.List;

/**
 * @author SQX
 * @date 2020/11/10 - 19:26
 */
public interface KeyDao {
    /**
     * 将钥匙和锁的信息一起查出
     * @param id
     * @return
     */
    public Key getKeyById(Integer id);

    public Key getKeyByIdSimple(Integer id);

    public List<Key> getKeysByLockId(Integer id);
}

