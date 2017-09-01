package org.wuxi.fudan.syfw.model.hibernate;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * User entity. @author MyEclipse Persistence Tools
 * 用户信息管理，处理用户登录，注册
 */

@XmlRootElement(name="User")
public class User  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
     private String username;
     private String password;
     private String role;
     private String phone;


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String username, String password, String role, String phone) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
   


}