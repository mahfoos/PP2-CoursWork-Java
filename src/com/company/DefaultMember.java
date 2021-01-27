package com.company;

public class DefaultMember implements Comparable<DefaultMember>{
    private String membershipNumber;
    private String name;
    private String startMembershipDate;

    public DefaultMember(String membershipNumber, String name, String startMembershipDate) {
        this.membershipNumber = membershipNumber;
        this.name = name;
        setStartMembershipDate(startMembershipDate);
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNo) {
        this.membershipNumber = membershipNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String membName) {
        this.name = membName;
    }

    public String getStartMembershipDate() {
        return startMembershipDate;
    }

    public void setStartMembershipDate(String date) {
        Date nnn = new Date();
        boolean test =nnn.matches(date);
        if(test == true) {
            this.startMembershipDate = date;
        }
    }

   @Override
    public int compareTo(DefaultMember o) {
        return this.name.compareTo(o.getName());
   }
}
