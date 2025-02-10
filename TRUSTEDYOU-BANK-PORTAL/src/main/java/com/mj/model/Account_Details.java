package com.mj.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name="TRUSTEDYOU_BANK_ACCOUNT ")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Account_Details 
{
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="CUSTOMER_ID")
	private Integer customerId;
	
	@Column(name="ACCOUNT_NO")
	@NonNull
	private Long accountNo;
	
	@Column(name="BRANCH_NAME")
	@NonNull
	private String branchName;
	
	@NonNull
	@Column(name="TYPE_OF_ACCOUNT")
	private String typeOfAccount;
	
	@Column(name="NAME")
	@NonNull
	private String name;
	
	@Column(name="DATE_OF_BIRH")
	@NonNull
	private String dateOfBirth;
	
//	@Column(name="FATHER_NAME")
//	@NonNull
//	private String NameOfTheFather;
//	
	
	@Column(name="MARITAL_STATUS")
	@NonNull
	private String maritalStatus;
	
	@Column(name="OCCUPATION")
	@NonNull
	private String occupation="OTHERS";
	
	@Column(name="ADRESS")
	@NonNull
	private String adress;
	
	@Column(name="NATIONALITY")
	@NonNull
	private String nationality;
	
	@Column(name="ACCOUNT_OPENED_ON")
	@NonNull
	private LocalDate ld;
	
	@Column(name="MODE_OF_OPERATION")
	@NonNull
	private String modeOfoperation;
	
	@Column(name="PASS_BOOK_ISSUE_DATE")
	@NonNull
	private LocalDate passBookIssueDate;
	
	@Column(name="MOBILE_NO")
	@NonNull
	private Long mobileNo;
	
	@Column(name="EMAIL_ID")
	@NonNull
	private String emailId;
	
	@Column(name="PAN_NO")
	@NonNull
	private String panNo;
	
	@Column(name="KYC_IDENTIFIER")
	//@NonNull
	private String kyc_Identifier;
	
	@Column(name="NOMINEE_REG")
	private String nomineeReg="Y";
	
	@Column(name="NOMINEE_NAME")
	//@NonNull
	private String nomineeName="z";

}