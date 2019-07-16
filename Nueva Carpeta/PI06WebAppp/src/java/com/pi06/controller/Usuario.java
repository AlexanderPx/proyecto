/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi06.controller;

import com.pi06.bdd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author herig
 */
@Named("Usuario")
@RequestScoped
public class Usuario {
    private String Cedula;
    private String Nombre;
    private String Correo;
    private String password;
public String registrar(){
    String msg="";
    if(BuscarUsuario(Cedula, Correo)){
    msg="Ya existe un usuario con las mismas credenciales";
    }else{
    Conexion con = new Conexion();
        //insert into tb_usuarios(ci,correo,nombre,password) values ('1720849346','a@a.com','admin','admin');
             String result="";
     PreparedStatement pr=null;
         String sql="INSERT INTO TB_USUARIOS (CI,CORREO,NOMBRE,PASSWORD)";
     sql+="VALUES (?,?,?,?)";
     
             try{
                 pr=con.getConexion().prepareStatement(sql);
                 pr.setString(1, Cedula);
                 pr.setString(2, Correo);
                 pr.setString(3, Nombre);
                 pr.setString(4, password);
                 if(pr.executeUpdate()==1){
                     msg="Usuario creado correctamente";
                     System.out.println("Usuario creado correctamente");
                 }else{
                    System.out.println("Error al intentar crear usuario");
                    msg="Error al intentar crear usuario";
                 }
             }catch (SQLException e){
                 System.out.println("Error en la insercion"+e.getMessage());
             }
             con.cerrarConexion();
    }
             return msg;
     
   }
public void login (){
    
}
    public boolean BuscarUsuario(String ci,String email) {
        	
		 Conexion con = new Conexion();
		ResultSet rs = null;
                boolean exist=false;
		String sql ="select ci,correo from tb_usuarios";
                       try {
			rs = con.Consulta(sql);
			while (rs.next()) {
                        String tempCI=rs.getString(1);
                        String tempCorreo=rs.getString(2);
                        if(tempCI.equals(ci)||tempCorreo.equals(email)){
                        exist=true;
                        return exist;
                        }
                        }
		} catch (Exception e) {
			System.out.println("Error login");
		}
		con.cerrarConexion();
        return exist;
    }
    public void crearsesion(Usuario user){
        FacesContext facesC= FacesContext.getCurrentInstance();
        HttpSession Sesion = (HttpSession) facesC.getExternalContext().getSession(true);
        Sesion.setAttribute("user", user);
                }
    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
