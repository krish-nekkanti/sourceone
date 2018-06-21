package com.ah8.app.ivservice.model;



import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Table(name="PAYMENTS")
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@paymentsId")
public class Payments implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="payments_id")
	public Integer paymentsId;
	
	@Column(name="PAYMENT_NAME")
	public String  paymentName;
	
	@Column(name="PAY_AMT")
	public Integer payAmt;
	
	@Column(name="DUE_DATE")
	public Date dueDate;
	
	@Column(name="TYPE")
	public String type;
	
	@Column(name="BANK_NAME")
	public String bankName;
	
	@Column(name="NAME")
	public String name;
	
	@Column(name="PHONE_NBR")
	public String phoneNbr;
	
	@Column(name="CARD_ACC_NO")
	public String cardAccNo;
	
	@Column(name="FREEQUENCY")
	public String freequency;
	
	@Column(name="PRIORITY")
	public String priority;
	
	@Column(name="STATUS")
	public String status;
	
	@Column(name="PAID_AMT")
	public Integer paitAmt;
	
	@Column(name="BALANCE_AMT")
	public Integer balanceAmt;
	
	@Column(name="REMARKS")
	public String remarks;

	public Payments() {
		
	}
	public Integer getPaymentsId() {
		return paymentsId;
	}

	public void setPaymentsId(Integer paymentsId) {
		this.paymentsId = paymentsId;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public Integer getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Integer payAmt) {
		this.payAmt = payAmt;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	public String getCardAccNo() {
		return cardAccNo;
	}

	public void setCardAccNo(String cardAccNo) {
		this.cardAccNo = cardAccNo;
	}

	public String getFreequency() {
		return freequency;
	}

	public void setFreequency(String freequency) {
		this.freequency = freequency;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPaitAmt() {
		return paitAmt;
	}

	public void setPaitAmt(Integer paitAmt) {
		this.paitAmt = paitAmt;
	}

	public Integer getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(Integer balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
} 