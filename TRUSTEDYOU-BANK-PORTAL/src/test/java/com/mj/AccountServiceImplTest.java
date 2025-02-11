package com.mj;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mj.model.AccountOpeningForm;
import com.mj.model.Account_Details;
import com.mj.reposiroty.AccountDetailsRepository;
import com.mj.reposiroty.AccountOpeningFormRepository;
import com.mj.service.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;  // Service to be tested

    @Mock
    private AccountOpeningFormRepository aofrRepo;  // Mocked repository

    @Mock
    private AccountDetailsRepository adRepo;  // Mocked repository

    private AccountOpeningForm accountOpeningForm;
    private Account_Details accountDetails;

    @BeforeEach
    void setUp() {
        accountOpeningForm = new AccountOpeningForm();
        accountOpeningForm.setBranchName("Test Branch");
        accountOpeningForm.setName("John Doe");
        accountOpeningForm.setTypeOfAccount("Savings");
        accountOpeningForm.setMobileNo(9876543210L);
        accountOpeningForm.setEmailID("johndoe@test.com");
        accountOpeningForm.setPanNo("ABCDE1234F");
        accountOpeningForm.setNationality("Indian");
        accountOpeningForm.setDateOfBirth("01-01-1998");
        accountOpeningForm.setAdress("1-22/3-1, sriram");
        accountOpeningForm.setCategery("General");
        accountOpeningForm.setDateOfBirth("B Tech");
        accountOpeningForm.setMaritalStatus("Single");
        accountOpeningForm.setNameOfTheFather("veera");
        accountOpeningForm.setNameOfTheGurdian("veera");
        accountOpeningForm.setPinCode(345675L);
        accountOpeningForm.setProofs("pancard");
        accountOpeningForm.setReligion("Hindu");

        accountDetails = new Account_Details();
        accountDetails.setAccountNo(1234567890L);
        accountDetails.setCustomerId(987654321);
        accountDetails.setName("John Doe");
        accountDetails.setBranchName("Test Branch");
        
    }

    @Test
    void testFormDetails_ShouldCreateAccount() {
        // Arrange
        when(aofrRepo.save(any(AccountOpeningForm.class))).thenReturn(accountOpeningForm);
        when(adRepo.save(any(Account_Details.class))).thenReturn(accountDetails);
        when(adRepo.findById(anyInt())).thenReturn(Optional.empty());  // No account exists yet
        
        // Act
        Account_Details createdAccount = accountService.formDetails(accountOpeningForm);
        
        // Assert
        verify(aofrRepo, times(1)).save(any(AccountOpeningForm.class));
        verify(adRepo, times(1)).save(any(Account_Details.class));
        assert(createdAccount != null);
        assert(createdAccount.getName().equals("John Doe"));
    }
    
    //@Disabled
    @Test
    void testFormDetails_ShouldThrowExceptionWhenAccountAlreadyExists() {
        // Arrange
        lenient().when(aofrRepo.save(any(AccountOpeningForm.class))).thenReturn(accountOpeningForm);
        lenient().when(adRepo.findById(anyInt())).thenReturn(Optional.of(accountDetails));  // Account already exists
        
        // Act & Assert
        try {
            accountService.formDetails(accountOpeningForm);
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().contains("Customer Id is Alredy Exist"));
        }
    }


    @Test
    void testCheckAccount_ShouldReturnAccount() {
        // Arrange
        when(adRepo.findById(anyInt())).thenReturn(Optional.of(accountDetails));

        // Act
        Account_Details account = accountService.check_Account(accountDetails.getCustomerId());

        // Assert
        assert(account != null);
        assert(account.getName().equals("John Doe"));
    }

    @Test
    void testCheckAccount_ShouldThrowExceptionWhenAccountNotFound() {
        // Arrange
        when(adRepo.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        try {
            accountService.check_Account(accountDetails.getCustomerId());
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().contains("Invalid Customer Id"));
        }
    }

    @Test
    void testUpdate_ShouldReturnUpdatedMessage() {
        // Arrange
        when(adRepo.findById(anyInt())).thenReturn(Optional.of(accountDetails));
        when(adRepo.save(any(Account_Details.class))).thenReturn(accountDetails);

        // Act
        String result = accountService.update(accountDetails);

        // Assert
        assert(result.equals("updated"));
        verify(adRepo, times(1)).save(any(Account_Details.class));
    }

    @Test
    void testUpdate_ShouldReturnInvalidCustomerIdMessage() {
        // Arrange
        when(adRepo.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        String result = accountService.update(accountDetails);
        assert(result.equals("Invalid Customer Id"));
    }

    @Test
    void testUpdateDetails_ShouldReturnUpdatedAccount() {
        // Arrange
        when(adRepo.findById(anyInt())).thenReturn(Optional.of(accountDetails));
        when(adRepo.save(any(Account_Details.class))).thenReturn(accountDetails);

        // Act
        Account_Details updatedAccount = accountService.updateDetails(accountDetails.getCustomerId(), 1234567890L);

        // Assert
        assert(updatedAccount.getMobileNo() == 1234567890L);
    }

    @Test
    void testUpdateDetails_ShouldThrowExceptionWhenAccountNotFound() {
        // Arrange
        when(adRepo.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        try {
            accountService.updateDetails(accountDetails.getCustomerId(), 1234567890L);
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().contains("Invalid Customer Id"));
        }
    }
}
