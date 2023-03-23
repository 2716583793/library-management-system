package com.wang.dao;

import com.wang.bean.Type;

import java.util.List;

/**
 * 图书类别操作接口
 */
public interface TypeDAO {
    /**
     * 插入一个Type对象
     *
     * @param type 待插入的Type对象
     * @return 是否插入成功
     */
    boolean insertType(Type type);

    /**
     * 修改Type对象的信息
     *
     * @param newType 传入新对象替换原对象
     */
    void updateType(Type newType);

    /**
     * 删除指定id的Type对象
     *
     * @param typeId 指定id
     */
    void deleteTypeById(int typeId);

    /**
     * 查询指定id的Type对象
     *
     * @param typeId 指定id
     * @return 存在则返回对象，不存在则返回null
     */
    Type searchTypeById(int typeId);

    /**
     * 将所有图书类别存放在二维数组中
     *
     * @return 存放所有Type对象的二维数组
     */
    Object[][] searchAllTypeToArray();

    /**
     * 将所有图书类别存放在List集合中
     *
     * @return 存放所有Type对象的List集合
     */
    List<Type> searchAllTypeToList();

    /**
     * 将根据类别名模糊查询到的图书类别存放在List集合中
     *
     * @param typeName 输入的查找信息
     * @return 存放所有符合条件的Type对象的List集合
     */
    List<Type> fuzzySearchTypeByTypeName(String typeName);

    /**
     * 是否存在与参数同名对象
     *
     * @param typeName 类别名
     * @return 是否存在同名
     */
    boolean isExists(String typeName);
}
