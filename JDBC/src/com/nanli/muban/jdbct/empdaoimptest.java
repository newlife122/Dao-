package com.nanli.muban.jdbct;

import com.nanli.muban.dao.imp.EmpDaoImpl;
import com.nanli.muban.javabean.Emp;

import java.util.List;

/**
 * @author raoxin
 */
public class empdaoimptest {
    public static void main(String[] args) {
        EmpDaoImpl a = new EmpDaoImpl();
        //获取测试
        List<Emp> list = a.getEmps();
        for (Emp emp : list) {
            System.out.println(emp);
        }
        //获取单个测试
        Emp empa = a.getEmpById(1);
        System.out.println(empa);
        //我们这个表创建的是unique，name和username不可以重复
        //        empa.setName("lwx1");
        //        empa.setUsername("lwx1");
        //        //更新测试
        //        a.insertEmp(empa);
            a.deleteEmpById(13);
    }
}
