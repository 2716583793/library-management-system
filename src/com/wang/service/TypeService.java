package com.wang.service;

import com.wang.bean.Type;

import java.util.List;

/**
 * 图书类别操作接口
 */
public interface TypeService {
    /**
     * 添加图书类别
     *
     * @param type 待添加图书类别信息
     * @return 是否插入成功
     */
    boolean addType(Type type);

    /**
     * 修改Type对象的信息
     *
     * @param newType 传入新图书类别信息替换原信息
     */
    void changeTypeById(Type newType);

    /**
     * 删除图书类别事务
     *
     * @param typeId 待删除图书类别id
     */
    void dropType(int typeId);

    /**
     * 得到指定id图书类别信息
     *
     * @param typeId 指定id
     * @return 指定id图书类别信息
     */
    Type getTypeById(int typeId);

    /**
     * 将所有图书类别存放在二维数组中
     *
     * @return 存放所有Type对象的二维数组
     */
    Object[][] getAllTypeToArray();

    /**
     * 将所有图书类别存放在List集合中
     *
     * @return 存放所有Type对象的List集合
     */
    List<Type> getAllTypeToList();

    /**
     * 将根据类别名模糊查询到的图书类别存放在List集合中
     *
     * @param typeName 输入的查找信息
     * @return 存放所有符合条件的Type对象的List集合
     */
    List<Type> getTypeListByTypeName(String typeName);

    /**
     * 是否存在与参数同名类别
     *
     * @param typeName 图书类别名
     * @return 是否存在同名类别
     */
    boolean isExistsType(String typeName);
}
