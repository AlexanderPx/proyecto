package com.pi06.bdd;


 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Conexion {
private Statement St; 
private String driver;
private String user;
private String pwd;
private String cadena;
private Connection con;

String getDriver()
{
return this.driver;
}
String getUser()
{
return this.user;
}
String getPwd()
{
return this.pwd;
}
String getCadena()
{
return this.cadena;
}
public Connection getConexion()
{ 
return this.con; 
}
public Conexion() {
this.driver ="oracle.jdbc.OracleDriver";
this.user="p54g6_oltp_na";
this.pwd="g06_4263";
this.cadena="jdbc:oracle:thin:@172.17.42.63:1521:orclupsoltp";
this.con=this.crearConexion();
}
public Connection crearConexion()
{ 
try
{
Class.forName(getDriver());
Connection con2;
                    con2 = DriverManager.getConnection(getCadena(),getUser(),getPwd());
                        
                         if(con2!=null)
        {
        System.out.println("Conexion exitosa a esquema HR");
        }
        else{System.out.println("Conexion fallida");}
        
return con2;
}
catch(Exception ee)
{
System.out.println("Error: " + ee.getMessage());
return null;
}
}
    

public String Ejecutar(String sql)
{
String error=":v";
try
{
St=getConexion().createStatement();
St.execute(sql);
error="Éxito al insertar los datos";
}
catch(Exception ex)
{
error = ex.getMessage();
}
return(error);
}

public ResultSet Consulta(String sql)
	{
	String error="";
	ResultSet reg=null;
	
	try
	{
	St=getConexion().createStatement();
	reg=St.executeQuery(sql);
	

	}
	catch(Exception ee)
	{
	error = ee.getMessage();
	}
	return(reg);
	}
	public void cerrarConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
   
