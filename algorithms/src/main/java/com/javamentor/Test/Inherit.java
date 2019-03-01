package com.javamentor.Test;

class Parent {

    public void onlyParent() {
        System.out.println("Only parent method");
    }
    public void pMethod() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    public void pMethod() {
        System.out.println("Child parent method");
    }

    public void cMethod() {
        System.out.println("Only Child method");
    }
}

public class Inherit {

    public static void main(String[] args) {
        Parent p = new Parent();
//        p.pMethod();
//        p.onlyParent();

        Parent c = new Child();
        c.pMethod();
        c.onlyParent();
        ((Child) c).cMethod();

        Child cc = new Child();
//        cc.pMethod();
//        cc.cMethod();

        System.out.println(1.0 / 0.0); //Infinity
        System.out.println(-1.0 / 0.0); //-Infinity
        System.out.println(0.0 / 0.0); //NaN
    }
}
