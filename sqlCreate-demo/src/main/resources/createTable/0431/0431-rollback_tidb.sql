-- TiDB数据库 回退SQL
-- 删除表0431记录
DROP TABLE IF EXISTS BL_0431_ACC_ORI_JKPAY;
DROP TABLE IF EXISTS BL_0431_ACC_ORI_UPAY;
DROP TABLE IF EXISTS BL_0431_ACC_COM_PAYAGENT_UPAY;
DROP TABLE IF EXISTS BL_0431_ACC_DIF_MERCHANT;
delete from PARTITION_CONFIG where table_name in ('BL_0431_ACC_ORI_JKPAY','BL_0431_ACC_ORI_UPAY','BL_0431_ACC_COM_PAYAGENT_UPAY','BL_0431_ACC_DIF_MERCHANT');
