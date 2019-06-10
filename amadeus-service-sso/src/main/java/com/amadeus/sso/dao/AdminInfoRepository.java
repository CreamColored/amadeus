package com.amadeus.sso.dao;

import com.amadeus.framework.domain.admin.AdminInfo;

import java.util.Optional;

public interface AdminInfoRepository extends CrudRepository<AdminInfo,String>{

    Optional<AdminInfo> findByTelephoneNumber(String telephoneNumber);

}
