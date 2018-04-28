package com.example.server.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.common.Class;
import com.example.common.REST.SmallClass;
import com.example.common.User;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

/**
 * Created by vita on 4/3/18.
 */

public class ClassTable {
    public static final String TABLE_NAME = "Classes";

    public static final String COLUMN_CLASS_ID = "ClassID";
    public static final String COLUMN_OWNER_ID = "ownerID";
    public static final String COLUMN_CLASS_NAME = "className";
    public static final String COLUMN_CLASS_CODE = "classCode";

    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_OWNER_ID + " INTEGER NOT NULL, " +
            COLUMN_CLASS_NAME + " TEXT, " +
            COLUMN_CLASS_CODE + " TEXT);";

    //creates the class in the database, this does not create any of the sub-objects
    //the ID will be set in the passed class
    public static void createClass(Class cls) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement query = db.prepareStatement("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_CLASS_CODE + ", " +
                COLUMN_CLASS_NAME + ", " +
                COLUMN_OWNER_ID + ")" +
                " VALUES (?,?,?);"
        );

        query.setString(1, cls.getClassCode());
        query.setString(2, cls.getClassName());
        query.setInt(3, cls.getOwnerId());
        query.execute();

        int newId = query.getGeneratedKeys().getInt(1);
        cls.setClassID(newId);
    }

    public static void createClass(SmallClass cls) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement query = db.prepareStatement("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_CLASS_CODE + ", " +
                COLUMN_CLASS_NAME + ", " +
                COLUMN_OWNER_ID + ")" +
                " VALUES (?,?,?);"
        );

        query.setString(1, cls.getClassCode());
        query.setString(2, cls.getClassName());
        query.setInt(3, cls.getOwnerId());
        query.execute();

        int newId = query.getGeneratedKeys().getInt(1);
        cls.setClassID(newId);
    }

    public static SmallClass getClassById(int id) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();
        PreparedStatement query = db.prepareStatement("SELECT " +
                COLUMN_CLASS_CODE + ", " +
                COLUMN_CLASS_NAME + ", " +
                COLUMN_OWNER_ID + " " +
                " FROM " + TABLE_NAME + " " +
                " WHERE " + COLUMN_CLASS_ID + "=?;"
        );

        query.setInt(1, id);

        ResultSet rs = query.executeQuery();
        if (!rs.isAfterLast()) {
            SmallClass cls = new SmallClass();
            cls.setClassCode(rs.getString(1));
            cls.setClassName(rs.getString(2));
            cls.setOwnerId(rs.getInt(3));
            cls.setClassID(id);

            return cls;
        } else {
            return null;
        }
    }


    public static ArrayList<Class> getClassesForUserId(int userID) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();
        PreparedStatement query = db.prepareStatement("SELECT " +
            EnrollmentsTable.TABLE_NAME + "." + EnrollmentsTable.COLUMN_CLASS_ID + ", " +
            ClassTable.COLUMN_CLASS_CODE + ", " +
            ClassTable.COLUMN_CLASS_NAME + ", " +
            ClassTable.COLUMN_OWNER_ID +
            " FROM " + EnrollmentsTable.TABLE_NAME +
            " INNER JOIN " + ClassTable.TABLE_NAME +
            " ON " + ClassTable.TABLE_NAME + "." + ClassTable.COLUMN_CLASS_ID + "=" + EnrollmentsTable.TABLE_NAME + "." + EnrollmentsTable.COLUMN_CLASS_ID +
            " WHERE " + EnrollmentsTable.COLUMN_USER_ID + "=?;"

        );

        query.setInt(1, userID);
        ResultSet rs = query.executeQuery();
        ArrayList<Class> classes = new ArrayList<Class>();

        while (!rs.isAfterLast()) {
            Class cls = new Class();
            cls.setClassID(rs.getInt(1));
            cls.setClassCode(rs.getString(2));
            cls.setClassName(rs.getString(3));
            cls.setOwnerId(rs.getInt(4));
            classes.add(cls);
            rs.next();
        }

        if (classes.size() > 0) {
            return classes;
        } else {
            return null; //the user is not enrolled in any classes
        }
    }


}
