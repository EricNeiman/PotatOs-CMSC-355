/*  CMSC 355 Common Class Files Test
    This file will test the functionality of all of the common files for the Android Quiz App
    Class Files: User.java, Class.java, Quiz.java, Question.java, QuestionImage.java, and Answer.java
 */

package com.example.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date; // for close time

public class CommonTest {
    @BeforeEach
    void setUp()  {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCommon() {
        System.out.println("\nTesting Common Files:\n");

        System.out.println("Creating a Teacher: ");
        User t = new User(true, null, null, "teacher", "teacher.email", "password", 01);
        System.out.println("UserName: " + t.getName() + " Email: " + t.getEmail() + " Password: " + t.getPasswordHash() + " UserId: " + t.getId());
        System.out.println("Changing the username, id, email, and password."); // testing all of the basic setters
        t.setName("teacherName");
        t.setEmail("teacherEmail@email.com");
        t.setPasswordHash("passw0rd");
        t.setId(02);
        System.out.println("UserName: " + t.getName() + " Email: " + t.getEmail() + " Password: " + t.getPasswordHash() + " UserId: " + t.getId());


        System.out.println("\nCreating a blank student user.");
        User s = new User(); // creates the student user
        System.out.println("UserName: " + s.getName() + " Email: " + s.getEmail() + " Password: " + s.getPasswordHash() + " UserId: " + s.getId());
        s.setName("studentName");
        s.setEmail("studentEmail@email.com");
        s.setPasswordHash("p@ssw0rd");
        s.setId(99);
        System.out.println("UserName: " + s.getName() + " Email: " + s.getEmail() + " Password: " + s.getPasswordHash() + " UserId: " + s.getId());


        System.out.println("\nCreating a class.");
        Class c = new Class();
        System.out.println("OwnerId: " + c.getOwnerId() + " ClassName: " + c.getClassName() + " ClassCode: " + c.getClassCode() + " ClassID: " + c.getClassID());
        System.out.println("Adding the student to the class and the class to the teacher's classes.");
        c.setOwnerId(t.getId());
        c.setClassName("className");
        c.setClassCode("cl@ssC0de");
        c.appendUsers(s); // adds student to class' users
        t.appendClassesOwned(c); // adds class to teacher's classes
        s.appendClassesIn(c); // adds class to student's classes
        System.out.println("Printing the teachers class information:\nClassName: " + t.getClassesOwned().get(0).getClassName() + " ClassCode: " +
                t.getClassesOwned().get(0).getClassCode() + " ClassID: " + t.getClassesOwned().get(0).getClassID() + " Users: ");
        for (int i = 0; i < t.getClassesOwned().get(0).getUsers().size(); i++) {
            System.out.print("User" + i + ": " + t.getClassesOwned().get(0).getUsers().get(i).getName() + " ");
        }


        System.out.println("\n\nAdding 3 more students to the teacher's class. \nPrinting students in the class.");
        User s1 = new User(false, null, null, "student01", "s01.email", "s01password", 01);
        User s2 = new User(false, null, null, "student02", "s02.email", "s02password", 02);
        User s3 = new User(false, null, null, "student03", "s03.email", "s03password", 03);
        s1.appendClassesIn(c);
        s2.appendClassesIn(c);
        s3.appendClassesIn(c);
        t.getClassesOwned().get(0).appendUsers(s1);
        t.getClassesOwned().get(0).appendUsers(s2);
        t.getClassesOwned().get(0).appendUsers(s3);
        for (int i = 0; i < t.getClassesOwned().get(0).getUsers().size(); i++) {
            System.out.print("User " + i + ": " + t.getClassesOwned().get(0).getUsers().get(i).getName() + " \n");
        }


        System.out.println("\nCreating a quiz with four questions for the class.");

        Quiz q = new Quiz(
                "quiz1" ,
                "quizPass",
                null,
                null,
                null,
                60.00,
                t,
                false,
                false,
                0
        );

        Question q1 = new Question();
        Answer a11 = new Answer("A", "text for answer A", "feedback for answer A", true,  false, 01);
        Answer a12 = new Answer("B", "text for answer B", "feedback for answer B", false, false, 01);
        Answer a13 = new Answer("C", "text for answer C", "feedback for answer C", false, false, 01);
        Answer a14 = new Answer("D", "text for answer D", "feedback for answer D", false, false, 01);
        Answer[] q1a = {a11, a12, a13, a14};
        q1.setAnswers(q1a);
        q1.setPointValue(4);

        Question q2 = new Question();
        Answer a21 = new Answer("A", "text for answer A", "feedback for answer A", false, false, 01);
        Answer a22 = new Answer("B", "text for answer B", "feedback for answer B", false, false, 01);
        Answer a23 = new Answer("C", "text for answer C", "feedback for answer C", true,  false, 01);
        Answer a24 = new Answer("D", "text for answer D", "feedback for answer D", false, false, 01);
        Answer[] q2a = {a21, a22, a23, a24};
        q2.setAnswers(q2a);
        q2.setPointValue(7);

        Question q3 = new Question();
        Answer a31 = new Answer("A", "text for answer A", "feedback for answer A", false, false, 01);
        Answer a32 = new Answer("B", "text for answer B", "feedback for answer B", true,  false, 01);
        Answer a33 = new Answer("C", "text for answer C", "feedback for answer C", false, false, 01);
        Answer a34 = new Answer("D", "text for answer D", "feedback for answer D", false, false, 01);
        Answer[] q3a = {a31, a32, a33, a34};
        q3.setAnswers(q3a);
        q3.setPointValue(2);

        Question q4 = new Question();
        Answer a41 = new Answer("A", "text for answer A", "feedback for answer A", false, false, 01);
        Answer a42 = new Answer("B", "text for answer B", "feedback for answer B", true,  false, 01);
        Answer a43 = new Answer("C", "text for answer C", "feedback for answer C", false, false, 01);
        Answer a44 = new Answer("D", "text for answer D", "feedback for answer D", false, false, 01);
        Answer[] q4a = {a41, a42, a43, a44};
        q4.setAnswers(q4a);
        q4.setPointValue(5);

        q.appendQuestions(q1);
        q.appendQuestions(q2);
        q.appendQuestions(q3);
        q.appendQuestions(q4);

        t.getClassesOwned().get(0).appendQuizzes(q);

        System.out.println("Printing Quiz with questions.");
        for (int i = 0; i < t.getClassesOwned().get(0).getQuizzes().get(0).getQuestions().size(); i++) {
            System.out.println("Question (" + i + ")");
            for (int j = 0; j < 4; j++) {
                System.out.println(t.getClassesOwned().get(0).getQuizzes().get(0).getQuestions().get(i).getAnswers()[j].getLetter() + ") "
                        + t.getClassesOwned().get(0).getQuizzes().get(0).getQuestions().get(i).getAnswers()[j].getAnswerText() + " ("
                        + t.getClassesOwned().get(0).getQuizzes().get(0).getQuestions().get(i).getAnswers()[j].getCorrect() + ") "
                        + t.getClassesOwned().get(0).getQuizzes().get(0).getQuestions().get(i).getAnswers()[j].getFeedback());
            }
        }
        Date open = new Date();
        Date close = new Date();
        q.setCloseTime(close);
        q.setOpenTime(open);
        q.setOwner(t);
        System.out.println("Printing other quiz information:\nOpenTime: " + q.getOpenTime() + " CloseTime: " + q.getCloseTime() + " Timer: " + q.getTimer() +
                " PointTotal: " + q.getPointsPossible());

        c.appendQuizzes(q);
    }
}
