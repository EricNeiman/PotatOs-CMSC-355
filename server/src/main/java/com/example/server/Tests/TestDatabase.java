package com.example.server.Tests;

/**
 * Created by vita on 4/3/18.
 */
import com.example.common.Class;
import com.example.common.User;
import com.example.server.DatabaseHelper.ClassTable;
import com.example.server.DatabaseHelper.UserTable;


import java.sql.SQLException;

public class TestDatabase {
    /**
     * Example local unit test, which will execute on the development machine (host).
     *
     * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
     */
    public class ExampleUnitTest {
        @Test
        public void addition_isCorrect() {
            try {
                User user = new User(null, null, "Science Class", "email@example.com", "asdfopjasdfopjasdoji", 0);
                user = UserTable.createUser(user);
                Class cls = new Class();

                cls.setClassName("Test class");
                cls.setClassCode("U828d");
                cls.setOwnerId(user.getId());

                ClassTable.createClass(cls);
            } catch (SQLException ex) {
                assert(false, "SQL error on test data");
            }
        }
    }
}
