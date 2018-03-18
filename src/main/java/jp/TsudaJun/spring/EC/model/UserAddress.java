package jp.TsudaJun.spring.EC.model;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import jp.TsudaJun.spring.EC.validation.UserAddress.IsNumber;

@Entity
@Table(name="useraddress")
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressid;
	
	@Column
	@NotEmpty(message="氏名を入力してください")
	private String hostname;

	@Column
	@IsNumber(message="郵便番号は数字で入力してください")
	@Size(min=7, max=7, message="郵便番号は7桁で入力してください")
	private String postcode;
	
	@Column
	@NotEmpty(message="住所を入力してください")
	private String address;
	
	@Column
	@IsNumber(message="電話番号は数字で入力してください")
	@Size(min=7, max=11, message="電話番号は7桁以上11桁以下で入力してください")
	private String tel;
	
	@Column
	@NotEmpty
	private String userid;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="addressid", referencedColumnName = "addressid" )
	private List<Sell> sells = null;
	
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	
	public int getAddressid() {
		return addressid;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode ;
	}
	
	public  String getPostcode() {
		return postcode;
	}
	
	public void setAddress(String address) {
		this.address = address ;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setTel(String tel) {
		this.tel = tel ;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setUserid(String userid) {
		this.userid = userid ;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname ;
	}
	
	public  String getHostname() {
		return hostname;
	}
}
