/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.sql.DataSource;

/**
 *
 * @author ALI
 */
@WebService(serviceName = "MenuWS")
public class MenuWS {
    @Resource(name = "myAirDb")
    private DataSource myAirDb;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "design")
    public String design(@WebParam(name = "Itema1") String Itema1, @WebParam(name = "Pricea1") float Pricea1) throws Exception {
    Connection con=   myAirDb.getConnection();
    
   String squery="insert into warehouse_db(sno,item_name,cost) values(sno.nextval,?,?)";
        
  PreparedStatement pst = con.prepareStatement(squery);
  pst.setString(1, Itema1);
  pst.setFloat(2, Pricea1);
  pst.executeQuery();

 return "SUCCESS";
   
       
    }
}
