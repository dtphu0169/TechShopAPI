package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.dto.AccountDto;
import com.tech.TechShopAPI.dto.AddressDto;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Address;
import com.tech.TechShopAPI.service.AccountService;
import com.tech.TechShopAPI.service.AddressService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    AddressService addressService;

    // test api role
//    @PreAuthorize("hasRole('USER')") // các đường dẫn chưa đề cập trong sẽ được authenticated nên không cần role user
    @GetMapping("/user")
    public String user(Principal principal) {
        return "Hello, "+ principal.getName();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(Authentication authentication) {
        Collection<? extends GrantedAuthority> au = authentication.getAuthorities();
        GrantedAuthority a = au.stream().iterator().next();
        return "Hello, Admin!" ;
    }


    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Account>> getAll(Principal principal){
        try{
            List<Account> list = accountService.getAllAccount();
            if (list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
            }
            return new ResponseEntity<List<Account>>(list, HttpStatus.OK) ;
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<String> getemail(@PathVariable int id){
        try{
            String email = accountService.getEmailbyId(id);
            if (email == null || email.equals("")){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
            }
            return new ResponseEntity<String>(email, HttpStatus.OK) ;
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @GetMapping()
    public ResponseEntity<AccountDto> getInfo(Principal principal){
        return new ResponseEntity<AccountDto>(accountService.getInfo(principal),HttpStatus.OK) ;
    }

    @PutMapping("/changepassword")
    public ResponseEntity<?> changepassword(Principal principal,@RequestBody String password ){
        accountService.changepassword(principal,password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/address")
    public ResponseEntity<?> addAddress(Principal principal,@RequestBody AddressDto addressDto){
        Address address = addressService.save(principal,addressDto);
        return new ResponseEntity<Address>(address,HttpStatus.OK);
    }
    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(Principal principal,@PathVariable int id)  {
        try{
            addressService.delete(principal,id);
        }catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/address")
    public ResponseEntity<?> editAddress(Principal principal,@RequestBody AddressDto addressDto){
        try{
            Address address = addressService.edit(principal,addressDto);
            return new ResponseEntity<Address>(address,HttpStatus.OK);
        }catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

//
//    @GetMapping("/getAll")
//    List<Account> getAccounts(){
//        List<Account> list = new ArrayList<>();
//        Account ac1 = new Account();
//        ac1.setId(1);
//        ac1.setUserName("ac1");
//        ac1.setEmail("ac223@gmail.com");
//        ac1.setPassword("1234");
//        ac1.setRole(true);
//        list.add(ac1);
//
//        Account ac2 = new Account();
//        ac2.setId(2);
//        ac2.setUserName("ac2");
//        ac2.setEmail("acxx@gmail.com");
//        ac2.setPassword("1235");
//        ac2.setRole(false);
//        list.add(ac2);
//
//        list.addAll(accounts);
//        return list;
//
//    }
//
//    @PostMapping("/insert")
//    Account insertAccount(@RequestBody Account account){
//        accounts.add(account);
//        return account;
//    }


}
