package com.sz.String;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/7/28 10:43
 */
public class check {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(16);
        boolean value = convert("1", "2",
                (a1, a2) -> map.put(a1, a2),
                key -> map.entrySet().contains(key));
        System.out.println(value);
    }

    public static <T1, T2, R1, R2> R2 convert(T1 t1,
                                              T2 t2,
                                              BiFunction<T1, T2, R1> biFunction,
                                              Function<R1, R2> function) {
        return biFunction.andThen(function).apply(t1, t2);
    }
}
