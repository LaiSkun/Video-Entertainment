/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.ultils;

import com.poly.entity.NhanVien;

/**
 *
 * @author Admin
 */
public class Auth {
     //Đối tượng này sẽ chứ thông tin người sử dụng đăng nhập vào
    public static NhanVien user = null;
    //Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất 
    public static void clear(){
        Auth.user=null;
    }
    public static boolean isLogin(){
        return Auth.user != null;
    }
    public static boolean isManager(){
        return Auth.isLogin() && user.getVaiTro();
    }
}
