/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.entity.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Admin
 */
public class KhachHangDAO extends QuanLiCFDAO<KhachHang, Integer>{
    final String Insert_SQL= "insert into KhachHang (TenKH,GioiTinh,DiaChi,NgaySinh,Email,SDT) values (?,?,?,?,?,?)";
    final String Update_SQL="update KhachHang set TenKH=?,GioiTinh=?,DiaChi=?,NgaySinh=?,Email=?,SDT=? where MaKH = ? ";
    final String Delete_SQL="delete from KhachHang where MaKH = ?";
    final String SelectAll_SQL ="select *from KhachHang";
    final String SelectByID_SQL ="select *from KhachHang where MaKH=?";
    
    @Override
    public void insert(KhachHang entity) {
        DatabaseConnection.update(Insert_SQL,entity.getTenKH(),entity.getGioiTinh(),entity.getDiaChi(),entity.getNgaySinh(),entity.getEmail(),entity.getSDT());
    }

    @Override
    public void update(KhachHang entity) {
        DatabaseConnection.update(Update_SQL, entity.getTenKH(),entity.getGioiTinh(),entity.getDiaChi(),entity.getNgaySinh(),entity.getEmail(),entity.getSDT(),entity.getMaKH());
    }

    @Override
    public void delete(Integer key) {
        DatabaseConnection.update(Delete_SQL, key);
    }

    @Override
    public List<KhachHang> selectAll() {
       
        return selectBySQL(SelectAll_SQL);
       
    }

    @Override
    public KhachHang selectById(Integer key) {
        List<KhachHang> list = selectBySQL(SelectByID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
        
    }

    @Override
    protected List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql, args);
            while(rs.next()){
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setTenKH(rs.getString("TenKH"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setEmail(rs.getString("Email"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setSDT(rs.getString("SDT"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
      
    }
     public List<KhachHang> selectByKeytWord(String keyword){
        String sql = "Select *from KhachHang where TenKH like ?";
        return this.selectBySQL(sql,"%" + keyword + "%");
    }
}
