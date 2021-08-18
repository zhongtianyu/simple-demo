package com.sz;

import java.util.Arrays;
import java.util.function.Predicate;

enum Classifier implements Predicate<String> {
        ENDS_WITH_N {
            @Override
            public boolean test(String s) {
                return s.endsWith("n");
            }
        },
        STARTS_WITH_E {
            @Override
            public boolean test(String s) {
                return s.startsWith("e");
            }
        }, NOT_MATCH {
            @Override
            public boolean test(String s) {
                return false;
            }
        };

        public static Classifier apply(String s) {
            return Arrays.stream(Classifier.values())
                    .filter(c -> c.test(s))
                    .findFirst().orElse(NOT_MATCH);
        }
    }