package com.zybooks.studyhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudyDatabase {

    private static StudyDatabase mStudyDb;
    private List<Subject> mSubjects;
    private HashMap<Long, List<Question>> mQuestions;

    public enum SubjectSortOrder { ALPHABETIC, UPDATE_DESC, UPDATE_ASC };

    public static StudyDatabase getInstance() {
        if (mStudyDb == null) {
            mStudyDb = new StudyDatabase();
        }
        return mStudyDb;
    }

    // Prevent instantiating from outside the class
    private StudyDatabase() {
        mSubjects = new ArrayList<>();
        mQuestions = new HashMap<>();

        // Add a few subjects and questions
        Subject subject = new Subject("Math");
        subject.setId(1);
        addSubject(subject);

        Question question = new Question();
        question.setId(1);
        question.setText("What is 2 + 3?");
        question.setAnswer("2 + 3 = 5");
        question.setSubjectId(1);
        addQuestion(question);

        question = new Question();
        question.setId(2);
        question.setText("What is pi?");
        question.setAnswer("Pi is the ratio of a circle's circumference to its diameter.");
        question.setSubjectId(1);
        addQuestion(question);

        subject = new Subject("History");
        subject.setId(2);
        addSubject(subject);

        question = new Question();
        question.setId(3);
        question.setText("On what date was the U.S. Declaration of Independence adopted?");
        question.setAnswer("July 4, 1776.");
        question.setSubjectId(2);
        addQuestion(question);

        subject = new Subject("Computing");
        subject.setId(3);
        addSubject(subject);
    }

    public void addSubject(Subject subject) {
        mSubjects.add(subject);
        List<Question> questionList = new ArrayList<>();
        mQuestions.put(subject.getId(), questionList);
    }

    public Subject getSubject(long subjectId) {
        for (Subject subject: mSubjects) {
            if (subject.getId() == subjectId) {
                return subject;
            }
        }

        return null;
    }

    public List<Subject> getSubjects(SubjectSortOrder order) {
        return mSubjects;
    }

    public void addQuestion(Question question) {
        List<Question> questionList = mQuestions.get(question.getSubjectId());
        if (questionList != null) {
            questionList.add(question);
        }
    }

    public List<Question> getQuestions(long subjectId) {
        return mQuestions.get(subjectId);
    }
}
