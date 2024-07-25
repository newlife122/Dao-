package com.nanli.muban.jdbct;
import com.nanli.muban.javabean.Emp;
import com.nanli.muban.utils.JDBCUtilsByDruid;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author raoxin
 */
public class test {
    public static void main(String[] args) throws Exception {
        test aq = new test();
        Class<?> a = Class.forName("com.nanli.muban.javabean.Emp");
        Properties peo = new Properties();
        peo.load(test.class.getClassLoader().getResourceAsStream("druid.properties"));
        List<Emp> list= aq.getList(Emp.class,"select * from emp");
        for (Emp emp : list) {
            System.out.println(emp.getName());
        }
        System.out.println(list);
    }

    /**
     * @param clazz
     * @param sql
     * @param args
     * @return
     * @param <T>
     * @throws Exception
     */
    public <T> List<T> getList(Class<T> clazz, String sql, Object... args) throws Exception {
        ArrayList<T> List = new ArrayList<>();
        //我们要如何通过反射
        //得到结果集的元数据：ResultSetMetaData，就是可以得到数据库的字段的一个
        //1.rsmd.getColumnCount()数据库一共多少字段
        //2.rsmd.getColumnLabel(i + 1);//可以获得字段的名称
        //3.rs.getObject(i + 1);//获取当前行第i+1列的Object的值
        //4.filed.set(o,value);//设置该对象，对应字段的值
        //rs.getObject(i + 1)返回数据库的每一行
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;

        try {
            con = JDBCUtilsByDruid.getConnection();
            pst = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i+1,args[i]);
            }
            rst = pst.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();
            int count = rsmd.getColumnCount();
            while (rst.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < count; i++) {
                    Object value = rst.getObject(i+1);
                    String fieldstring = rsmd.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(fieldstring);
                    field.setAccessible(true);
                    field.set(t,value);
                }
                List.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            rst.close();
            pst.close();
            con.close();
        }

        return List;
    }
}
