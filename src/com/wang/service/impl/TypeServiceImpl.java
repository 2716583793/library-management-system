package com.wang.service.impl;

import com.wang.bean.Type;
import com.wang.dao.TypeDAO;
import com.wang.dao.impl.TypeDAOImpl;
import com.wang.service.TypeService;

import java.util.List;

/**
 * 图书类别操作实现
 */
public class TypeServiceImpl implements TypeService {

    private final TypeDAO typeDAO = new TypeDAOImpl();

    @Override
    public boolean addType(Type type) {
        if (typeDAO.isExists(type.getTypeName())) {
            return false;
        }
        return typeDAO.insertType(type);
    }

    @Override
    public void changeTypeById(Type newType) {
        typeDAO.updateType(newType);
    }

    @Override
    public void dropType(int typeId) {
        typeDAO.deleteTypeById(typeId);
    }

    @Override
    public Type getTypeById(int typeId) {
        return typeDAO.searchTypeById(typeId);
    }

    @Override
    public Object[][] getAllTypeToArray() {
        return typeDAO.searchAllTypeToArray();
    }

    @Override
    public List<Type> getAllTypeToList() {
        return typeDAO.searchAllTypeToList();
    }

    @Override
    public List<Type> getTypeListByTypeName(String typeName) {
        return typeDAO.fuzzySearchTypeByTypeName(typeName);
    }

    @Override
    public boolean isExistsType(String typeName) {
        return typeDAO.isExists(typeName);
    }
}
