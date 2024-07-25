package com.nanli.muban.dao;

import com.nanli.muban.javabean.Emp;

import java.util.List;

/**
 * @author raoxin
 * 我们需要的操作先定义在这里
 * 一般来说XXX即为javabean的名字
 *  1.List<XXX> getXXXs 获取所有XXX信息
 *  2.void saveXXX(XXX book) 保存一个XXX对象
 *  3.void deleteXXXById(XXX book)
 *  4.void updateXXX(XXX book)
 *  5.XXX getXXXById()
 *  6.大致就是对这个数据库的一些是实现
 */
public interface EmpDao {
    List<Emp> getEmps();

}
