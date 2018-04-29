package com.example.common;

import com.example.common.REST.ClassREST;
import com.example.common.REST.PotatOsApi;
import com.example.common.REST.QuizREST;
import com.example.common.REST.UserREST;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

class QuizRESTTest {
    @BeforeEach
    void setUp()  {
        PotatOsApi.resetDatabase();  //calls to the server to reset the database
    }

    @AfterEach
    void tearDown() {
        PotatOsApi.resetDatabase(); //calls to the server to reset the database
    }

    @Test
    //the server must be running for this to work
    void testREST()  {
        Assert.assertEquals(true, PotatOsApi.isServerUp());

        User user = new User(
                false,
                null,
                null,
                "Jacob Draddy",
                "email@example.com",
                "asdfopjasdfopjasdoji",
                0);
        UserREST.createUser(user);
        Assert.assertNotEquals(0, user.getId());


        Class cls = new Class();
        cls.setOwnerId(user.getId());
        cls.setClassName("Science Class");
        cls.setClassCode("U828d");
        ClassREST.createClass(cls);



        User user2 = new User(
                false,
                null,
                null,
                "Donald Duck",
                "email2@example.com",
                "asdfopjasdfopjasdoji",
                0);
        UserREST.createUser(user2);


        user2.getClassesIn().add(cls);
        UserREST.updateUser(user2);



        Assert.assertEquals("Science Class", cls.getClassName());

        //the ID's should be set, and not 0
        Assert.assertNotEquals(0, cls.getClassID());
        Assert.assertNotEquals(0, user.getId());

        Class dbCls = ClassREST.getClassById(cls.getClassID());
        Assert.assertNotEquals(null, dbCls);
        Assert.assertEquals(cls.getClassID(), dbCls.getClassID());
        Assert.assertEquals(cls.getClassName(), dbCls.getClassName());
        Assert.assertEquals(cls.getClassCode(), dbCls.getClassCode());

        User dbUsr = UserREST.getByEmailPass(user.getEmail(), user.getPasswordHash());
        Assert.assertNotEquals(null, dbUsr.getClassesIn());
        Assert.assertEquals(user.getId(), dbUsr.getId());
        Assert.assertEquals(user.getEmail(), dbUsr.getEmail());
        Assert.assertEquals(user.getPasswordHash(), dbUsr.getPasswordHash());
        Assert.assertEquals(user.getIsTeacher(), dbUsr.getIsTeacher());
        Assert.assertEquals(user.getName(), dbUsr.getName());


        Answer an1 = new Answer("A", "Answer 1", "", false, false, 0);
        Answer an2 = new Answer("B", "Answer 1", "", false, false, 0);
        Answer an3 = new Answer("C", "Answer 1", "", false, false, 0);
        Answer an4 = new Answer("D", "Answer 1", "", false, false, 0);

        //    public Question(String prompt, QuestionImage image, Boolean correct, Answer[] answers, int pointValue, int id) {

        Answer[] answers = {an1, an2, an3, an4};
        Question question = new Question(
                "Pick the right answer...",
                null,
                false,
                answers,
                1,
                0
        );

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question);

        //   public Quiz(String quizName, String password, ArrayList<Question> questions, Date openTime, Date closeTime, Double timer, User owner, boolean submitted) {

        Quiz quiz = new Quiz(
                "Test Quiz",
                "password",
                questions,
                new Date(),
                new Date(),
                0.0,
                user,
                false
        );


        QuizREST.createQuiz(quiz);
        Assert.assertNotEquals(0, quiz.getId());
        QuizREST.getQuizById(quiz.getId());

        Assert.assertEquals(1, quiz.getQuestions().size());
        Assert.assertEquals(4, quiz.getQuestions().get(0).getAnswers().length);
        Assert.assertNotEquals(0, quiz.getQuestions().get(0).getId());
        Assert.assertNotEquals(4, quiz.getQuestions().get(0).getAnswers()[0].getId());
    }
}