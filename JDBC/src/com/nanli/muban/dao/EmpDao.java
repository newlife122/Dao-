package com.nanli.muban.dao;

import com.nanli.muban.javabean.Emp;

import java.util.List;

/**
 * @author raoxin
 * 我们需要的操作先定义在这里
 * 一般来说XXX即为javabean的名字
 *  1.List<XXX> getXXXs 获取所有XXX信息
 *      1.一般来说增删改查，增和改需要输入改对象，删和查只要id就行了，故》》》增改删查
 *  2.void insertXXX(XXX book) 保存一个XXX对象
 *  3.void updateXXX(XXX book)
 *  4.void deleteXXXById(String id)
 *  5.XXX getXXXById()
 *  6.大致就是对这个数据库的一些是实现,更具体的实现还可以有如下
 *      1.
 */
public interface EmpDao {
    List<Emp> getEmps();
    void insertEmp(Emp emp);
    void updateEmp(Emp emp);
    void deleteEmpById(int empid);

    Emp getEmpById(int empid);
}
