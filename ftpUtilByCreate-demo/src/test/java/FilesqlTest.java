import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class FilesqlTest {

    @Test
    public void testBOSS() {
        try {

            String file = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\和包分省建表.sql";

            String newFile = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\boss";

            String province = "100,200,210,220,230,240,250,270,280,290,311,351,371,431,451,471,531,551,571,591,731,771,791,851,871,891,898,931,951,971,991";

            String[] provinces = province.split(",");


            for (String pro : provinces) {
                List<String> list = FileUtils.readLines(new File(file), "UTF-8");

                for (int i = 0; i < list.size(); i++) {
                    String temp = list.get(i).replace("100", pro);
                    list.remove(i);
                    list.add(i, temp);

                }

                FileUtils.writeLines(new File(newFile + "/HEBAO_BOSS_DATA_" + pro), "UTF-8", list, false);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBOSSsql() {
        try {

            String newFile = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\boss.sql";

            String file = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\boss";

            String province = "100,200,210,220,230,240,250,270,280,290,311,351,371,431,451,471,531,551,571,591,731,771,791,851,871,891,898,931,951,971,991";

            String[] provinces = province.split(",");


            for (String pro : provinces) {
                List<String> list = FileUtils.readLines(new File(file + "/HEBAO_BOSS_DATA_" + pro), "UTF-8");
                list.add("\r\n\r\n");
                FileUtils.writeLines(new File(newFile), "UTF-8", list, true);
                //  FileUtils.writeLines(new File(newFile ), "UTF-8", list, true);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testHEBAO() {
        try {

            String file = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\和包商户分省建表.sql";

            String newFile = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\hebao";

            String province = "100,200,210,220,230,240,250,270,280,290,311,351,371,431,451,471,531,551,571,591,731,771,791,851,871,891,898,931,951,971,991";

            String[] provinces = province.split(",");


            for (String pro : provinces) {
                List<String> list = FileUtils.readLines(new File(file), "UTF-8");

                for (int i = 0; i < list.size(); i++) {
                    String temp = list.get(i).replace("100", pro);
                    list.remove(i);
                    list.add(i, temp);
                }

                FileUtils.writeLines(new File(newFile + "/HEBAO_DATA_" + pro), "UTF-8", list, false);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testHEBAOsql() {
        try {

            String newFile = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\hebao.sql";

            String file = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\hebao";

            String province = "100,200,210,220,230,240,250,270,280,290,311,351,371,431,451,471,531,551,571,591,731,771,791,851,871,891,898,931,951,971,991";

            String[] provinces = province.split(",");


            for (String pro : provinces) {
                List<String> list = FileUtils.readLines(new File(file + "/HEBAO_DATA_" + pro), "UTF-8");
                list.add("\r\n\r\n");
                FileUtils.writeLines(new File(newFile), "UTF-8", list, true);
                //  FileUtils.writeLines(new File(newFile ), "UTF-8", list, true);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testCHARGE() {
        try {

            String file = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\charge_trans_100.sql";

            String newFile = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\charge";

            String province = "100,200,210,220,230,240,250,270,280,290,311,351,371,431,451,471,531,551,571,591,731,771,791,851,871,891,898,931,951,971,991";

            String[] provinces = province.split(",");


            for (String pro : provinces) {
                List<String> list = FileUtils.readLines(new File(file), "UTF-8");

                for (int i = 0; i < list.size(); i++) {
                    String temp = list.get(i).replace("100", pro);
                    list.remove(i);
                    list.add(i, temp);
                }

                FileUtils.writeLines(new File(newFile + "/CHARGE_TRANS_" + pro), "UTF-8", list, false);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCHARGEsql() {
        try {

            String newFile = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\charge.sql";

            String file = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\charge";

            String province = "100,200,210,220,230,240,250,270,280,290,311,351,371,431,451,471,531,551,571,591,731,771,791,851,871,891,898,931,951,971,991";

            String[] provinces = province.split(",");


            for (String pro : provinces) {
                List<String> list = FileUtils.readLines(new File(file + "/CHARGE_TRANS_" + pro), "UTF-8");
                list.add("\r\n\r\n");
                FileUtils.writeLines(new File(newFile), "UTF-8", list, true);
                //  FileUtils.writeLines(new File(newFile ), "UTF-8", list, true);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void droptableTest() {
        try {

            String sql1 = "drop table  HEBAO_BOSS_DATA_";
            String sql2 = "drop table  HEBAO_DATA_";
            String sql23 = "drop table  HEBAO_DATA_";

            String file = "D:\\办公\\工作\\备份\\业务平台\\业务需求\\202008\\和包扩容迁移\\定时创建分区的配置.sql";

            String province = "100,200,210,220,230,240,250,270,280,290,311,351,371,431,451,471,531,551,571,591,731,771,791,851,871,891,898,931,951,971,991";

            String[] provinces = province.split(",");

            for (String pro : provinces) {
                List<String> list = FileUtils.readLines(new File(file), "UTF-8");
                for (int i = 0; i < list.size(); i++) {
                    String temp = list.get(i).replace("100", pro);
                    list.remove(i);
                    list.add(i, temp);
                }
                list.add("\r\n");
                FileUtils.writeLines(new File(file), "UTF-8", list, true);
                //  FileUtils.writeLines(new File(newFile ), "UTF-8", list, true);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 省字段替换，数据入库
     */
    @Test
    public void testCmProvince() {
        try {
            String path = this.getClass().getClassLoader().getResource("分省导入配置.sql").getPath();
            //String path = "D:\\idea_workspace\\simple-demo\\ftp-client-demo\\src\\test\\resources\\分省导入配置.sql";
            //获取中文需要设置编码
            path = URLDecoder.decode(path, "UTF-8");

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
            FileUtils.writeLines(new File(path), "UTF-8", list, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFile() {

        String path ="D:\\idea_workspace\\simple-demo\\ftp-client-demo\\src\\test\\resources\\分省导入配置.sql";
        File file = new File(path);
        System.out.println(file);
    }

    @Test
    public void testLoad() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("分省导入配置.sql").getPath();
        path = URLDecoder.decode(path, "UTF-8");
        File file = new File(path);

        System.out.println(file);
    }
}
