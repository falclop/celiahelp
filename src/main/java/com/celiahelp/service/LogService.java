// LogService.java
package com.celiahelp.service;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Log;

import java.util.List;

public interface LogService {
    List<Log> getAll();
    Log getById(Long id) throws NotFoundException;
    Log create(Log log) throws ServiceException;
    Log update(Long id, Log log) throws ServiceException, NotFoundException;
    void delete(Long id) throws ServiceException, NotFoundException;
}
