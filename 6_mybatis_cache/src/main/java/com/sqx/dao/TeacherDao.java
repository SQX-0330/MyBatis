package com.sqx.dao;

import com.sqx.bean.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SQX
 * @date 2020/11/12 - 20:13
 */
public interface TeacherDao {

    public Teacher getTeacherById(Integer id);

    public List<Teacher> getTeacherByCondition(Teacher teacher);


    public List<Teacher> getTeacherByIdIn(@Param("ids") List<Integer> list);


    public List<Teacher> getTeacherByConditionChoose(Teacher teacher);

    public int updateTecher(Teacher teacher);
}
