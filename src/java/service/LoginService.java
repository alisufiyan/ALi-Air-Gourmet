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
@WebService(serviceName = "LoginService")
public class LoginService {
    @Resource(name = "myAirDb")
    private DataSource myAirDb;


    /**
     * Web service operation
     */
    @WebMethod(operationName = "verify")
    public String verify(@WebParam(name = "login_id") String login_id, @WebParam(name = "password") String password) throws Exception {
   Connection con=   myAirDb.getConnection();
   String selectSQL = "SELECT LOGIN_ID, PASSWORD FROM LOGIN WHERE PASSWORD = ?";
PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
preparedStatement.setString(1, password);

ResultSet rs = preparedStatement.executeQuery( );
while (rs.next()) {
    if(login_id.equals(rs.getString("LOGIN_ID")) && password.equals(rs.getString("PASSWORD"))){
	return 	"Success";
}}
        return "Failed";
    }
}
