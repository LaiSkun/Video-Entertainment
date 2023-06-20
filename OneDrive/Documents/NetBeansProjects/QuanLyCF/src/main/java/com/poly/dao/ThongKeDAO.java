/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String[] cols,Object...args){
        try{
            List<Object[]> list = new ArrayList<>();
            ResultSet rs =DatabaseConnection.query(sql,args);
            while (rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i = 0;i<cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getDoanhThuNam(int nam){
        String sql = "{CALL  sp_ThongKeDoanhThuTheoNam(?)}";
        String[] cols = {"MaHD","TongTien","MonThapNhat","MonCaoNhat"};
        return this.getListOfArray(sql, cols, nam);
    }
    public List<Object[]> getDoanhThuThang(int thang){
        String sql = "{CALL sp_ThongKeDoanhThuTheoThang(?)}";
        String[] cols = {"MaHD","TongTien","MonThapNhat","MonCaoNhat"};
        return this.getListOfArray(sql, cols, thang);
    }
    public List<Object[]> getDoanhThuNgay(int ngay){
        String sql = "{CALL  sp_ThongKeDoanhThuTheoNgay(?)}";
        String[] cols = {"MaHD","TongTien","MonThapNhat","MonCaoNhat"};
        return this.getListOfArray(sql, cols, ngay);
    }
}
