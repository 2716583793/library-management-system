package com.wang.dao.impl;

import com.wang.bean.Type;
import com.wang.dao.BaseDAO;
import com.wang.dao.TypeDAO;

import java.util.List;

/**
 * 图书类别操作实现
 */
public class TypeDAOImpl extends BaseDAO<Type> implements TypeDAO {
    @Override
    public boolean insertType(Type type) {
        String sql = "insert into `book_type`(`typeName`,`typeDesc`)values(?,?)";
        return update(sql, type.getTypeName(), type.getTypeDesc()) > 0;
    }

    @Override
    public void updateType(Type newType) {
        String sql = "update `book_type` set `typeName`=?,`typeDesc`=? where `typeId`=?";
        update(sql, newType.getTypeName(), newType.getTypeDesc(), newType.getTypeId());
    }

    @Override
    public void deleteTypeById(int typeId) {
        String sql = "delete from `book_type` where `typeId`=?";
        update(sql, typeId);
    }

    @Override
    public Type searchTypeById(int typeId) {
        String sql = "select * from `book_type` where `typeId`=?";
        return getBean(sql, typeId);
    }

    @Override
    public Object[][] searchAllTypeToArray() {
        String sql = "select * from `book_type`";
        List<Object[]> l = getArrayList(sql);
        Object[][] list = new Object[l.size()][3];
        int i = 0;
        for (Object[] o : l) {
            list[i++] = o;
        }
        return list;
    }

    @Override
    public List<Type> searchAllTypeToList() {
        String sql = "select * from `book_type`";
        return getBeanList(sql);
    }

    @Override
    public List<Type> fuzzySearchTypeByTypeName(String typeName) {
        String sql = "select * from `book_type` where `typeName` like ?;";
        return getBeanList(sql, "%" + typeName + "%");
    }

    @Override
    public boolean isExists(String typeName) {
        String sql = "select * from `book_type` where `typeName`=?";
        return getBean(sql, typeName) != null;
    }
}
