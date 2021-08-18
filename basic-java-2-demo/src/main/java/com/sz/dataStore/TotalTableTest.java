package com.sz.dataStore;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/11/3 9:19
 */
public class TotalTableTest {



    public static void main(String[] args) {

        DataStorageModel model = new DataStorageModel();
        model.setFlowType("paycharge-charge");

       String tableName ="total_com_statistics";
        StringBuffer sqlBuffer = new StringBuffer("insert into " + tableName + "(ID,BUSILINE,SETTLE_DATE,JSON");

        if ("TOTAL_COM_STATISTICS".equals(tableName.toUpperCase()) && null != model.getFlowType() && model.getFlowType().trim().length() > 0) {
            sqlBuffer.append(",FLOW_TYPE");
        }
        checkFlowType(tableName,model,sqlBuffer);
    }

    private static void checkFlowType(String tableName,DataStorageModel model,StringBuffer sqlBuffer) {

        if ("TOTAL_COM_STATISTICS".equals(tableName.toUpperCase())) {
            //流水类型 纯支付：pay，充值+支付（支付侧）：paycharge-pay，充值+支付（充值侧）：paycharge-charge，纯充值：charge
            String flowType = model.getFlowType().equals("account") ? "pay" : model.getFlowType();
            sqlBuffer.append("'" + flowType + "',");
        }
        System.out.println(sqlBuffer);
    }
}
