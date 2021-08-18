package com.sz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/25 12:38
 */
public class SolveIfElse {

    public Map<Classifier, String> booleanGrouping() throws Exception {
        List<String> strings = new ArrayList<>();
        strings.add("ala");
        strings.add("ela");
        strings.add("jan");
        // our ifs:
    /*
     if(!string.endsWith("n")){
     }else if(string.startsWith("e")){}

     final map should contains two elements
     endsWithN -> ["jan"]
     startsWithE -> ["ela"]
     NOT_MATCH -> ["ala"]

    */
        return strings.stream()
                .collect(
                        // using function Obj -> Bool not predicate
                        Collectors.groupingBy(Classifier::apply)
                ).entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                e -> e.getKey(),
                                e -> e.getValue().stream().collect(Collectors.joining(""))
                        )
                );
    }
}
