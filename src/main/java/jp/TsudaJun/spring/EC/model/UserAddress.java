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

@Entity
@Table(name="useraddress")
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressid;
	
	@Column
	private String hostname;

	@Column
	private String postcode;
	
	@Column
	private String address;
	
	@Column
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
