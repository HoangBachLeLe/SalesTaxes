create table goods
(
    id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    goods_name VARCHAR(20) NOT NULL,
    category   VARCHAR(20) NOT NULL,
    origin     VARCHAR(20) NOT NULL,
    price      DOUBLE      NOT NULL
);