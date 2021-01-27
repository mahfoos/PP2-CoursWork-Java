package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MemberDetailsGui extends Application {

    @Override public void start(Stage stage) {

        Label lbl = new Label("Members Details"); // Label For Table Name
        TableView <DefaultMember> tableMember = new TableView<DefaultMember>();
        final ObservableList<DefaultMember> observer = FXCollections.observableArrayList(MyGymManager.lisOfMember);

        TableColumn memberId = new TableColumn("Membership Number"); // Creating Membership Number Column
        memberId.setCellValueFactory(new PropertyValueFactory("membershipNumber"));

        TableColumn memberName = new TableColumn("Member Name"); // Creating Member Name Column
        memberName.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn memberType = new TableColumn("Member Enrolled Date"); // Creating Member Enrolled Date Column
        memberType.setCellValueFactory(new PropertyValueFactory("startMembershipDate"));

        TableColumn memberSchool = new TableColumn("School Name"); // Creating School Name Column
        memberSchool.setCellValueFactory(new PropertyValueFactory("schoolName"));

        TableColumn memberAge = new TableColumn("Member Age"); // Creating Member Age Column
        memberAge.setCellValueFactory(new PropertyValueFactory("age"));

        tableMember.setItems(observer);
        tableMember.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableMember.getColumns().addAll(memberId,memberName,memberType,memberSchool,memberAge);

        tableMember.setMaxSize(600,400);
        VBox tblBox = new VBox();
        tblBox.setSpacing(5);
        tblBox.setPadding(new Insets(10,50,50,50));
        tblBox.getChildren().addAll(lbl,tableMember);

        Scene tblScene = new Scene(tblBox,700,500);
        stage.setTitle("Members Details");
        stage.setScene(tblScene);
        stage.sizeToScene();
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
