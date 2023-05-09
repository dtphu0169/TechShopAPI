package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.AddressDto;
import com.tech.TechShopAPI.model.Address;
import org.apache.tomcat.websocket.AuthenticationException;

import java.security.Principal;

public interface AddressService {
    AddressDto save(Principal principal, AddressDto addressDto);

    void delete(Principal principal, int id) throws AuthenticationException;

    AddressDto edit(Principal principal, AddressDto id) throws AuthenticationException;
}
