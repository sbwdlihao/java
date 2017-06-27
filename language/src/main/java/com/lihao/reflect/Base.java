package com.lihao.reflect;

import java.io.Serializable;

class Base implements Serializable{

    private static final long serialVersionUID = -1444172336256112978L;

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
