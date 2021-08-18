package com.sz.String;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/11/2 17:41
 */
public class equalsIgnoreCase {


    public static void main(String[] args) {
        String a = "TOTAL_COM_STATISTICS";
        String b = "total_com_statistics";
        String c = "TOTAL_COM_statistics";
        //equalsIgnoreCaseTest(a,b,c);
        //toUpperCaseTest(a, b, c);
        tableNameTest();
    }

    private static void tableNameTest() {
        String tableName="total_com_statistics";
        System.out.println("TOTAL_COM_STATISTICS".equals(tableName.toUpperCase()));
    }

    private static void toUpperCaseTest(String a, String b, String c) {
        System.out.println("a:" + a.indexOf(b));
        System.out.println("a:" + a.toUpperCase());
        System.out.println("a:" + a.toLowerCase());
        System.out.println("b:" + b.toUpperCase());
        System.out.println("b:" + b.toLowerCase());

        System.out.println(a.toUpperCase().indexOf(b.toUpperCase()));


    }

    private static void equalsIgnoreCaseTest(String a, String b, String c) {
        System.out.println(a.equals(b));
        System.out.println(a.equalsIgnoreCase(c));
        System.out.println(b.equalsIgnoreCase(c));
    }


}
