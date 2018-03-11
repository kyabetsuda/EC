package jp.TsudaJun.spring.EC.model;

import java.sql.Timestamp;
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
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemid;
	
	@Column
	@NotEmpty(message="商品名を入力してください")
	private String itemname;
	
	@Column
	private int includingtax;
	
	@Column
	private int tax;
	
	@Column
	private int price;
	
	@Column(insertable = false, updatable = false)
	private Timestamp insymd;

	@Column(insertable = false, updatable = true)
	private Timestamp updymd;

	@Column
	private String userid;
	
	@Column
	private int itemattribute;
	
	@Column
	private int stock;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="itemid", referencedColumnName = "itemid" )
	private List<Sell> sells = null;
	
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	
	public int getItemid() {
		return itemid;
	}
	
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	public String getItemname() {
		return itemname;
	}
	
	public void setIncludingtax(int includingtax) {
		this.includingtax = includingtax;
	}
	
	public int getIncludingtax() {
		return includingtax;
	}
	
	public void setTax(int tax) {
		this.tax = tax;
	}
	
	public int getTax() {
		return tax;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	
	public Timestamp getInsymd() {
		return insymd;
	}
	
	public void setUpdymd(Timestamp updymd) {
		this.updymd = updymd;
	}
	
	public Timestamp getUpdymd() {
		return updymd;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setItemattribute(int itemattribute) {
		this.itemattribute = itemattribute;
	}
	
	public int getItemattribute() {
		return itemattribute;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getStock() {
		return stock;
	}
}
