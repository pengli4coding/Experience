package com.pl.bean;

import com.pl.annotation.Column;
import com.pl.annotation.Id;
import com.pl.annotation.Table;

@Table("tb_animal")
public class Annimal {

	@Id("t_id")
	@Column("t_id")
	private Long id;
	private String name;
	private String food;
	@Column("MASTER_NAME")
	private String masterName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", food=" + food + ", masterName=" + masterName + "]";
	}
	
	
}
