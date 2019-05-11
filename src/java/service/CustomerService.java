/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.sql.DataSource;

/**
 *
 * @author ALI
 */
@WebService(serviceName = "CustomerService")
public class CustomerService {
    @Resource(name = "myAirDb")
    private DataSource myAirDb;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "customerDetail")
    public String customerDetail(@WebParam(name = "name") String name, @WebParam(name = "email") String email, @WebParam(name = "address") String address, @WebParam(name = "contact") String contact, @WebParam(name = "choice") int choice, @WebParam(name = "date") String date, @WebParam(name = "cost") float cost) throws Exception {
       Connection con=   myAirDb.getConnection();
        String squery="insert into CUSTOMER_DB(cno,u_id,org_name,email,contact,amount,address,doj)"
                + " values(sno.nextval,?,?,?,?,?,?,?)";
         
  PreparedStatement pst = con.prepareStatement(squery);
  pst.setInt(1, choice);
  pst.setString(2, name);
   pst.setString(3, email);
  pst.setString(4, contact);
   pst.setFloat(5, cost);
    pst.setString(6, address);
    DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    Date dateset=(Date)formatter.parse(date);
     pst.setString(7, dateset.toString());
     
pst.executeQuery(); 
  

	System.out.println("Date before Addition: "+date);
	//Specifying date format that matches the given date
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c = Calendar.getInstance();
	try{
	   //Setting the date to the given date
	   c.setTime(sdf.parse(date));
	}catch(ParseException e){
		e.printStackTrace();
	 }
	   
	//Number of Days to add
	c.add(Calendar.DAY_OF_MONTH, 30);  
	//Date after adding the days to the given date
	String newDate = sdf.format(c.getTime());  
	//Displaying the new Date after addition of Days
    
 String query="insert into CHEF_DB(SNO,O_ID,DATE_TARGET)"
                + " values(sno.nextval,?,?)";
         PreparedStatement pst1 = con.prepareStatement(query);
  pst1.setString(1, name);
  pst1.setString(2, newDate);
   
pst1.executeQuery(); 
        return "SUCESS";
    }
}
