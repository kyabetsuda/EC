package jp.TsudaJun.spring.EC.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sell")
public class Sell {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellid;
	
	@Column(insertable = false, updatable = false)
	private Timestamp boughtymd;
	
	@Column
	private int includingtax;
	
	@Column
	private int itemid;
	
	@Column
	private String userid;
	
	public void setSellid(int sellid) {
		this.sellid = sellid;
	}
	
	public int getSellid() {
		return sellid;
	}
	
	
	public Timestamp getBoughtymd() {
		return boughtymd;
	}
	
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	
	public int getItemid() {
		return itemid;
	}
	
	public void setIncludingtax(int includingtax) {
		this.includingtax = includingtax;
	}
	
	public int getIncludingtax() {
		return includingtax;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

}
