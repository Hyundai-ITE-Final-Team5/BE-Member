package com.mycompany.ite5bemember.dto;

import java.util.Map;

import lombok.Data;

@Data
public class CartedProduct {
	private String bname;
	private String pid;
	private String pname;
	private String pccolorcode;
	private String pcimg1;
	private String psid;
	private int psstock;
	private String psize;
	private int pquantity;
	private int pcprice;
	private String pcchipimg;
	private Map<Object, Object> colornsize;
}
