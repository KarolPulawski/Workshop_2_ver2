package com.coderslab.admin;

import com.coderslab.dao.daoimplements.GroupDAO;
import com.coderslab.dao.daointerfaces.GroupDAOInterface;
import com.coderslab.model.Group;

import java.util.ArrayList;
import java.util.Scanner;

public class GroupAdministration {

    private static Scanner scanner = new Scanner(System.in);

    private static String getStringFromUser(String s) {
        System.out.println(s);
        return scanner.nextLine();
    }

    private static int getIntFromUser(String s) {
        System.out.println(s);
        Integer number = null;
        while(number == null || number < 1) {
            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number and greater than 0.");
            }
        }
        return number;
    }

    protected static void displayAllGroup() {
        System.out.println("------------------------------Group list-----------------------------------------");
        System.out.printf("%-5s %-40s \n", "id", "name");
        System.out.println("--------------------------------------------------------------------------------");
        ArrayList<Group> groups = GroupDAOInterface.loadAll();
        for(Group group : groups) {
            int group_id = group.getId();
            String group_name = group.getName();
            System.out.printf("%-5d %-40s \n", group_id, group_name);
        }
    }

    public static void processAction() {
        boolean action = true;
        while (action) {
            displayAllGroup();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" * add");
            System.out.println(" * edit");
            System.out.println(" * delete");
            System.out.println(" * quit");
            System.out.println("--------------------------------------------------------------------------------------");
            String choice = getStringFromUser("Please type one out of above actions: ");
            switch (choice) {
                case "add":
                    Group groupAdd = new Group();
                    groupAdd.setName(getStringFromUser("Please type name of group: "));

                    GroupDAO groupDAOAdd = new GroupDAO();
                    groupDAOAdd.saveToDB(groupAdd);

                    break;
                case "edit":
                    Group groupEdit = GroupDAOInterface.loadById(getIntFromUser("Please type group's id to edit: "));
                    if(groupEdit == null) {
                        System.out.println("Group id does not exist and can not be modified!");
                        break;
                    }
                    groupEdit.setName(getStringFromUser("Please type name of group"));
                    GroupDAO groupDAOEdit = new GroupDAO();
                    groupDAOEdit.saveToDB(groupEdit);

                    break;
                case "delete":
                    Group groupDelete = GroupDAOInterface.loadById(getIntFromUser("Please type group's id to delete: "));
                    if(groupDelete == null) {
                        System.out.println("Group id does not exist and can not be deleted!");
                        break;
                    }

                    GroupDAO groupDAODelete = new GroupDAO();
                    groupDAODelete.delete(groupDelete);

                    break;
                case "quit":
                    System.out.println("quit");
                    action = false;
                    break;
                default:
                    System.out.println("Please enter valid action!");
            }
        }
    }
}
