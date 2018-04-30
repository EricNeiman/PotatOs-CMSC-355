package com.example.server.DatabaseHelper;

import com.example.common.Class;
import com.example.common.REST.SmallClass;
import com.example.common.REST.SmallUser;
import com.example.common.User;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UserTableTest {
    @BeforeEach
    void setUp() throws SQLException {
        PotatOsDatabase.deleteTables();
        PotatOsDatabase.createTables();
    }

    @AfterEach
    void tearDown() {
        PotatOsDatabase.deleteTables();  //don't delete so you can inspect the database
    }

    @Test
    void createUser() throws SQLException {
        User user = new User(
                false,
                null,
                null,
                "Jacob Draddy",
                "email@example.com",
                "asdfopjasdfopjasdoji",
                0);
        UserTable.createUser(user);

        Class cls = new Class();
        cls.setOwnerId(user.getId());
        cls.setClassName("Science Class");
        cls.setClassCode("U828d");

        ClassTable.createClass(cls);

        EnrollmentsTable.enrollUserInClass(user, cls);

        Assert.assertEquals("Science Class", cls.getClassName());

        //if the database is clean, these should be set on creation, and 1
        Assert.assertEquals(1, cls.getClassID());
        Assert.assertEquals(1, user.getId());

        SmallClass dbCls = ClassTable.getClassById(cls.getClassID());
        Assert.assertNotEquals(null, dbCls);
        Assert.assertEquals(cls.getClassID(), dbCls.getClassID());
        Assert.assertEquals(cls.getClassName(), dbCls.getClassName());
        Assert.assertEquals(cls.getClassCode(), dbCls.getClassCode());

        User dbUsr = UserTable.getUserById(user.getId());
        Assert.assertNotEquals(null, dbUsr.getClassesIn());
    }
}