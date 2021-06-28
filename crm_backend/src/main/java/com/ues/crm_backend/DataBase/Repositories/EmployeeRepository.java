package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.IEmployeeRepository;
import com.ues.crm_backend.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeRepository {
    @Autowired
    IEmployeeRepository iEmployeeRepository;

    public List<Object[]> getAllManagers() {
        return iEmployeeRepository.findManagers("MANAGER");
    }

    public Map<Object, Object> getNewContactCount() {
        List<Object[]> managers = getAllManagers();
        Map<Integer, BigInteger> day = getMap(iEmployeeRepository.getContactCountInPeriod(0), managers);
        Map<Integer, BigInteger>  week = getMap(iEmployeeRepository.getContactCountInPeriod(7), managers);

        Map<Integer, BigInteger> month = getMap(iEmployeeRepository.getContactCountInPeriod(30), managers);
        Map<Integer, BigInteger> year = getMap(iEmployeeRepository.getContactCountInPeriod(365), managers);
        Map<Integer, BigInteger> all = getMap(iEmployeeRepository.getContactCountInPeriod(36500), managers);
        Map<Object, Object> response = new HashMap<>();
        response.put("label", "Контактные лица");
        response.put("day", day.values());
        response.put("week", week.values());
        response.put("month", month.values());
        response.put("year", year.values());
        response.put("all", all.values());
        response.put("employeeIds", managers);
        return response;
    }

    private Map<Integer, BigInteger> getMap(List<Object[]> periodData, List<Object[]> managers) {
        Map<Integer, BigInteger> contactMap = new HashMap<>();
        for (Object[] manager :managers) {
            for(Object[] contactCount: periodData) {
                if (contactCount[0] == manager[0]) {
                    contactMap.put((Integer) manager[0], (BigInteger) contactCount[1]);
                    continue;
                }
            }
            if(!contactMap.containsKey((Integer) manager[0]))
                contactMap.put((Integer) manager[0], BigInteger.ZERO);
        }
        return contactMap;
    }

    public Map<Object, Object> getNewCompanyCount() {
        List<Object[]> managers = getAllManagers();
        Map<Integer, BigInteger> day = getMap(iEmployeeRepository.getCompanyCountInPeriod(0), managers);
        Map<Integer, BigInteger>  week = getMap(iEmployeeRepository.getCompanyCountInPeriod(7), managers);

        Map<Integer, BigInteger> month = getMap(iEmployeeRepository.getCompanyCountInPeriod(30), managers);
        Map<Integer, BigInteger> year = getMap(iEmployeeRepository.getCompanyCountInPeriod(365), managers);
        Map<Integer, BigInteger> all = getMap(iEmployeeRepository.getCompanyCountInPeriod(36500), managers);

        Map<Object, Object> response = new HashMap<>();
        response.put("label", "Компании");
        response.put("day", day.values());
        response.put("week", week.values());
        response.put("month", month.values());
        response.put("year", year.values());
        response.put("all", all.values());
        response.put("employeeIds", managers);
        return response;
    }
}
