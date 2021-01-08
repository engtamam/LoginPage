package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.PreparableStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginControler {
	Connection con ;
	PreparedStatement pst;
	ResultSet rs;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    void onLogin(ActionEvent event) {
    	String username = user.getText();
    	String password = pass.getText();

    	if(username.equals("") || password.equals(""))
    		JOptionPane.showMessageDialog(null,"username or password is blank");
    	else{//connect interface with database
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				String url       = "jdbc:mysql://localhost:3325/course101";
				String uname     = "root";
				String password2 = "";//for connection
				String query 	 = "select * from login where username=? and password=?";//for statement

				con = DriverManager.getConnection(url,uname,password2);
				pst = con.prepareStatement(query);
				pst.setString(1, username);
				pst.setString(2,password);
				rs = pst.executeQuery();
				if(rs.next())
					JOptionPane.showMessageDialog(null,"login success");
				else{
					JOptionPane.showMessageDialog(null,"login faild");
					user.setText("");
					pass.setText("");
					user.requestFocus();
				}
				pst.close();
				con.close();


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}

    }

}
