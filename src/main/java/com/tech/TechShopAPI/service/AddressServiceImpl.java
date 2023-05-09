package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.AddressDto;
import com.tech.TechShopAPI.dto.Dtomapper;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Address;
import com.tech.TechShopAPI.repository.AccountRepository;
import com.tech.TechShopAPI.repository.AddressRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public AddressDto save(Principal principal, AddressDto addressDto) {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        Address address = new Address();
        address.setAccount(account);
        address.setProvine_city(addressDto.getProvine_city());
        address.setDictrict(addressDto.getDictrict());
        address.setWard(addressDto.getWard());
        address.setDetail(addressDto.getDetail());

        AddressDto dto = Dtomapper.mapAddress(addressRepository.save(address));
        return dto;
    }

    @Override
    public void delete(Principal principal, int id) throws AuthenticationException {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        Address address = addressRepository.findById(id).get();
        if (account.getId() != address.getAccount().getId()){
            throw  new AuthenticationException("account id not match");
        }
        addressRepository.delete(address);
    }

    @Override
    public AddressDto edit(Principal principal, AddressDto addressDto) throws AuthenticationException {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        Address address = addressRepository.findById(addressDto.getId()).get();
        if (account.getId() != address.getAccount().getId()){
            throw new AuthenticationException("account id not match");
        }

        address.setProvine_city(addressDto.getProvine_city());
        address.setDictrict(addressDto.getDictrict());
        address.setWard(addressDto.getWard());
        address.setDetail(addressDto.getDetail());

        AddressDto dto = Dtomapper.mapAddress(addressRepository.save(address));
        return dto;
    }
}
