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
@WebService(serviceName = "KitchenService")
public class KitchenService {
    @Resource(name = "myAirDb")
    private DataSource myAirDb;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "chef_ws")
    public String chef_ws() throws Exception {
        
      Connection con=   myAirDb.getConnection();
   String selectSQL = "SELECT * FROM CHEF_DB";
PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
ResultSet rs = preparedStatement.executeQuery();

  if(rs.next()){
      
        String query="insert into DISPATCHER_DB(DNO,ORG,DATE_DISPATCH)"
                + " values(dno.nextval,?,?)";
         PreparedStatement pst1 = con.prepareStatement(query);
  pst1.setString(1,  rs.getString("O_ID"));
  pst1.setString(2, rs.getString("date_target"));
  ResultSet rs1=pst1.executeQuery();
	return 	 rs.getString("O_ID")+"  :   "+ rs.getString("date_target");
  }
       // return "Success";
        return null;
       
    

    }
}