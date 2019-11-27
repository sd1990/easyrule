package org.songdan.easy.rules.model;

/**
 * @author: Songdan
 * @create: 2019-11-25 19:42
 **/
public class NumObj {

    private Integer num;

    public NumObj(Integer num) {
        this.num = num;
    }

    public Integer generate() {
        System.out.println("numobj generate working!!!");
        return num;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
