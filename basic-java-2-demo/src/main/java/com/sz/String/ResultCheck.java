package com.sz.String;

import com.sz.String.cmsz.Result;
import com.sz.String.cmsz.ReturnResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/8/2 12:39
 */
public class ResultCheck {
    public static void main(String[] args) {
        ArrayList<Result> list = new ArrayList<>();
        Map<String, Result> map = new HashMap<>(16);
        ReturnResult returnResult2 = new ReturnResult(Result.SUCCESS);

        Result result = Result.SUCCESS;
        list.add(result);
        map.put("result", result);

        changeResult(result, list, map, returnResult2);
        System.out.println(result);//SUCCESS
        System.out.println(list.get(0));//SUCCESS
        System.out.println(map.get("result"));//FAILED
        System.out.println(new ReturnResult(result).getResult());//SUCCESS
        System.out.println(returnResult2.getResult());//FAILED
    }

    /**
     * result更新的结果并没有给到主流程，因为result属于局部变量，但是当放到集合中时会有改变。
     */
    private static void changeResult(Result result, ArrayList<Result> list, Map<String, Result> map, ReturnResult returnResult2) {
        result = Result.FAILED;
        list.add(result);
        map.put("result", result);
        returnResult2.setResult(result);
    }
}
