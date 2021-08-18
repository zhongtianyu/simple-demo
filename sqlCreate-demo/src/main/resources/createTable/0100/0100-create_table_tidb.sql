-- 0100建表SQL脚本
-- SET NAMES utf8mb4;
-- SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS BL_0100_ACC_ORI_JKPAY;
CREATE TABLE `BL_0100_ACC_ORI_JKPAY` (
 `ID` varchar(50) NOT NULL COMMENT 'ID',
 `BUSILINE` varchar(30) DEFAULT NULL COMMENT '业务线',
 `SETTLE_DATE` varchar(30) NOT NULL COMMENT '账期日',
 `PROVINCE` varchar(30) DEFAULT NULL COMMENT '省编码',
 `JSON` json DEFAULT NULL COMMENT 'JSON集'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='金科原始对账数据表'
PARTITION BY RANGE COLUMNS(SETTLE_DATE)
(PARTITION p20190928 VALUES LESS THAN ('20190929') ENGINE = InnoDB,
 PARTITION p20190929 VALUES LESS THAN ('20190930') ENGINE = InnoDB,
 PARTITION p20190930 VALUES LESS THAN ('20190931') ENGINE = InnoDB);
 ALTER TABLE BL_0100_ACC_ORI_JKPAY ADD INDEX INDEX_BL_0100_ACC_ORI_JKPAY_SETTLE_DATE(SETTLE_DATE);

DROP TABLE IF EXISTS BL_0100_ACC_ORI_UPAY;
CREATE TABLE `BL_0100_ACC_ORI_UPAY` (
 `ID` varchar(50) NOT NULL COMMENT 'ID',
 `BUSILINE` varchar(30) DEFAULT NULL COMMENT '业务线',
 `SETTLE_DATE` varchar(30) NOT NULL COMMENT '账期日',
 `PROVINCE` varchar(30) DEFAULT NULL COMMENT '省编码',
 `JSON` json DEFAULT NULL COMMENT 'JSON集'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='统一支付原始对账数据表'
PARTITION BY RANGE COLUMNS(SETTLE_DATE)
(PARTITION p20190928 VALUES LESS THAN ('20190929') ENGINE = InnoDB,
 PARTITION p20190929 VALUES LESS THAN ('20190930') ENGINE = InnoDB,
 PARTITION p20190930 VALUES LESS THAN ('20190931') ENGINE = InnoDB);
 ALTER TABLE BL_0100_ACC_ORI_UPAY ADD INDEX INDEX_BL_0100_ACC_ORI_UPAY_SETTLE_DATE(SETTLE_DATE);

DROP TABLE IF EXISTS BL_0100_ACC_COM_PAYAGENT_UPAY;
CREATE TABLE `BL_0100_ACC_COM_PAYAGENT_UPAY` (
 `ID` varchar(50) NOT NULL COMMENT 'ID',
 `BUSILINE` varchar(30) DEFAULT NULL COMMENT '业务线',
 `SETTLE_DATE` varchar(30) NOT NULL COMMENT '账期日',
 `PROVINCE` varchar(30) DEFAULT NULL COMMENT '省编码',
 `JSON` json DEFAULT NULL COMMENT 'JSON集'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='补全数据表'
PARTITION BY RANGE COLUMNS(SETTLE_DATE)
(PARTITION p20190928 VALUES LESS THAN ('20190929') ENGINE = InnoDB,
 PARTITION p20190929 VALUES LESS THAN ('20190930') ENGINE = InnoDB,
 PARTITION p20190930 VALUES LESS THAN ('20190931') ENGINE = InnoDB);
 ALTER TABLE BL_0100_ACC_COM_PAYAGENT_UPAY ADD INDEX INDEX_BL_0100_ACC_COM_PAYAGENT_UPAY_SETTLE_DATE(SETTLE_DATE);

DROP TABLE IF EXISTS BL_0100_ACC_DIF_MERCHANT;
CREATE TABLE `BL_0100_ACC_DIF_MERCHANT` (
 `ID` varchar(50) NOT NULL COMMENT 'ID',
 `BUSILINE` varchar(30) DEFAULT NULL COMMENT '业务线',
 `SETTLE_DATE` varchar(30) NOT NULL COMMENT '账期日',
 `PROVINCE` varchar(30) DEFAULT NULL COMMENT '省编码',
 `JSON` json DEFAULT NULL COMMENT 'JSON集'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户差异数据表'
PARTITION BY RANGE COLUMNS(SETTLE_DATE)
(PARTITION p20190928 VALUES LESS THAN ('20190929') ENGINE = InnoDB,
 PARTITION p20190929 VALUES LESS THAN ('20190930') ENGINE = InnoDB,
 PARTITION p20190930 VALUES LESS THAN ('20190931') ENGINE = InnoDB);
 ALTER TABLE BL_0100_ACC_DIF_MERCHANT ADD INDEX INDEX_BL_0100_ACC_DIF_MERCHANT_SETTLE_DATE(SETTLE_DATE);


