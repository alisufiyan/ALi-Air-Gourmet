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
@WebService(serviceName = "DispatcherService")
public class DispatcherService {
    @Resource(name = "myAirDb")
    private DataSource myAirDb;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dispatch")
    public String dispatch() throws Exception {
       Connection con=   myAirDb.getConnection();
   String selectSQL = "SELECT * FROM DISPATCHER_DB";
PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
ResultSet rs = preparedStatement.executeQuery( );
        if(rs.next()) 
            return rs.getString("CLIENT_NO")+"  "+rs.getString("DATE_DISPATCH");
        return "SUCESS";
    }
}
