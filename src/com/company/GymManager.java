package com.company;


import java.io.IOException;

public interface GymManager {
    void addNewMember(DefaultMember member);
    boolean delMember(String membershipNo);
    void printMemberDetails();
    void sort();
    void saveDetails() throws IOException;
}
