package com.mj.service;

import com.mj.model.AccountOpeningForm;
import com.mj.model.Account_Details;

public interface IAccountService 
{
	public Account_Details formDetails(AccountOpeningForm aof);
	public Account_Details check_Account(Integer cId);
	public String update(Account_Details ad);
	public Account_Details updateDetails(Integer id, Long mobileNo);
	

}
