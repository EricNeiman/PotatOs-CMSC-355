package com.example.server.Tests;

/**
 * Created by vita on 4/3/18.
 */
import com.example.common.User;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDatabase {
    /**
     * Example local unit test, which will execute on the development machine (host).
     *
     * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
     */
    public class ExampleUnitTest {
        @Test
        public void addition_isCorrect() {
            User user = new User(null, null, "Science Class", "email@example.com", "asdfopjasdfopjasdoji", 0);
        }
    }
}
