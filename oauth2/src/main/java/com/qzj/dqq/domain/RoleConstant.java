package com.qzj.dqq.domain;

/**
 * Created by qzj on 2018/2/5
 */
public enum RoleConstant {

    ADMIN("ROLE_ADMIN", 1), GREEN("ROLE_USER", 2);

    private String name ;
    private int index ;

     RoleConstant(String name , int index ){
        this.name = name ;
        this.index = index ;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }


}
