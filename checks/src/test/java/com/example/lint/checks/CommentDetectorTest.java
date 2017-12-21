package com.example.lint.checks;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import java.util.Collections;
import java.util.List;

/**
 * Created by teddylin on 20/12/2017.
 */
public class CommentDetectorTest extends LintDetectorTest {

    public void testComment() {
        lint().files(
                java("" +
                        "package test.pkg;\n" +
                        "//開始\n" +
                        "public class TestClass1 {\n" +
                        "    // In a comment, mentioning \"lint\" has no effect\n" +
                        "    //函式上\n" +
                        "    public void test() {\n" +
                        "        //函式內\n" +
                        "    }//函式後\n" +
                        "    //函式下\n" +
                        "//結束\n " +
                        "}"))
                .run()
                .expect("src/test/pkg/TestClass1.java:2: Warning: Do not input abnormal characters. [Comment Formatting]\n" +
                        "//開始\n" +
                        "~~~~\n" +
                        "src/test/pkg/TestClass1.java:5: Warning: Do not input abnormal characters. [Comment Formatting]\n" +
                        "    //函式上\n" +
                        "    ~~~~~\n" +
                        "src/test/pkg/TestClass1.java:7: Warning: Do not input abnormal characters. [Comment Formatting]\n" +
                        "        //函式內\n" +
                        "        ~~~~~\n" +
                        "src/test/pkg/TestClass1.java:8: Warning: Do not input abnormal characters. [Comment Formatting]\n" +
                        "    }//函式後\n" +
                        "     ~~~~~\n" +
                        "src/test/pkg/TestClass1.java:9: Warning: Do not input abnormal characters. [Comment Formatting]\n" +
                        "    //函式下\n" +
                        "    ~~~~~\n" +
                        "src/test/pkg/TestClass1.java:10: Warning: Do not input abnormal characters. [Comment Formatting]\n" +
                        "//結束\n" +
                        "~~~~\n" +
                        "0 errors, 6 warnings\n");
    }


    @Override
    protected Detector getDetector() {
        return new CommentDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Collections.singletonList(CommentDetector.ISSUE);
    }
}