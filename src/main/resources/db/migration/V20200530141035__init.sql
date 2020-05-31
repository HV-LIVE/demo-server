CREATE TABLE `system_config`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `push_ip`     varchar(50) NOT NULL COMMENT '推流ip',
    `push_port`   int(11)     NOT NULL COMMENT '推流端口',
    `pull_ip`     varchar(50) NOT NULL COMMENT '拉流ip',
    `pull_port`   int(11)     NOT NULL COMMENT '拉流端口',
    `create_time` datetime(3) NOT NULL COMMENT '创建时间',
    `update_time` datetime(3) NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
);

CREATE TABLE `user`
(
    `id`           bigint(20)  NOT NULL AUTO_INCREMENT,
    `account`      varchar(50) NOT NULL COMMENT '账号',
    `password`     varchar(50) NOT NULL COMMENT '密码',
    `name`         varchar(50) NOT NULL COMMENT '姓名',
    `phone_number` varchar(11) NOT NULL COMMENT '手机号',
    `stream_name`  varchar(50) NOT NULL COMMENT '流名',
    `create_time`  datetime(3) NOT NULL COMMENT '创建时间',
    `update_time`  datetime(3) NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uidx_account` (`account`)
);

CREATE TABLE `channel`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) NOT NULL COMMENT '频道名',
    `create_time` datetime(3) NOT NULL COMMENT '创建时间',
    `update_time` datetime(3) NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uidx_name` (`name`)
);

CREATE TABLE `section`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `channel_id`  bigint(20)  NOT NULL COMMENT '所属频道ID',
    `name`        varchar(50) NOT NULL COMMENT '栏目名',
    `create_time` datetime(3) NOT NULL COMMENT '创建时间',
    `update_time` datetime(3) NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uidx_channel_id_name` (`channel_id`, `name`)
);

CREATE TABLE `live`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `title`       varchar(50) NOT NULL COMMENT '标题',
    `section_id`  bigint(20)  NOT NULL COMMENT '所属栏目ID',
    `user_id`     bigint(20)  NOT NULL COMMENT '所属用户ID',
    `start_time`  datetime(3) NOT NULL COMMENT '开始时间',
    `end_time`    datetime(3) NOT NULL COMMENT '结束时间',
    `create_time` datetime(3) NOT NULL COMMENT '创建时间',
    `update_time` datetime(3) NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
);