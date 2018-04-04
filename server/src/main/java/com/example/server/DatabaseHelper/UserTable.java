package com.example.server.DatabaseHelper;

import com.example.common.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vita on 4/3/18.
 */

public class UserTable {
    public static final String TABLE_NAME = "Users";
    public static final String COLUMN_USER_ID = "UserID";
    public static final String COLUMN_PASSWORD_HASH = "passwordHash";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_PASSWORD_HASH + " TEXT, " +
            COLUMN_EMAIL + " TEXT, " +
            COLUMN_NAME + " TEXT " +
            ");";

    public static User getUserById(int id) {
        try {
            User user = new User();
            Connection db = PotatOsDatabase.getDbConnection();

            PreparedStatement query = db.prepareStatement("SELECT  " +
                    COLUMN_PASSWORD_HASH + ", " +
                    COLUMN_EMAIL + ", " +
                    COLUMN_NAME + ", " +
                    " FROM "+ TABLE_NAME + " WHERE " + COLUMN_USER_ID + "=?;"
            );

            query.setInt(1, id);

            ResultSet cursor = query.executeQuery();

            user.setPasswordHash(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setName(cursor.getString(3));

            return user;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static User getUserByEmailPass(String email, String passwordHash) {
        try {
            User user = new User();
            Connection db = PotatOsDatabase.getDbConnection();

            PreparedStatement query = db.prepareStatement("SELECT  " +
                    COLUMN_PASSWORD_HASH + ", " +
                    COLUMN_EMAIL + ", " +
                    COLUMN_NAME + ", " +
                    " FROM "+ TABLE_NAME +
                    " WHERE " +
                    COLUMN_PASSWORD_HASH + "=? " +
                    " AND " + COLUMN_EMAIL + "=? ;"
            );

            query.setString(1, passwordHash);
            query.setString(2, email);

            ResultSet cursor = query.executeQuery();

            if (cursor.first()) {
                user.setPasswordHash(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setName(cursor.getString(3));
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public static boolean updateUser(User newUserInfo) {
        try {
            Connection db = PotatOsDatabase.getDbConnection();

            User user = new User();

            PreparedStatement query = db.prepareStatement("UPDATE " + TABLE_NAME + " SET " +
                    COLUMN_PASSWORD_HASH + "=?, " +
                    COLUMN_EMAIL + "=?, " +
                    COLUMN_NAME + "=?, " +
                    " WHERE " + COLUMN_USER_ID + "=?;"
            );

            query.setString(1, user.getPasswordHash());
            query.setString(2, user.getEmail());
            query.setString(3, user.getName());
            query.setInt(4, user.getId());

            query.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    //the ID will be set in the passed class
    public static void createUser(User user) throws SQLException {
        //TODO: implement
        Connection db = PotatOsDatabase.getDbConnection();
        PreparedStatement query = db.prepareStatement("INSERT INTO " + TABLE_NAME + " (" +
            UserTable.COLUMN_EMAIL + ", " +
            UserTable.COLUMN_NAME + ", " +
            UserTable.COLUMN_PASSWORD_HASH + ") VALUES (?,?,?);");

        query.setString(1, user.getEmail());
        query.setString(2, user.getName());
        query.setString(3, user.getPasswordHash());

        query.execute();

        user.setId(query.getGeneratedKeys().getInt(1));
        System.out.println("User created ... " + user.getName());
    }
}
