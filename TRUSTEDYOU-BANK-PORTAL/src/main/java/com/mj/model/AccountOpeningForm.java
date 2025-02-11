package com.mj.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name="TRUSTEDYOU_BANK_OPENING_FORM ")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountOpeningForm 
{
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer sNo;
	
	@NonNull
	@Column(name="TYPE_OF_ACCOUNT")
	private String typeOfAccount;
	
	@Column(name="BRANCH_NAME")
	@NonNull
	private String branchName;
	
	@Column(name="NAME")
	@NonNull
	private String name;
	
	@Column(name="FATHER_NAME")
	@NonNull
	private String NameOfTheFather;
	
	@Column(name="DATE_OF_BIRH")
	@NonNull
	private String dateOfBirth;
	
	@Column(name="MARITAL_STATUS")
	@NonNull
	private String maritalStatus;
	
	@Column(name="GURDIAN_NAME")
	@NonNull
	private String NameOfTheGurdian;
	
	@Column(name="NATIONALITY")
	@NonNull
	private String nationality;
	

	@Column(name="RELIGION")
	@NonNull
	private String religion;
	
	@Column(name="CATEGERY")
	@NonNull
	private String categery;
	
	@Column(name="EDUCATION_QUALIFICATION")
	@NonNull
	private String educationQualification;
	
	@Column(name="PAN_NO")
	@NonNull
	private String panNo;
	
	@Column(name="MOBILE_NO")
	@NonNull
	private Long mobileNo;
	
	@Column(name="EMAIL_ID")
	@NonNull
	private String emailID;
	
	@Column(name="ID_PROOFS")
	@NonNull
	private String proofs;
	
	@Column(name="ADRESS")
	@NonNull
	private String adress;
	
	@Column(name="PINCODE")
	@NonNull
	private Long pinCode;
	
	
	
	

}
