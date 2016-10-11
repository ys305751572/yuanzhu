-- ----------------------------
-- Table structure for tb_tfw_tzgg
-- ----------------------------
DROP TABLE IF EXISTS `tb_report`;
CREATE TABLE `tb_report` (
  `ID`         INT(11)      NOT NULL AUTO_INCREMENT,
  `USERID`     INT(255)     NOT NULL,
  `CONTENT`    VARCHAR(255) NULL     DEFAULT '',
  `TYPE`       VARCHAR(255) NOT NULL DEFAULT '',
  `STATUS`     INT(2)       NOT NULL DEFAULT 0,
  `CREATETIME` BIGINT,
  PRIMARY KEY (`ID`)
)
  ENGINE = InnoDB
  COMMENT '举报表'
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 2;

-- ----------------------------
-- Records of tb_tfw_tzgg
-- ----------------------------


