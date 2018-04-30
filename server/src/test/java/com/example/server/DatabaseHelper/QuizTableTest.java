package com.example.server.DatabaseHelper;

import com.example.common.Answer;
import com.example.common.Class;
import com.example.common.Question;
import com.example.common.Quiz;
import com.example.common.REST.SmallClass;
import com.example.common.User;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

class QuizTableTest {
    @BeforeEach
    void setUp() throws SQLException {
        PotatOsDatabase.deleteTables();
        PotatOsDatabase.createTables();
    }

    @AfterEach
    void tearDown() {
//        PotatOsDatabase.deleteTables();  //don't delete so you can inspect the database
    }

    @Test
    void createQuiz() throws SQLException {
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



        Answer an1 = new Answer("A", "Answer 1", "", false, false, 0);
        Answer an2 = new Answer("B", "Answer 1", "", false, false, 0);
        Answer an3 = new Answer("C", "Answer 1", "", false, false, 0);
        Answer an4 = new Answer("D", "Answer 1", "", false, false, 0);

        AnswerTable.createAnswer(an1);
        AnswerTable.createAnswer(an2);
        AnswerTable.createAnswer(an3);
        AnswerTable.createAnswer(an4);

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

        QuestionTable.createQuestion(question);
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


        QuizTable.createQuiz(quiz);
        Assert.assertNotEquals(0, quiz.getId());
        QuizTable.getQuizById(quiz.getId());

        Assert.assertEquals(1, quiz.getQuestions().size());
        Assert.assertEquals(4, quiz.getQuestions().get(0).getAnswers().length);
        Assert.assertNotEquals(0, quiz.getQuestions().get(0).getId());
        Assert.assertNotEquals(4, quiz.getQuestions().get(0).getAnswers()[0].getId());
    }
}