/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.entity.HoaDonChiTiet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietDAO extends QuanLiCFDAO<HoaDonChiTiet, Integer>{
    final String Insert_SQL= "insert into ChiTietHoaDon(MaHD,MaMon,SoLuong,ThanhTien,GhiChu) values(?,?,?,?,?)";
    final String Update_SQL="update HoaDonChiTiet set MaMon=?,TenMon=?,SoLuong=?,DonGia=? where MaHDCT = ? ";
    final String Delete_SQL="delete from HoaDonChiTiet where MaHDCT=?";
    final String SelectAll_SQL ="select *from HoaDonChiTiet";
    final String SelectByID_SQL ="select *from HoaDonChiTiet where MaHDCT=?";
    final String Delete_ALL="delete from HoaDonChiTiet";
    
    @Override
    public void insert(HoaDonChiTiet entity) {
       DatabaseConnection.update(Insert_SQL,entity.getMaHD(),entity.getMaMon(),entity.getSoLuong(),entity.getThanhTien(),entity.getGhiChu());
    }
    
    @Override
    public void update(HoaDonChiTiet entity) {
        DatabaseConnection.update(Update_SQL,entity.getMaHD(),entity.getMaMon(),entity.getTenMon(),entity.getSoLuong(),entity.getThanhTien());
    }

    @Override
    public void delete(Integer key) {
       DatabaseConnection.update(Delete_SQL,key);
    }
    public void deleteALL(){
        DatabaseConnection.update(Delete_ALL);
    }
    @Override
    public List<HoaDonChiTiet> selectAll() {
       
        return selectBySQL(SelectAll_SQL);
       
    }

    @Override
    public HoaDonChiTiet selectById(Integer key) {
        List<HoaDonChiTiet> list = selectBySQL(SelectByID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
       
    }

    @Override
    protected List<HoaDonChiTiet> selectBySQL(String sql, Object... args) {
       List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseConnection.query(sql, args);
            while(rs.next()){
                HoaDonChiTiet entity = new HoaDonChiTiet();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setMaMon(rs.getString("MaMon"));
                entity.setTenMon(rs.getString("TenMon"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setThanhTien(rs.getDouble("ThanhTien"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
