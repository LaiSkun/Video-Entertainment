/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.entity.Menu;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MenuDAO extends QuanLiCFDAO<Menu, String>{
    final String Insert_SQL= "insert into Menu(MaMon,TenMon,SizeMon,GiaBanMon,GhiChuMon,Hinh) values(?,?,?,?,?,?)";
    final String Update_SQL="update Menu set TenMon=?,SizeMon=?,GiaBanMon=?,GhiChuMon=?,Hinh=? where MaMon = ? ";
    final String Delete_SQL="delete from Menu where MaMon = ?";
    final String SelectAll_SQL ="select *from Menu";
    final String SelectByID_SQL ="select *from Menu where MaMon=?";
    
    
    @Override
    public void insert(Menu entity) {
       DatabaseConnection.update(Insert_SQL, entity.getMaMon(),entity.getTenMon(),entity.getSizeMon(),entity.getGiaBanMon(),entity.getGhiChuMon(),entity.getHinh());
    }

    @Override
    public void update(Menu entity) {
       DatabaseConnection.update(Update_SQL,entity.getTenMon(),entity.getSizeMon(),entity.getGiaBanMon(),entity.getGhiChuMon(),entity.getHinh(),entity.getMaMon());
    }

    @Override
    public void delete(String key) {
       DatabaseConnection.update(Delete_SQL,key);
    }

    @Override
    public List<Menu> selectAll() {
       return selectBySQL(SelectAll_SQL);
    }

    @Override
    public Menu selectById(String key) {
        List<Menu> list = selectBySQL(SelectByID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Menu> selectBySQL(String sql, Object... args) {
        List<Menu> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql, args);
            while(rs.next()){
                Menu entity = new Menu();
                entity.setMaMon(rs.getString("MaMon"));
                entity.setTenMon(rs.getString("TenMon"));
                entity.setSizeMon(rs.getString("SizeMon"));
                entity.setGiaBanMon(rs.getInt("GiaBanMon"));
                entity.setGhiChuMon(rs.getString("GhiChuMon"));
                entity.setHinh(rs.getString("Hinh"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     public List<Menu> selectByKeytWord(String keyword){
        String sql = "Select *from Menu where TenMon like ?";
        return this.selectBySQL(sql,"%" + keyword + "%");
    }
}
