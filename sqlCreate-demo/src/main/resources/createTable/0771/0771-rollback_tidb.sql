-- TiDB数据库 回退SQL
-- 删除表0771记录
DROP TABLE IF EXISTS BL_0771_ACC_ORI_JKPAY;
DROP TABLE IF EXISTS BL_0771_ACC_ORI_UPAY;
DROP TABLE IF EXISTS BL_0771_ACC_COM_PAYAGENT_UPAY;
DROP TABLE IF EXISTS BL_0771_ACC_DIF_MERCHANT;
delete from PARTITION_CONFIG where table_name in ('BL_0771_ACC_ORI_JKPAY','BL_0771_ACC_ORI_UPAY','BL_0771_ACC_COM_PAYAGENT_UPAY','BL_0771_ACC_DIF_MERCHANT');