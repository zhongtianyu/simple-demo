import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/11/7 9:39
 */
@RunWith(SpringRunner.class)
//自动导入配置
//@ImportAutoConfiguration(classes = {PooledFTPClientAutoConfiguration.class})
//获取配置
@PropertySource("application.properties")
public class ftpTest {

    @Test
    public void test0Put() throws Exception {

        String prov = "100-北京,200-广东,210-上海,220-天津,230-重庆,240-辽宁,250-江苏,270-湖北,280-四川,290-陕西,311-河北,351-山西,371-河南,431-吉林,451-黑龙江,471-内蒙古,531-山东,551-安徽,571-浙江,591-福建,731-湖南,771-广西,791-江西,851-贵州,871-云南,891-西藏,898-海南,931-甘肃,951-宁夏,971-青海,991-新疆";
        String[] provinces = prov.split(",");
        List<String> list = new ArrayList<>();
        int i = 0;
        for (String province : provinces) {
            String sql = "INSERT INTO cm_province(PROVINCE, SETTLE_DATE, FLOW_TYPE, FILE_TYPE, PRO_NAME)VALUES('provinces','20200921','card', 0, 'proName');";
            String[] pro = province.split("-");
            sql = sql.replace("provinces", pro[0]);
            sql = sql.replace("proName", pro[1]);

            list.add(i, sql);
            i++;
        }

        ClassPathResource classPathResource = new ClassPathResource("分省导入配置.sql");
        File file = classPathResource.getFile();

        FileUtils.writeLines(file,"UTF-8",list,true);

    }
}
