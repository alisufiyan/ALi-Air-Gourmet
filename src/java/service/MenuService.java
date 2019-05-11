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
@WebService(serviceName = "MenuService")
public class MenuService {
    @Resource(name = "myAirDb")
    private DataSource myAirDb;


    /**
     * Web service operation
     */
    @WebMethod(operationName = "menu_design")
    public String menu_design(@WebParam(name = "Item1") String Item1, @WebParam(name = "Item2") String Item2, @WebParam(name = "Item3") String Item3, @WebParam(name = "Item4") String Item4, @WebParam(name = "Item5") String Item5, @WebParam(name = "Price1") float Price1, @WebParam(name = "Price2") float Price2, @WebParam(name = "Price3") float Price3, @WebParam(name = "Price4") float Price4, @WebParam(name = "Price5") float Price5) throws SQLException {
        Connection con=   myAirDb.getConnection();
    
   String squery="insert into warehouse_db(sno,org_name,email,contact,date,address,uamount) values(sno.nextval,?,?)";
        
  PreparedStatement pst = con.prepareStatement(squery);
  pst.setString(1, Item1);
  pst.setFloat(2, Price1);
pst.executeQuery();
         return "success";

    }
}
