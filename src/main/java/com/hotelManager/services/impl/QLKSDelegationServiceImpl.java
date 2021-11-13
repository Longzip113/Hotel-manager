package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.DelegationRequest;
import com.hotelManager.dtos.responses.QLKSDelegationResponse;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.entities.QLKSDelegationEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSDelegationModel;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.repositories.QLKSCustomerRepository;
import com.hotelManager.repositories.QLKSDelegationRepository;
import com.hotelManager.repositories.QLKSEmployeeRepository;
import com.hotelManager.services.QLKSDelegationService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSDelegationServiceImpl implements QLKSDelegationService {

    @Autowired
    QLKSDelegationRepository qlksDelegationRepository;

    @Autowired
    QLKSCustomerRepository customerRepository;

    @Autowired
    QLKSEmployeeRepository qlksEmployeeRepository;


    @Override
    public List<QLKSDelegationResponse> getAll() throws HotelManagerException {

        List<QLKSDelegationModel> delegations = qlksDelegationRepository.getAll();
        List<QLKSDelegationResponse> delegationResponses = new ArrayList<>();

        delegations.stream().forEach(item -> {

            QLKSDelegationResponse delegationResponse = QLKSDelegationResponse.builder()
                    .nameDelegations(item.getNameDelegations())
                    .id(item.getId())
                    .nameManager(item.getNameManager())
                    .nameCompany(item.getNameCompany())
                    .numberOfPeople(item.getNumberOfPeople()).build();

            if (StringUtils.isNotBlank(item.getIdCustomer())) {
                List<String> listIdCustomer = List.of(item.getIdCustomer().split("/"));
                delegationResponse.setCustomers(customerRepository.getByIds(listIdCustomer));
            }

            delegationResponses.add(delegationResponse);
        });
        return delegationResponses;
    }

    @Override
    public void add(DelegationRequest addDelegationRequest) throws HotelManagerException {

        Optional<QLKSDelegationEntity> qlksRoleEntity = qlksDelegationRepository.getByName(addDelegationRequest.getNameDelegations(), "");

        if (qlksRoleEntity.isPresent()) {

            log.error("Name delegation existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_DELEGATION_ALREADY_EXISTED);
        }

        Optional<QLKSEmployeeModel> manager =  qlksEmployeeRepository.getById(addDelegationRequest.getIdTeamManager());
        if (manager.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        QLKSDelegationEntity entity = QLKSDelegationEntity.builder()
                .nameDelegations(addDelegationRequest.getNameDelegations())
                .idTeamManager(addDelegationRequest.getIdTeamManager())
                .nameCompany(addDelegationRequest.getNameCompany())
                .isDelete(Boolean.FALSE)
                .build();

        qlksDelegationRepository.save(entity);
    }

    @Override
    public void delete(String id) throws HotelManagerException {
        Optional<QLKSDelegationModel> entity = qlksDelegationRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        qlksDelegationRepository.delete(id);
    }

    @Override
    public void update(String id, DelegationRequest updateDelegationRequest) throws HotelManagerException {
        Optional<QLKSDelegationModel> entity = qlksDelegationRepository.getById(id);
        Optional<QLKSCustomerEntity> manager =  customerRepository.getById(updateDelegationRequest.getIdTeamManager());

        if (entity.isEmpty() || manager.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        Optional<QLKSDelegationEntity> qlksRoleEntity = qlksDelegationRepository.getByName(updateDelegationRequest.getNameDelegations()
                , id);

        if (qlksRoleEntity.isPresent()) {

            log.error("Name delegation existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_DELEGATION_ALREADY_EXISTED);
        }

        qlksDelegationRepository.update(id, updateDelegationRequest);
    }

    @Override
    public QLKSDelegationResponse getDetail(String id) throws HotelManagerException {
        Optional<QLKSDelegationModel> entity = qlksDelegationRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        QLKSDelegationResponse delegationResponse = QLKSDelegationResponse.builder()
                .nameDelegations(entity.get().getNameDelegations())
                .id(entity.get().getId())
                .nameManager(entity.get().getNameManager())
                .nameCompany(entity.get().getNameCompany())
                .numberOfPeople(entity.get().getNumberOfPeople()).build();

        if (StringUtils.isNotBlank(entity.get().getIdCustomer())) {
            List<String> listIdCustomer = List.of(entity.get().getIdCustomer().split("/"));
            delegationResponse.setCustomers(customerRepository.getByIds(listIdCustomer));
        }

        return delegationResponse;
    }
}
