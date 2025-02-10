package com.mj.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.model.AccountOpeningForm;
import com.mj.model.Account_Details;
import com.mj.reposiroty.AccountDetailsRepository;
import com.mj.reposiroty.AccountOpeningFormRepository;

@Service("Bank_Account_Service")
public class AccountServiceImpl implements IAccountService {

	//Account Opening Form Repository
	@Autowired
	private AccountOpeningFormRepository aofrRepo;
	   
	//Account Details Repository
	@Autowired
	private AccountDetailsRepository adRepo;
	
	
//====================Account Opening Form Details=======================
	@Override
	public Account_Details formDetails(AccountOpeningForm aof) 
	{
		//Create Object
		 Account_Details ad = new Account_Details();
		
		
		if(aof.getMobileNo() !=ad.getMobileNo())
		{
			if(aof !=null)
			{
				
				
				 // Save the Account Opening Form
		        AccountOpeningForm accountForm = aofrRepo.save(aof);
		        
		        // Generate a random 10-digit account number
		        Long accountNo = (long)(Math.random() * 10000000000L);  // 10 digits
		        
		       
		        
		        // Ensure the number is 10 digits (if necessary)
		        while (accountNo < 1000000000L) {
		            accountNo = (long)(Math.random() * 10000000000L); // Regenerate if it's less than 10 digits
		        }
		        
		        
		        // Generate a random 9-digit CUSTOMER ID
		        Integer custId = (int)(Math.random() * 1000000000);  // 10 digits
		        
		        
		        // Ensure the number is 9 digits (if necessary)
		        while (custId < 100000000L) {
		        	custId = (int)(Math.random() * 100000000); // Regenerate if it's less than 10 digits
		        }
		        
		        
		        // Create Account_Details object and set the account number
		       // Account_Details ad = new Account_Details();
		        ad.setAccountNo(accountNo);//Taking Random 10 digits Numbers 
		        ad.setBranchName(accountForm.getBranchName());
		        ad.setTypeOfAccount(accountForm.getTypeOfAccount());
		        ad.setName(accountForm.getName());
		        ad.setDateOfBirth(accountForm.getDateOfBirth());
		     //   ad.setNameOfTheFather(accountForm.getNameOfTheFather());
		        ad.setMaritalStatus(accountForm.getMaritalStatus());
		        ad.setAdress(accountForm.getAdress());
		        ad.setCustomerId(custId);//Taking Random 9 digits Numbers
		        ad.setNationality(accountForm.getNationality());
		        ad.setLd(LocalDate.now()); // Account Opening Date 
		        ad.setModeOfoperation(accountForm.getTypeOfAccount());
		        ad.setPassBookIssueDate(LocalDate.now().plusDays(4));
				ad.setMobileNo(accountForm.getMobileNo());
				ad.setEmailId(accountForm.getEmailID());
				ad.setPanNo(accountForm.getPanNo());
				//ad.setNomineeName(null);
				
				 // Save the Account Details
				 Account_Details acountDet = adRepo.save(ad);
				 return acountDet;
			}
			
			
			
		}
		return ad;
		
		
		
	}
	
// ===============Check Account =============================

	
	  @Override public Account_Details check_Account(Integer cId ) 
	  {
		  
	  //find the Id
	  Optional<Account_Details> idC=adRepo.findById(cId);
	  
	  
	  if(idC.isPresent()) 
	  {
		  Account_Details account=idC.get(); 
		  return account; 
	  } 
	
	  else { 
		 throw new IllegalArgumentException("Invalid Customer Id");
		  }
	  
	  
	  }
	 
	  
//=============================================================

	@Override
	public String update(Account_Details ad) {
		
		//Get the Account Details 
		Optional<Account_Details> oad= adRepo.findById(ad.getCustomerId());
		
		if(oad.isPresent())
		{
			
			adRepo.save(ad); // Save the Account Details
			
			
			 return "updated"; 
					 
		}
		else
		{
			return "Invalid Customer Id";
		}
		
		
	}
	 
	//=============================================================
	
	
	@Override
	public Account_Details updateDetails(Integer cId, Long mobileNo) 
	{
		
		Optional<Account_Details> CID=adRepo.findById(cId);
		
		if(CID.isPresent())
		{
			CID.get().setMobileNo(mobileNo); //Save the Mobile Number
			
			Account_Details ade=adRepo.save(CID.get()); 
			return ade;
		}
		else
		{
			
			 throw new IllegalArgumentException("Invalid Customer Id");
			
		}
		
		
		
	}
}
