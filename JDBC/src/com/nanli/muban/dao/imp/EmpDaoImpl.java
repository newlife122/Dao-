package com.nanli.muban.dao.imp;

import com.nanli.muban.dao.BasicDao;
import com.nanli.muban.dao.EmpDao;
import com.nanli.muban.javabean.Emp;

import java.util.List;

/**
 * @author raoxin
 */
public class EmpDaoImpl extends BasicDao<Emp> implements EmpDao {
    @Override
    public List<Emp> getEmps() {
        return this.getBeanList("select * from Emp");
    }

    @Override
    public void insertEmp(Emp emp) {
        this.update("INSERT INTO emp(id, username, password, name, create_time, update_time) VALUES(null,?,?,?,?,?)",
                emp.getUsername(),emp.getPassword(),emp.getName(),emp.getCreate_time(),emp.getUpdate_time());
    }


    @Override
    public void updateEmp(Emp emp) {
        this.update("UPDATE emp SET username = ?, password = ?, name = ?,create_time = ?, update_time = ? WHERE id = ?",
                emp.getUsername(),emp.getPassword(),emp.getName(),emp.getCreate_time(),emp.getUpdate_time(),emp.getId());
    }

    @Override
    public void deleteEmpById(int empid) {
        this.update("DELETE FROM emp WHERE id = ?",empid);
    }

    @Override
    public Emp getEmpById(int empid) {
        return this.getBean("select * from emp where id = ?",empid);
    }

}
