package com.mycompany.ite5bemember.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DownloadedCoupon {
	private String ecoupontitle;
	private int ediscount;
	private int cpstatus;
	private Date cpissuedate;
	private Date cpexpiredate;
}
