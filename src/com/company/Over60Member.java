package com.company;

public class Over60Member extends DefaultMember {
    private int age;

    public Over60Member(String membershipNumber, String name, String startMembershipDate, int age) {
        super(membershipNumber, name, startMembershipDate);
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 60) {
            this.age = age;
        }else {
            System.out.println("Invalid Age for Over 60 Member");
        }
    }
}