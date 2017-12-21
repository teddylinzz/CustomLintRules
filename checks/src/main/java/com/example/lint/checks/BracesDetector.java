/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lint.checks;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UForEachExpression;
import org.jetbrains.uast.UIfExpression;
import org.jetbrains.uast.UWhileExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Sample detector showing how to analyze Kotlin/Java code.
 * This example flags all string literals in the code that contain
 * the word "lint".
 */
public class BracesDetector extends Detector implements Detector.UastScanner {
    /** Issue describing the problem and pointing to the detector implementation */
    public static final Issue ISSUE = Issue.create(
            // ID: used in @SuppressLint warnings etc
            "Formatting",

            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            "Braces are used where optional",

            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            "Braces are used with if, else, for, do and while statements, even when the body is empty or contains only a single statement.\n",
            Category.TYPOGRAPHY,
            6,
            Severity.WARNING,
            new Implementation(
                    BracesDetector.class,
                    Scope.JAVA_FILE_SCOPE));

    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        List<Class<? extends UElement>> result = new ArrayList<>();
        result.add(UIfExpression.class);
        result.add(UWhileExpression.class);
        result.add(UForEachExpression.class);
        return result;
    }

    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new UElementHandler() {
            @Override
            public void visitIfExpression(UIfExpression uIfExpression) {
                if (!new com.example.lint.rules.BracesRule().check(uIfExpression.asRenderString())) {
                    context.report(ISSUE, uIfExpression, context.getLocation(uIfExpression),
                            "Always use curly braces for \"if/else/for/while/do\" statements");
                }
            }

            @Override
            public void visitWhileExpression(UWhileExpression uWhileExpression) {
                if (!new com.example.lint.rules.BracesRule().check(uWhileExpression.asRenderString())) {
                    context.report(ISSUE, uWhileExpression, context.getLocation(uWhileExpression),
                            "Always use curly braces for \"if/else/for/while/do\" statements");
                }
            }

            @Override
            public void visitForEachExpression(UForEachExpression uForEachExpression) {
                if (!new com.example.lint.rules.BracesRule().check(uForEachExpression.asRenderString())) {
                    context.report(ISSUE, uForEachExpression, context.getLocation(uForEachExpression),
                            "Always use curly braces for \"if/else/for/while/do\" statements");
                }
            }
        };
    }
}
