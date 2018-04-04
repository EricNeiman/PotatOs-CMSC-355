package com.example.server.DatabaseHelper;

/**
 * Created by vita on 4/3/18.
 */
import com.example.common.Class;
import com.example.common.User;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
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
                User user = new User(null, null, "Jacob Draddy", "email@example.com", "asdfopjasdfopjasdoji", 0);
                UserTable.createUser(user);

                Class cls = new Class();
                cls.setOwnerId(user.getId());
                cls.setClassName("Test class");
                cls.setClassCode("U828d");

                ClassTable.createClass(cls);

                ClassTable.enrollUserInClass(user, cls);

                Assert.assertEquals(cls.getClassName(), "Science Class");

                Class dbCls = ClassTable.getClassById(cls.getClassID());
                Assert.assertEquals(dbCls.getClassID() , cls.getClassID());
                Assert.assertEquals(dbCls.getClassName(), cls.getClassName());
                Assert.assertEquals(dbCls.getClassCode(), cls.getClassCode());
            } catch (SQLException ex) {
                Assert.fail();
            }
        }
    }
}
