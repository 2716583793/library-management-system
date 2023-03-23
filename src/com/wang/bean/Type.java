package com.wang.bean;

/**
 * 图书类别实体类
 */
public class Type {
    private Integer typeId; //图书类别id
    private String typeName; //图书类别名称
    private String typeDesc; //图书类别描述

    public Type() {
    }

    public Type(Integer typeId, String typeName, String typeDesc) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDesc = typeDesc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
}
