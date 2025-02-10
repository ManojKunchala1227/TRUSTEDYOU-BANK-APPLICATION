package com.mj.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.model.AccountOpeningForm;
import com.mj.model.Account_Details;
import com.mj.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/Bank_Account_")
@Tag(name="TRUSTEDYOU BANK", description="of India")
public class BankAccountRestController 
{
	//Get IAccount Service Interface 
	@Autowired
	private IAccountService accountService;
	
	
//=================================================================================================
	@Operation(summary="FILL THE FORM")
	@PostMapping("/Opening_Form")
	public ResponseEntity<?> accountOpeningForm(@RequestBody AccountOpeningForm aop)
	{
		try {
			Account_Details ad= accountService.formDetails(aop);
			return new ResponseEntity<Account_Details> (ad,HttpStatus.OK);
		}
		catch(Exception e)
		{
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
//=================================================================================================
	@Operation(summary="CHECK THE ACCOUNT DETAILS")
	@GetMapping("/Account_Details/{cId}")
	
	public ResponseEntity<?> getAccountDetails(@PathVariable Integer cId )
	{
		try
		{
			Account_Details ade=accountService.check_Account(cId);
			
			return new ResponseEntity<Account_Details>(ade,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//=================================================================================================
	@Operation(summary="UPDATE THE ACCOUNT")
	@PutMapping("/Update_Account")
	public ResponseEntity<String> updateAccount(@RequestBody Account_Details ad )
	{
		
		try
		{

			String ade=accountService.update(ad);
			return new ResponseEntity<String>(ade,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//=================================================================================================
	@Operation(summary="UPDATE THE MOBILE NUMBER")
	@PutMapping("/Update_Details/{cId}/{mobile}")
	public ResponseEntity<?> updateDetails(@PathVariable Integer cId,@PathVariable Long mobile )
	{
		
		try
		{
			Account_Details ade= accountService.updateDetails(cId, mobile);
			
			return new ResponseEntity<Account_Details>(ade,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
