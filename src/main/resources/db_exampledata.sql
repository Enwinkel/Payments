
-- ----------------------------
-- Sequence structure for transactions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "transactions_id_seq";
CREATE SEQUENCE "transactions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS "accounts";
CREATE TABLE "accounts" (
  "id" int4 NOT NULL,
  "number" int8 NOT NULL,
  "account_name" varchar(45) COLLATE "pg_catalog"."default" NOT NULL,
  "balance" float8 NOT NULL,
  "blocked" bool,
  "user_id" int4 NOT NULL
)
;

-- ----------------------------
-- Records of accounts
-- ----------------------------
BEGIN;
INSERT INTO "accounts" VALUES (1, 4411113325465781, 'Account 1', 0, 'f', 1);
INSERT INTO "accounts" VALUES (2, 4411113325465782, 'Account 1', 150, 'f', 2);
INSERT INTO "accounts" VALUES (3, 4411113325465783, 'Account 2', 317.3, 'f', 2);
INSERT INTO "accounts" VALUES (4, 4411113325465784, 'Account 1', 1230, 'f', 3);
INSERT INTO "accounts" VALUES (5, 4411113234568493, 'Account 2', 7580, 'f', 3);
COMMIT;

-- ----------------------------
-- Table structure for contact_details
-- ----------------------------
DROP TABLE IF EXISTS "contact_details";
CREATE TABLE "contact_details" (
  "id" int4 NOT NULL,
  "city" varchar(45) COLLATE "pg_catalog"."default" NOT NULL,
  "street" varchar(45) COLLATE "pg_catalog"."default" NOT NULL,
  "home" varchar(45) COLLATE "pg_catalog"."default" NOT NULL,
  "apartment" varchar(45) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(45) COLLATE "pg_catalog"."default" NOT NULL,
  "phone" varchar(45) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of contact_details
-- ----------------------------
BEGIN;
INSERT INTO "contact_details" VALUES (1, 'Kiev', 'Kievskaya', '12', '21', 'admin@gmail.com', '+380992589898');
INSERT INTO "contact_details" VALUES (2, 'Kharkiv', 'Traktorostroiteley', '79/42', '80', 'serdyukov.aleksey@gmail.com', '0504038624');
INSERT INTO "contact_details" VALUES (16, 'Kharkiv', 'Poltavski shlyakh', '10', '15', 'che@ukr.net', '+380503230505');
INSERT INTO "contact_details" VALUES (17, 'Kharkiv', 'Traktorostroiteley', '79/42', '80', 'serdyukov.aleksey@gmail.com', '0504038624');
INSERT INTO "contact_details" VALUES (18, 'Kharkiv', 'Traktorostroiteley', '79/42', '80', 'serdyukov.aleksey@gmail.com', '0504038624');
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "roles";
CREATE TABLE "roles" (
  "id" int4 NOT NULL,
  "name" varchar(45) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO "roles" VALUES (1, 'ADMIN');
INSERT INTO "roles" VALUES (2, 'CLIENT');
COMMIT;

-- ----------------------------
-- Table structure for services
-- ----------------------------
DROP TABLE IF EXISTS "services";
CREATE TABLE "services" (
                            "id" int4 NOT NULL,
                            "name" varchar(45) COLLATE "pg_catalog"."default" NOT NULL,
                            "description" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of services
-- ----------------------------
BEGIN;
INSERT INTO "services" VALUES (1, 'Перевод на карту', 'Перевод на карту другого абонента');
INSERT INTO "services" VALUES (2, 'Пополнение мобильного', 'Пополнение мобильного телефона');
INSERT INTO "services" VALUES (3, 'Интернет', 'Оплата интернета по номеру личного счета');
COMMIT;


-- ----------------------------
-- Table structure for transactions
-- ----------------------------
DROP TABLE IF EXISTS "transactions";
CREATE TABLE "transactions" (
  "id" int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "timestamp" timestamp(6),
  "account_id" int4,
  "amount" numeric(255,2),
  "is_credit" bool,
  "description" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of transactions
-- ----------------------------
BEGIN;
INSERT INTO "transactions" VALUES (1, '2021-03-27 11:25:14', 2, 100.00, 't', 'top up');
INSERT INTO "transactions" VALUES (2, '2021-03-27 12:32:59.809', 2, 100.00, 't', 'Test top up');
INSERT INTO "transactions" VALUES (3, '2021-03-27 13:09:34.882', 2, 50.00, 'f', 'Test debit');
INSERT INTO "transactions" VALUES (4, '2021-03-27 11:25:14', 4, 100.00, 't', 'top up');
INSERT INTO "transactions" VALUES (5, '2021-03-27 12:32:59.809', 4, 100.00, 't', 'Test top up');
INSERT INTO "transactions" VALUES (6, '2021-03-27 13:09:34.882', 4, 50.00, 'f', 'Test debit');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "users";
CREATE TABLE "users" (
  "id" int4 NOT NULL,
  "login" varchar(45) COLLATE "pg_catalog"."default",
  "password" varchar(150) COLLATE "pg_catalog"."default",
  "first_name" varchar(45) COLLATE "pg_catalog"."default",
  "last_name" varchar(45) COLLATE "pg_catalog"."default",
  "surname" varchar(45) COLLATE "pg_catalog"."default",
  "roles_id" int4,
  "contact_details_id" int4,
  "blocked" bool
)
;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO "users" VALUES (1, 'admin', '$2a$10$n0Ffg189M9Xfsl6ZHfJs2eLjTAAjw4hB45RB7aUPy2qoyjHiwuI4q', 'Admin', 'Adminovich', 'Admin', 1, 1, 'f');
INSERT INTO "users" VALUES (2, 'stanislav', '$2a$10$E34J3McemX1Cni061Y3bSu7Wj2jM/obzimUmM4nuwK7eytJHlby8y', 'Stanislav', 'Stanislavovich', 'Stupak', 2, 1, 'f');
COMMIT;

-- ----------------------------
-- Primary Key structure for table accounts
-- ----------------------------
ALTER TABLE "accounts" ADD CONSTRAINT "accounts_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table contact_details
-- ----------------------------
ALTER TABLE "contact_details" ADD CONSTRAINT "contact_details_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table roles
-- ----------------------------
ALTER TABLE "roles" ADD CONSTRAINT "roles_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table transactions
-- ----------------------------
CREATE INDEX "user_id" ON "accounts" USING btree (
    "user_id" "pg_catalog"."int4_ops" ASC NULLS LAST
    );

-- ----------------------------
-- Indexes structure for table transactions
-- ----------------------------
CREATE INDEX "account_id" ON "transactions" USING btree (
  "account_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table transactions
-- ----------------------------
ALTER TABLE "transactions" ADD CONSTRAINT "transactions_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table users
-- ----------------------------
CREATE INDEX "fk_users_contact_details1_idx" ON "users" USING btree (
  "contact_details_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "fk_users_roles_idx" ON "users" USING btree (
  "roles_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "login_UNIQUE" ON "users" USING btree (
  "login" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table accounts
-- ----------------------------
ALTER TABLE "accounts" ADD CONSTRAINT "user_id_fk" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table transactions
-- ----------------------------
ALTER TABLE "transactions" ADD CONSTRAINT "account_id_fk" FOREIGN KEY ("account_id") REFERENCES "accounts" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
