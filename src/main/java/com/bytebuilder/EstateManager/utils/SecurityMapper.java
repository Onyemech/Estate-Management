package com.bytebuilder.EstateManager.utils;

import com.bytebuilder.EstateManager.data.models.Security;

public class SecurityMapper {
    public static Security mapToSecurity(Security security) {
        Security sec = new Security();
        sec.setFirstName(security.getFirstName());
        sec.setLastName(security.getLastName());
        sec.setPassword(security.getPassword());
        sec.setId(security.getId());
        return sec;
    }
}
