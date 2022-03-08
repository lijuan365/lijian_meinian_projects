package com.lijian.human;

import com.alibaba.druid.support.json.JSONUtils;
import com.lijian.util.MD5Utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class human {

    private String name;
    private int age;
    private String Address;
    private float weight;

    public human() {
    }

    public human(String name, int age, String address, float weight) {
        this.name = name;
        this.age = age;
        Address = address;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public static void main(String[] args) {

        BCryptPasswordEncoder bpe  = new BCryptPasswordEncoder();
        String encode = bpe.encode("admin");
        System.out.println(encode);
        //$2a$10$.NLxWocdbWc/hhERsZ1NZuE64DFA1hV5HcqSKYR.pd1Uh4c128aAu
        //$2a$10$bxOjHpUe3kEfIiQuph1PKuEct80l2wMRzXvxpyE9L5I8WiFGH1nBW
        //$2a$10$08uDQT/XBKOIzcTuP6yzqecTsjld9PmKVuR1j1Enk8qjiZoaKCw/C

        //

    }

}
