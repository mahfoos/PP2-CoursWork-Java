package com.company;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyGymManager implements GymManager {

    public static List<DefaultMember> lisOfMember = new ArrayList<DefaultMember>();

    @Override
    public void addNewMember(DefaultMember defMember)  {
        if (lisOfMember.size() < 100) {
            lisOfMember.add(defMember);
            System.out.println("\n\t\t\tNo of Registered Member: " + lisOfMember.size());
            System.out.println("\t\t\tNumber of Space Available: " + (100 - lisOfMember.size()));
        } else {
            System.out.println(" No space Available");
        }

    }


    @Override
    public boolean delMember(String membershipNo) {
        boolean bool = false;
        for (DefaultMember defMember : lisOfMember) {
            if (defMember.getMembershipNumber().equals(membershipNo)) {
                bool = true;
                lisOfMember.remove(defMember);
                System.out.println("Member with the membership no " + membershipNo + " Successfully removed");
                System.out.println("No of Registered Member " + lisOfMember.size());
                System.out.println("No of Free Space  " + (100 - lisOfMember.size()));
                if (defMember instanceof StudentMember) {
                    System.out.println("Member type is : Student Member");
                } else if (defMember instanceof Over60Member) {
                    System.out.println("Member type is : Over60Member");
                } else {
                    System.out.println("Member type is : DefaultMember");
                }
                break;
            }

        }
        if (!bool) {
            System.out.println("Not found");

        }
        return bool;
    }

    @Override
    public void printMemberDetails() {
        for (DefaultMember defMember : lisOfMember) {
            System.out.print("\t\t\tMembership Number: " + defMember.getMembershipNumber() + " ");
            if (defMember instanceof StudentMember) {
                System.out.print("\tMember type is : StudentMember");
            } else if (defMember instanceof Over60Member) {
                System.out.print("\tMember type is : Over60Member");
            } else {
                System.out.print("\tMember type is : DefaultMember");
            }
            System.out.print("\tName is : " + defMember.getName() + " ");
            System.out.println("\tMembership start is : " + defMember.getStartMembershipDate());
        }
    }

    @Override
    public void sort() {
        Collections.sort(lisOfMember);
        System.out.println("\t\t\tSuccessfully Sorted");

    }

    @Override
    public void saveDetails() {
        File file = new File("MemberDetails.txt");
        try {

            FileWriter out = new FileWriter(file, true);
            for (DefaultMember defMember : lisOfMember) {
                out.write("MembershipNumber: " + defMember.getMembershipNumber() + " ");
                if (defMember instanceof StudentMember) {
                    out.write("\tMember type is : StudentMember");
                } else if (defMember instanceof Over60Member) {
                    out.write("\tMember type is : Over60Member");
                } else {
                    out.write("\tMember type is : DefaultMember");
                }
                out.write("\tName is : " + defMember.getName());
                out.write("\tMembership start is : " + defMember.getStartMembershipDate() + "\n");
            }
            saveJson();
            System.out.println("\t\t\tMember Details saved to file Successfully");
            out.close();
        } catch (Exception e) {
            System.out.println("\t\t\tNo Data Saved");
        }

    }

    public void saveJson(){
        File file = new File("MemberDetails.json");
        try {
            FileWriter out = new FileWriter(file);
            JSONArray jsonArray = new JSONArray();
            for (DefaultMember defMember : lisOfMember) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("MembershipNumber" ,defMember.getMembershipNumber());
                if (defMember instanceof StudentMember) {
                    jsonObject.put("Member_type_is","StudentMember");
                    jsonObject.put("School_Name",((StudentMember) defMember).getSchoolName());
                } else if (defMember instanceof Over60Member) {
                    jsonObject.put("Member_type_is","Over60Member");
                    jsonObject.put("Member_Age",((Over60Member) defMember).getAge());
                } else {
                    jsonObject.put("Member_type_is","DefaultMember");
                }
                jsonObject.put("Name_is",defMember.getName());
                jsonObject.put("Membership_start_is" , defMember.getStartMembershipDate());
                jsonArray.put(jsonObject);
            }
            out.write(String.valueOf(jsonArray));
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadJson() throws Exception{
        try {
            InputStream inputStr = new FileInputStream("MemberDetails.json");
            JSONTokener tokenerJson = new JSONTokener(inputStr);
            JSONArray arrayJson = new JSONArray(tokenerJson);

            for (int i =0; i <arrayJson.length();i++){
                JSONObject jsonObject = arrayJson.getJSONObject(i);
                String str = jsonObject.getString("MembershipNumber");
                String strName = jsonObject.getString("Name_is");
                String type = jsonObject.getString("Member_type_is");
                String strDate = jsonObject.getString("Membership_start_is");
                switch (type){
                    case "DefaultMember":
                        lisOfMember.add(new DefaultMember(str,strName,strDate));
                        break;
                    case "StudentMember":
                        String school = jsonObject.getString("School_Name");
                        lisOfMember.add(new StudentMember(str,strName,strDate,school));
                        break;
                    case "Over60Member":
                        int over60 = jsonObject.getInt("Member_Age");
                        lisOfMember.add(new Over60Member(str,strName,strDate,over60));
                        break;

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
