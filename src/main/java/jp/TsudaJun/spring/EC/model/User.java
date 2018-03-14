package jp.TsudaJun.spring.EC.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.TsudaJun.spring.EC.validation.User.AlreadyExists;

@Entity
@Table (name="user")
public class User{


	@Id
	@Column
	@NotEmpty(message="ユーザIDを入力してください")
	@AlreadyExists
    private String userid;
	
	@Column
	@NotEmpty(message = "パスワードを入力してください")
	private String password;
	
	@Column
	@NotEmpty(message="ユーザネームを入力してください")
	private String username;
	
	@Column(insertable = false, updatable = false)
	private Timestamp ins_ymd;

	@Column(insertable = false, updatable = true)
	private Timestamp upd_ymd;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="userid", referencedColumnName = "userid" )
	private List<Item> items = null;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="userid", referencedColumnName = "userid" )
	private List<Sell> sells = null;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="userid", referencedColumnName = "userid" )
	private List<UserAddress> addresses = null;
	
	
	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}
	
	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}


}
