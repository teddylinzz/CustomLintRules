package com.example.lint.checks;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import org.jetbrains.uast.UClass;
import org.jetbrains.uast.UComment;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UMethod;

import java.util.ArrayList;
import java.util.List;

import rules.CommentRule;

/**
 * Created by teddylin on 20/12/2017.
 */

public class CommentDetector extends Detector implements Detector.UastScanner {

    public static final Issue ISSUE = Issue.create(
            // ID: used in @SuppressLint warnings etc
            "Comment Formatting",

            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            "Bad comment style",

            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            "Please use clean comments to annotate a program and help the reader (or grader) understand how and why your program works",
            Category.TYPOGRAPHY,
            6,
            Severity.WARNING,
            new Implementation(
                    CommentDetector.class,
                    Scope.JAVA_FILE_SCOPE));


    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        List<Class<? extends UElement>> result = new ArrayList<>();
        result.add(UClass.class);
        return result;
    }

    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new UElementHandler() {
            @Override
            public void visitClass(UClass uClass) {
                for (UMethod method : uClass.getMethods()) {
                    for (UComment comment : method.getComments()) {
                        if (new CommentRule().check(comment.getText())) {
                            context.report(ISSUE, comment, context.getLocation(comment.getPsi()),
                                    "Do not input abnormal characters.");
                        }
                    }

                    for (UComment comment : method.getUastBody().getComments()) {
                        if (new CommentRule().check(comment.getText())) {
                            context.report(ISSUE, comment, context.getLocation(comment.getPsi()),
                                    "Do not input abnormal characters.");
                        }
                    }
                }

                for (UComment comment : uClass.getComments()) {
                    if (new CommentRule().check(comment.getText())) {
                        context.report(ISSUE, comment, context.getLocation(comment.getPsi()),
                                "Do not input abnormal characters.");
                    }
                }
            }
        };
    }
}
