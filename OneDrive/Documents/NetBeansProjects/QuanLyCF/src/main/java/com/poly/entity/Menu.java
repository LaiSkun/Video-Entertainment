/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.entity;

/**
 *
 * @author Admin
 */
public class Menu {
    String MaMon;
    String TenMon; 
    String SizeMon;
    int GiaBanMon ;
    String GhiChuMon;
    String Hinh;

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }
    
    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public String getSizeMon() {
        return SizeMon;
    }

    public void setSizeMon(String SizeMon) {
        this.SizeMon = SizeMon;
    }

    public int getGiaBanMon() {
        return GiaBanMon;
    }

    public void setGiaBanMon(int GiaBanMon) {
        this.GiaBanMon = GiaBanMon;
    }

    public String getGhiChuMon() {
        return GhiChuMon;
    }

    public void setGhiChuMon(String GhiChuMon) {
        this.GhiChuMon = GhiChuMon;
    }
    
}
