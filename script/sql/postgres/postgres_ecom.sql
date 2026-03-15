
-- 整体约定  状态（0正常 1停用）  删除标志（0代表存在 1代表删除）




-- DROP TABLE ecom_question;
CREATE TABLE ecom_question (
    question_id  INT8 PRIMARY KEY,
    question_category_type  VARCHAR(10), -- 分类：J N3,N2,N1
    question_type VARCHAR(40) NOT NULL,   --小分类： 1单选 2多选 3判断 4填空 5简答
    title           VARCHAR(500) NOT NULL,        -- 题目标题（列表展示用）
    content         TEXT,                          -- 完整题目内容（可选） 题目内容（支持富文本，存储HTML或Markdown）
    difficulty      SMALLINT DEFAULT 2, -- 难度：1简单 2中等 3困难
    score           DECIMAL(4,2) DEFAULT 1.00 CHECK (score > 0),-- 分值（支持小数，如0.5分）
    analysis        TEXT,  -- 答案解析
    tags            VARCHAR(50)[], -- 知识点标签（数组类型，便于检索）
    options            VARCHAR(50)[], -- 选项（数组类型，便于检索）
    answer varchar(20);
    tenant_id     VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    del_flag      CHAR          DEFAULT '0'::BPCHAR,  -- （0存在 1删除）
    create_dept   INT8,
    create_by     INT8,
    create_time   TIMESTAMP,
    update_by     INT8,
    update_time   TIMESTAMP,
    remark        VARCHAR(200)  DEFAULT NULL::VARCHAR
);
COMMENT ON TABLE ecom_question IS '选择题问题';
COMMENT ON COLUMN ecom_question.question_category_type   IS '问题大分类';
COMMENT ON COLUMN ecom_question.question_type   IS '小分类';
COMMENT ON COLUMN ecom_question.title     IS '题目标题';
COMMENT ON COLUMN ecom_question.content IS '完整题目内容';
COMMENT ON COLUMN ecom_question.difficulty          IS '难度';
COMMENT ON COLUMN ecom_question.score    IS '分值';
COMMENT ON COLUMN ecom_question.analysis      IS '答案解析';
COMMENT ON COLUMN ecom_question.analysis      IS '答案解析';
COMMENT ON COLUMN ecom_question.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_question.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_question.create_by     IS '创建者';
COMMENT ON COLUMN ecom_question.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_question.update_by     IS '更新者';
COMMENT ON COLUMN ecom_question.update_time   IS '更新时间';
COMMENT ON COLUMN ecom_question.remark        IS '备注';



-- 新建国际地址表
-- DROP TABLE sys_addresses;
CREATE TABLE sys_addresses(
    address_id INT8 PRIMARY KEY,
    country_code CHAR(2) NOT NULL, -- ISO 3166-1 alpha-2
    administrative_area VARCHAR(50), -- 省/州
    locality VARCHAR(50), -- 城市
    dependent_locality VARCHAR(50), -- 区县
    street_detail VARCHAR(50), -- 街道类型
    postal_code VARCHAR(20), -- 邮编
    addresses_name      VARCHAR(50)   NOT NULL,
    phone     VARCHAR(20)   NOT NULL,  -- 电话
    other     VARCHAR(60)   NOT NULL,  -- 其他附加信息
    formatted_address VARCHAR(300) , -- 格式化地址
    lang VARCHAR(10) DEFAULT 'zh', -- 默认语言
    -- 经纬度坐标（WGS84标准）
    latitude DECIMAL(10, 8) ,  -- 纬度 (-90 to 90)
    longitude DECIMAL(11, 8) , -- 经度 (-180 to 180)
    -- 坐标系统标识
    coord_system VARCHAR(20) DEFAULT 'WGS84', -- 坐标系统类型
    accuracy_meters INT, -- 精度（米），可选
    tenant_id   varchar(20)  default '000000'::varchar,
    del_flag    char         default '0'::bpchar,
    create_dept int8,
    create_by   int8,
    create_time timestamp,
    update_by   int8,
    update_time timestamp,
    remark      varchar(100) default null::varchar
);
COMMENT ON TABLE sys_addresses IS '国际地址表';
comment on column sys_addresses.address_id      is '国际地址ID';
comment on column sys_addresses.country_code      is 'ISO国家代码';
comment on column sys_addresses.administrative_area      is '都道府县';
comment on column sys_addresses.locality      is '城市';
comment on column sys_addresses.dependent_locality      is '市/区/郡';
comment on column sys_addresses.street_detail      is '街道';
comment on column sys_addresses.postal_code      is '邮编';
comment on column sys_addresses.addresses_name      is '地址名';
comment on column sys_addresses.phone      is '电话';
comment on column sys_addresses.other      is '其他附加信息';
comment on column sys_addresses.formatted_address    is '格式化地址';
comment on column sys_addresses.lang    is '语言类型';
comment on column sys_addresses.latitude    is '纬度';
comment on column sys_addresses.longitude    is '经度';
comment on column sys_addresses.coord_system    is '坐标系统类型';
comment on column sys_addresses.accuracy_meters    is '精度';
comment on column sys_addresses.tenant_id    is '租户编号';
comment on column sys_addresses.del_flag     is '删除标志';
comment on column sys_addresses.create_dept  is '创建部门';
comment on column sys_addresses.create_by    is '创建者';
comment on column sys_addresses.create_time  is '创建时间';
comment on column sys_addresses.update_by    is '更新者';
comment on column sys_addresses.update_time  is '更新时间';
comment on column sys_addresses.remark       is '备注';

-- DROP TABLE sys_user_address;
-- 用户地址关联表  你可以只用联合主键的情况只有一个： “无业务字段、无生命周期、无被引用需求”
CREATE TABLE sys_user_address(
    user_address_id     INT8 PRIMARY KEY,
    user_id     INT8 NOT NULL,
    address_id  INT8 NOT NULL,
    is_default  CHAR(1) DEFAULT '1',-- 0是默认 1 非默认
    tag         VARCHAR(50),
    CONSTRAINT fk_user_address_rel_user_id FOREIGN KEY (user_id) REFERENCES sys_user(user_id),
    CONSTRAINT fk_user_address_rel_address_id FOREIGN KEY (address_id) REFERENCES sys_addresses(address_id)
);
COMMENT ON TABLE sys_user_address IS '用户地址关联表';
comment on column sys_user_address.user_address_id      is '用户地址id';
comment on column sys_user_address.user_id      is '用户id';
comment on column sys_user_address.address_id      is '地址ID';
comment on column sys_user_address.is_default    is '是否默认';
comment on column sys_user_address.tag      is '标签';

-- DROP TABLE sys_org;  -- 机构组织公司门店
CREATE TABLE sys_org (
    org_id                  INT8 PRIMARY KEY,
    org_code                VARCHAR(25),
    org_full_code           VARCHAR(100),
    org_name                VARCHAR(64)             NOT NULL,
    org_type                    VARCHAR(32)             NOT NULL DEFAULT 'STORE', -- 门店STORE，商贸公司ORG，客户公司,经销商
    capability_type           VARCHAR(50)            NOT NULL, -- delivery / dine_in / service / self_pick  外卖 自提 堂食 服务
    principal               VARCHAR(64)             NOT NULL,
    phone                   VARCHAR(32)             NOT NULL,
    email                   VARCHAR(40)             DEFAULT '',
    logo                    INT8,
    description             VARCHAR(1024),
    business_hours VARCHAR(50) DEFAULT '09:00-22:00', -- 营业时间
    delivery_radius INT4 DEFAULT 5000, -- 配送半径（米）
    org_status VARCHAR(20) NOT NULL DEFAULT 'Active', -- 生命周期状态（必须）
    operation_status VARCHAR(20) DEFAULT 'Open', -- 运营状态（门店/服务特有）
    parent_id               INT8                  NOT NULL DEFAULT -1,
    user_id                 INT8                  NOT NULL,
    username                VARCHAR(64)             NOT NULL,
    formatted_address VARCHAR(300) , -- 格式化地址
    latitude DECIMAL(10, 8) ,  -- 纬度 (-90 to 90)
    longitude DECIMAL(11, 8) , -- 经度 (-180 to 180)
    version                 INTEGER                 NOT NULL DEFAULT 0,
    address_id INT8 ,
    tenant_id     VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    del_flag      CHAR          DEFAULT '0'::BPCHAR,  -- （0存在 1删除）
    create_dept   INT8,
    create_by     INT8,
    create_time   TIMESTAMP,
    update_by     INT8,
    update_time   TIMESTAMP,
    remark        VARCHAR(200)  DEFAULT NULL::VARCHAR,
    CONSTRAINT fk_org_address FOREIGN KEY (address_id) REFERENCES sys_addresses(address_id)
);

COMMENT ON TABLE sys_org IS '机构组织公司';
COMMENT ON COLUMN sys_org.org_id IS '机构组织公司ID';
COMMENT ON COLUMN sys_org.org_code IS '机构组织公司编码';
COMMENT ON COLUMN sys_org.org_full_code IS '机构组织公司完整编码';
COMMENT ON COLUMN sys_org.org_name IS '机构组织公司名称';
COMMENT ON COLUMN sys_org.org_type IS '机构组织公司类型';
COMMENT ON COLUMN sys_org.capability_type IS '业务类型';
COMMENT ON COLUMN sys_org.principal IS '负责人';
COMMENT ON COLUMN sys_org.phone IS '联系电话';
COMMENT ON COLUMN sys_org.email IS '邮箱';
COMMENT ON COLUMN sys_org.logo IS 'LOGO';
COMMENT ON COLUMN sys_org.description IS '简介';
COMMENT ON COLUMN sys_org.business_hours IS '营业时间';
COMMENT ON COLUMN sys_org.delivery_radius IS '配送范围';
COMMENT ON COLUMN sys_org.org_status IS '主体状态';
COMMENT ON COLUMN sys_org.operation_status IS '营业状态';
COMMENT ON COLUMN sys_org.parent_id IS '父级机构';
COMMENT ON COLUMN sys_org.user_id IS '管理员用户ID';
COMMENT ON COLUMN sys_org.username IS '管理员用户名';
comment on column sys_org.formatted_address    is '格式化地址';
comment on column sys_org.latitude    is '纬度';
comment on column sys_org.longitude    is '经度';
COMMENT ON COLUMN sys_org.version IS '版本号';
COMMENT ON COLUMN sys_org.address_id IS '国际化地址';
COMMENT ON COLUMN sys_org.tenant_id     IS '租户编号';
COMMENT ON COLUMN sys_org.del_flag      IS '删除标志';
COMMENT ON COLUMN sys_org.create_dept   IS '创建部门';
COMMENT ON COLUMN sys_org.create_by     IS '创建者';
COMMENT ON COLUMN sys_org.create_time   IS '创建时间';
COMMENT ON COLUMN sys_org.update_by     IS '更新者';
COMMENT ON COLUMN sys_org.update_time   IS '更新时间';
COMMENT ON COLUMN sys_org.remark        IS '备注';

-- 超级通用国际化表 所有业务对象都能挂上来 减少表数量 语言切换时统一处理  弱约束通用表 一表不能通吃  ： 活动文案 、配置项名称、字典扩展说明、CMS 自定义内容
-- DROP TABLE ecom_i18n;
CREATE TABLE ecom_i18n (
    i18n_id  INT8 PRIMARY KEY,
    biz_type VARCHAR(40) NOT NULL,   --表名： store / product / dict / cms
    biz_id   INT8 NOT NULL, --表名：id
    lang     VARCHAR(10) NOT NULL, --表名：zh，en，
    field    VARCHAR(50) NOT NULL,   --表字段： name / desc / title
    content  TEXT NOT NULL,
    tenant_id     VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    del_flag      CHAR          DEFAULT '0'::BPCHAR,  -- （0存在 1删除）
    create_dept   INT8,
    create_by     INT8,
    create_time   TIMESTAMP,
    update_by     INT8,
    update_time   TIMESTAMP,
    remark        VARCHAR(200)  DEFAULT NULL::VARCHAR,
    CONSTRAINT unique_biz_lang_field UNIQUE (biz_type, biz_id, lang, field)  -- 缺点 biz_type, biz_id + lang 联合索引极难调优
);
COMMENT ON TABLE ecom_i18n IS '超级通用国际化表';
COMMENT ON COLUMN ecom_i18n.i18n_id   IS '通用国际化ID';
COMMENT ON COLUMN ecom_i18n.biz_type   IS '业务类型';
COMMENT ON COLUMN ecom_i18n.biz_id     IS '业务id';
COMMENT ON COLUMN ecom_i18n.lang IS '语言类型';
COMMENT ON COLUMN ecom_i18n.field          IS '字段';
COMMENT ON COLUMN ecom_i18n.content    IS '翻译内容';
COMMENT ON COLUMN ecom_i18n.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_i18n.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_i18n.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_i18n.create_by     IS '创建者';
COMMENT ON COLUMN ecom_i18n.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_i18n.update_by     IS '更新者';
COMMENT ON COLUMN ecom_i18n.update_time   IS '更新时间';
COMMENT ON COLUMN ecom_i18n.remark        IS '备注';

-- ==================== 商品管理模块 ====================
-- DROP TABLE ecom_brand;
CREATE TABLE ecom_brand (
    brand_id        INT8 PRIMARY KEY,
    brand_code      VARCHAR(50) ,      -- 内部唯一编码
    brand_logo      VARCHAR(255),               -- logo URL
    official_site   VARCHAR(255),               -- 官网
    country_code    CHAR(2),                     -- 品牌所属国家（ISO 3166-1）
    status          CHAR DEFAULT '0',            -- （0正常 1停用）
    sort_order      INT4 DEFAULT 0,
    tenant_id       VARCHAR(20) DEFAULT '000000',
    del_flag        CHAR DEFAULT '0',
    create_dept     INT8,
    create_by       INT8,
    create_time     TIMESTAMP,
    update_by       INT8,
    update_time     TIMESTAMP,
    remark          VARCHAR(500),
    CONSTRAINT uk_brand_code UNIQUE (tenant_id, brand_code)
);
COMMENT ON TABLE ecom_brand IS '商品品牌表';
COMMENT ON COLUMN ecom_brand.brand_id     IS '品牌ID';
COMMENT ON COLUMN ecom_brand.brand_code IS '品牌编码';
COMMENT ON COLUMN ecom_brand.brand_logo IS '图标';
COMMENT ON COLUMN ecom_brand.official_site          IS '官网';
COMMENT ON COLUMN ecom_brand.country_code          IS '品牌所属国家';
COMMENT ON COLUMN ecom_brand.status        IS '状态';
COMMENT ON COLUMN ecom_brand.sort_order    IS '排序权重';
COMMENT ON COLUMN ecom_brand.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_brand.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_brand.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_brand.create_by     IS '创建者';
COMMENT ON COLUMN ecom_brand.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_brand.update_by     IS '更新者';
COMMENT ON COLUMN ecom_brand.update_time   IS '更新时间';
COMMENT ON COLUMN ecom_brand.remark        IS '备注';
-- 商品分类表（保留原结构，支持多级分类）
-- DROP TABLE ecom_product_category;
CREATE TABLE IF NOT EXISTS ecom_product_category (
    category_id   INT8 PRIMARY KEY,
    tenant_id     VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    parent_id     INT8          DEFAULT 0,   -- （0为一级分类）
    category_name VARCHAR(40)  NOT NULL,
    category_code VARCHAR(20)  DEFAULT ''::VARCHAR,
    category_code_path     VARCHAR(100) , -- '路径如：1/2/3',
    icon          VARCHAR(255)  DEFAULT ''::VARCHAR,
    rule_template    JSONB DEFAULT '{}'::JSONB,  --  业务规则界定字段  product_type实物/虚拟/票务是否需要物流 has_expiry_date 关联有效期（如食品、卡密）support_return支持7天无理由（可作为默认值）
    spec_template    JSONB DEFAULT '{}'::JSONB,  --  规格模板定义 颜色，尺寸，
    delivery_template      JSONB DEFAULT '{}'::JSONB,  -- 运费模板ID
    attribute_schema      JSONB DEFAULT '{}'::JSONB,  -- 扩展属性 schema
    special_control      JSONB DEFAULT '{}'::JSONB,  -- 特殊管控规则5
COMMENT ON COLUMN ecom_product_category   IS '商品分类表';
COMMENT ON COLUMN ecom_product_category.category_id   IS '分类ID';
COMMENT ON COLUMN ecom_product_category.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_product_category.parent_id     IS '父分类ID';
COMMENT ON COLUMN ecom_product_category.category_name IS '分类名称';
COMMENT ON COLUMN ecom_product_category.category_code IS '分类编码';
COMMENT ON COLUMN ecom_product_category.category_code_path IS '编码路径';
COMMENT ON COLUMN ecom_product_category.icon          IS '分类图标';
COMMENT ON COLUMN ecom_product_category.rule_template          IS '业务规则';
COMMENT ON COLUMN ecom_product_category.spec_template          IS '规格模板';
COMMENT ON COLUMN ecom_product_category.delivery_template          IS '运费模板ID';
COMMENT ON COLUMN ecom_product_category.attribute_schema          IS '扩展属性';
COMMENT ON COLUMN ecom_product_category.special_control          IS '特殊管控规则';
COMMENT ON COLUMN ecom_product_category.sort_order    IS '排序权重';
COMMENT ON COLUMN ecom_product_category.status        IS '状态';
COMMENT ON COLUMN ecom_product_category.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_product_category.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_product_category.create_by     IS '创建者';
COMMENT ON COLUMN ecom_product_category.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_product_category.update_by     IS '更新者';
COMMENT ON COLUMN ecom_product_category.update_time   IS '更新时间';
COMMENT ON COLUMN ecom_product_category.remark        IS '备注';


-- SPU标准产品单元表（原ecom_product升级）
-- DROP TABLE ecom_product_spu;
CREATE TABLE IF NOT EXISTS ecom_product_spu (
    spu_id          INT8 PRIMARY KEY,
    tenant_id       VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    category_id     INT8          NOT NULL,  --  按照类别定义不同的模板类及对应的模板规则
    brand_id        INT8          DEFAULT 0,
    owner_type VARCHAR(20),  --商品所属权： store 门店/ leader团长 /user卖家/ platform平台
    owner_id   INT8,
    product_type VARCHAR(20) DEFAULT 'physical', -- 业务类型  ：physical（实物）virtual（虚拟卡密）ticket（电子票券）food_delivery（外卖）service（到店服务，如按摩、美发）course（在线课程）travel（景区门票/旅游项目）seckill（秒杀商品）服务 / 二手
    spu_name        VARCHAR(50)  NOT NULL,
    spu_desc        TEXT,
    packing_list    VARCHAR(1000),
    after_service   VARCHAR(1000),
    generic_spec    JSONB DEFAULT '{}'::JSONB,
    spu_images      JSONB DEFAULT '{}'::JSONB,
    min_price INT4 DEFAULT 0,
    max_price INT4 DEFAULT 0,
    total_sales INT4 DEFAULT 0,
    total_stock INT4 DEFAULT 0,
    status          CHAR          DEFAULT '0'::BPCHAR,  -- （0在售 1下架 2预售）
    audit_status    CHAR          DEFAULT '0'::BPCHAR,  -- （0待审核 1通过 2拒绝）
    del_flag        CHAR          DEFAULT '0'::BPCHAR,  -- （0存在 1删除）
    create_dept     INT8,
    create_by       INT8,
    create_time     TIMESTAMP,
    update_by       INT8,
    update_time     TIMESTAMP,
    remark          VARCHAR(100)  DEFAULT NULL::VARCHAR,
    CONSTRAINT fk_spu_category FOREIGN KEY (category_id) REFERENCES ecom_product_category(category_id)
);

COMMENT ON TABLE ecom_product_spu IS 'SPU标准产品单元表';
COMMENT ON COLUMN ecom_product_spu.spu_id          IS 'SPU ID';
COMMENT ON COLUMN ecom_product_spu.tenant_id       IS '租户编号';
COMMENT ON COLUMN ecom_product_spu.category_id     IS '分类ID';
COMMENT ON COLUMN ecom_product_spu.brand_id        IS '品牌ID';
COMMENT ON COLUMN ecom_product_spu.owner_type              IS '所属类型';
COMMENT ON COLUMN ecom_product_spu.owner_id              IS '所属ID';
COMMENT ON COLUMN ecom_product_spu.product_type     IS '产品业务类型';
COMMENT ON COLUMN ecom_product_spu.spu_name        IS 'SPU名称';
COMMENT ON COLUMN ecom_product_spu.spu_desc        IS 'SPU描述';
COMMENT ON COLUMN ecom_product_spu.packing_list      IS '商品打包信息';
COMMENT ON COLUMN ecom_product_spu.after_service      IS '售后服务';
COMMENT ON COLUMN ecom_product_spu.generic_spec      IS '规格说明';
COMMENT ON COLUMN ecom_product_spu.spu_images      IS 'SPU图片JSON';
COMMENT ON COLUMN ecom_product_spu.min_price        IS '最低价格';
COMMENT ON COLUMN ecom_product_spu.max_price        IS '最高价格';
COMMENT ON COLUMN ecom_product_spu.total_sales        IS '总销量';
COMMENT ON COLUMN ecom_product_spu.total_stock        IS '总库存';
COMMENT ON COLUMN ecom_product_spu.status          IS '状态';
COMMENT ON COLUMN ecom_product_spu.audit_status    IS '审核状态';
COMMENT ON COLUMN ecom_product_spu.del_flag        IS '删除标志';
COMMENT ON COLUMN ecom_product_spu.create_dept     IS '创建部门';
COMMENT ON COLUMN ecom_product_spu.create_by       IS '创建者';
COMMENT ON COLUMN ecom_product_spu.create_time     IS '创建时间';
COMMENT ON COLUMN ecom_product_spu.update_by       IS '更新者';
COMMENT ON COLUMN ecom_product_spu.update_time     IS '更新时间';
COMMENT ON COLUMN ecom_product_spu.remark          IS '备注';

-- SKU库存单元表（新增）采用EAV（实体-属性-值）模型与结构化字段相结合的方式，对高频属性保留结构化字段，低频属性使用EAV表  。例如，外卖SKU的规格可包含"配送时间"、"包装类型"等结构化字段，而门票SKU的规格可包含"日期"、"场次"等JSONB字段。
-- DROP TABLE ecom_product_sku;
CREATE TABLE IF NOT EXISTS ecom_product_sku (
    sku_id         INT8 PRIMARY KEY,
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    spu_id         INT8          NOT NULL,
    sku_name       VARCHAR(50)  NOT NULL,
    sku_spec       JSONB         DEFAULT '{}'::JSONB, -- 规格JSON：{"颜色":"红色","尺寸":"XL"}
    cost_price INT4 DEFAULT 0,  -- 是企业取得商品或存货所支付的实际价值，企业的秘密 利润 = price - cost_price
    market_price    INT4 DEFAULT 0,  -- 是参考价，可比的参照物的价格，原价
    price    INT4 DEFAULT 0,  -- 企业对外的定价，是收入来源
    -- stock_policy_type FINITE 有限库存（普通实物商品）/ INFINITE 无限库存（虚拟商品 / 纯数字） / PRESALE 预售库存（可超卖，延迟扣减） / NO_STOCK 不走库存（到店服务 / 预约）
    -- stock_type PHYSICAL      实物库存（仓库 / 门店） | VIRTUAL  虚拟库存（卡密 / 兑换码） |  QUOTA  配额库存（次数 / 人次） | TIME_SLOT    时间段库存（预约）预约 10:00  |  CAPACITY 产能库存（骑手 / 师傅 / 工位） 外卖骑手
    -- SKU 不拥有库存，只定义库存规则 （只定义「是否需要库存 & 库存策略」）   库存stock拥有 SKU，并定义库存形态
    stock_policy_type VARCHAR(20) DEFAULT 'finite', -- finite: 有限库存 infinite: 无限库存（虚拟商品/票券）daily_limit: 每日限售 / presale 预售库存/ no_stock11111  finite / infinite / presale / no_stock
    stock_quantity INT4          DEFAULT 0,  -- 结果缓存，不是事实来源 定时同步（或缓存刷新）
    sold_quantity  INT4          DEFAULT 0,
    weight        INT8          DEFAULT 0,  -- 单位：克g,用于运费计算
    length INT8, -- 长，单位：厘米
    width  INT8, -- 宽，单位：厘米
    height INT8, -- 高，单位：厘米
    bar_code          VARCHAR(50) ,            -- 唯一编码，用于扫码
    sku_code          VARCHAR(50) ,            -- 唯一编码
    status         CHAR          DEFAULT '0'::BPCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP,
    remark         VARCHAR(100)  DEFAULT NULL::VARCHAR,
    CONSTRAINT fk_sku_spu FOREIGN KEY (spu_id) REFERENCES ecom_product_spu(spu_id)
);

COMMENT ON TABLE ecom_product_sku IS 'SKU库存单元表';
COMMENT ON COLUMN ecom_product_sku.sku_id         IS 'SKU ID';
COMMENT ON COLUMN ecom_product_sku.tenant_id      IS '租户编号';
COMMENT ON COLUMN ecom_product_sku.spu_id         IS 'SPU ID';
COMMENT ON COLUMN ecom_product_sku.sku_name       IS 'SKU名称';
COMMENT ON COLUMN ecom_product_sku.sku_spec       IS '规格JSON';
COMMENT ON COLUMN ecom_product_sku.cost_price       IS '成本价格';
COMMENT ON COLUMN ecom_product_sku.market_price IS '市场价';
COMMENT ON COLUMN ecom_product_sku.price    IS '销售价';
COMMENT ON COLUMN ecom_product_sku.stock_policy_type    IS '库存策略类型';
COMMENT ON COLUMN ecom_product_sku.stock_quantity IS '单品销量';
COMMENT ON COLUMN ecom_product_sku.sold_quantity  IS '已售数量';
COMMENT ON COLUMN ecom_product_sku.weight  IS '重量';
COMMENT ON COLUMN ecom_product_sku.length    IS '长';
COMMENT ON COLUMN ecom_product_sku.width  IS '宽';
COMMENT ON COLUMN ecom_product_sku.height  IS '高';
COMMENT ON COLUMN ecom_product_sku.bar_code  IS '二维码';
COMMENT ON COLUMN ecom_product_sku.sku_code  IS '编码';
COMMENT ON COLUMN ecom_product_sku.status         IS '状态';
COMMENT ON COLUMN ecom_product_sku.del_flag       IS '删除标志';
COMMENT ON COLUMN ecom_product_sku.create_dept    IS '创建部门';
COMMENT ON COLUMN ecom_product_sku.create_by      IS '创建者';
COMMENT ON COLUMN ecom_product_sku.create_time    IS '创建时间';
COMMENT ON COLUMN ecom_product_sku.update_by      IS '更新者';
COMMENT ON COLUMN ecom_product_sku.update_time    IS '更新时间';
COMMENT ON COLUMN ecom_product_sku.remark         IS '备注';


-- 商品SKU关联表（对sku_spec 规格的结构化扩展）
CREATE TABLE IF NOT EXISTS ecom_product_sku_attributes (
    sku_attr_id     INT8 PRIMARY KEY,
    sku_id INT8 NOT NULL,
    attr_key    VARCHAR(50) NOT NULL,
    attr_value  VARCHAR(100) NOT NULL,
    attr_type   VARCHAR(50) DEFAULT 'string'::VARCHAR,
    tenant_id   varchar(20)  default '000000'::varchar,
    del_flag    char         default '0'::bpchar,
    create_dept int8,
    create_by   int8,
    create_time timestamp,
    update_by   int8,
    update_time timestamp,
    CONSTRAINT fk_sku_id FOREIGN KEY (sku_id) REFERENCES ecom_product_sku(sku_id)
);
COMMENT ON TABLE ecom_product_sku_attributes IS '商品SKU属性表';
COMMENT ON COLUMN ecom_product_sku_attributes.sku_attr_id IS '属性ID (主键)';
COMMENT ON COLUMN ecom_product_sku_attributes.sku_id IS '商品sku ID';
COMMENT ON COLUMN ecom_product_sku_attributes.attr_key IS '属性键';-- color,size
COMMENT ON COLUMN ecom_product_sku_attributes.attr_value IS '属性值';
COMMENT ON COLUMN ecom_product_sku_attributes.attr_type IS '属性类型:String,int,list';
comment on column ecom_product_sku_attributes.tenant_id    is '租户编号';
comment on column ecom_product_sku_attributes.del_flag     is '删除标志';
comment on column ecom_product_sku_attributes.create_dept  is '创建部门';
comment on column ecom_product_sku_attributes.create_by    is '创建者';
comment on column ecom_product_sku_attributes.create_time  is '创建时间';
comment on column ecom_product_sku_attributes.update_by    is '更新者';
comment on column ecom_product_sku_attributes.update_time  is '更新时间';


-- 活动表（优化：支持多商品）
-- DROP TABLE ecom_activity;
CREATE TABLE IF NOT EXISTS ecom_activity (
    activity_id       INT8 PRIMARY KEY,
    tenant_id         VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    activity_name     VARCHAR(50)  NOT NULL,
    activity_type     VARCHAR(20)   DEFAULT 'group'::VARCHAR,  -- ：group（拼团）seckill（秒杀）discount（折扣）full_reduction（满减）coupon（优惠券活动）flash_sale（限时抢购）
    cover_image       VARCHAR(100)  DEFAULT ''::VARCHAR,
    activity_image    JSONB DEFAULT '{}'::JSONB,
    share_title       VARCHAR(200)  DEFAULT ''::VARCHAR,
    share_description VARCHAR(200)  DEFAULT ''::VARCHAR,
    start_time        TIMESTAMP,
    end_time          TIMESTAMP,
    success_count     INT4          DEFAULT 0,
    status            CHAR          DEFAULT '0'::BPCHAR,  -- （0未开始 1进行中 2已结束）
    audit_status      CHAR          DEFAULT '0'::BPCHAR,  -- （0待审核 1通过 2拒绝）
    del_flag          CHAR          DEFAULT '0'::BPCHAR,
    create_dept       INT8,
    create_by         INT8,
    create_time       TIMESTAMP,
    update_by         INT8,
    update_time       TIMESTAMP,
    remark            VARCHAR(100)  DEFAULT NULL::VARCHAR
);

COMMENT ON TABLE ecom_activity IS '活动表';
COMMENT ON COLUMN ecom_activity.activity_id       IS '活动ID';
COMMENT ON COLUMN ecom_activity.tenant_id         IS '租户编号';
COMMENT ON COLUMN ecom_activity.activity_name     IS '活动名称';
COMMENT ON COLUMN ecom_activity.activity_type     IS '活动类型';
COMMENT ON COLUMN ecom_activity.cover_image       IS '活动封面图';
COMMENT ON COLUMN ecom_activity.activity_image       IS '活动详情图';
COMMENT ON COLUMN ecom_activity.share_title       IS '分享标题';
COMMENT ON COLUMN ecom_activity.share_description IS '分享描述';
COMMENT ON COLUMN ecom_activity.start_time        IS '开始时间';
COMMENT ON COLUMN ecom_activity.end_time          IS '结束时间';
COMMENT ON COLUMN ecom_activity.success_count     IS '成功团购次数';
COMMENT ON COLUMN ecom_activity.status            IS '活动状态';
COMMENT ON COLUMN ecom_activity.audit_status      IS '审核状态';
COMMENT ON COLUMN ecom_activity.del_flag          IS '删除标志';
COMMENT ON COLUMN ecom_activity.create_dept       IS '创建部门';
COMMENT ON COLUMN ecom_activity.create_by         IS '创建者';
COMMENT ON COLUMN ecom_activity.create_time       IS '创建时间';
COMMENT ON COLUMN ecom_activity.update_by         IS '更新者';
COMMENT ON COLUMN ecom_activity.update_time       IS '更新时间';
COMMENT ON COLUMN ecom_activity.remark            IS '备注';

-- 如果未来需要增加新的活动类型，如“满减活动”或“折扣券活动”，这些活动可能不需要“成团人数”或“活动库存”等字段，但这些字段依然会存在于表中
-- 活动商品关联表（新增：实现多对多关系+快照）
CREATE TABLE IF NOT EXISTS ecom_activity_attributes (
    attr_id     INT8 PRIMARY KEY,
    activity_id INT8 NOT NULL,
    attr_key    VARCHAR(50) NOT NULL,
    attr_value  VARCHAR(100) NOT NULL,
    attr_type   VARCHAR(50) DEFAULT 'string'::VARCHAR,  -- String,int,list
    tenant_id   varchar(20)  default '000000'::varchar,
    del_flag    char         default '0'::bpchar,
    create_dept int8,
    create_by   int8,
    create_time timestamp,
    update_by   int8,
    update_time timestamp,
    CONSTRAINT fk_attr_activity FOREIGN KEY (activity_id) REFERENCES ecom_activity(activity_id)
);
COMMENT ON TABLE ecom_activity_attributes IS '活动属性表';
COMMENT ON COLUMN ecom_activity_attributes.attr_id IS '属性ID';
COMMENT ON COLUMN ecom_activity_attributes.activity_id IS '活动ID';
COMMENT ON COLUMN ecom_activity_attributes.attr_key IS '属性键';
COMMENT ON COLUMN ecom_activity_attributes.attr_value IS '属性值';
COMMENT ON COLUMN ecom_activity_attributes.attr_type IS '属性类型';
COMMENT ON COLUMN ecom_activity_attributes.tenant_id IS '租户编号';
comment on column ecom_activity_attributes.del_flag     is '删除标志';
comment on column ecom_activity_attributes.create_dept  is '创建部门';
comment on column ecom_activity_attributes.create_by    is '创建者';
comment on column ecom_activity_attributes.create_time  is '创建时间';
comment on column ecom_activity_attributes.update_by    is '更新者';
comment on column ecom_activity_attributes.update_time  is '更新时间';

-- 活动商品关联表（新增：实现多对多关系+快照）  某个活动（销售策略）下，某个 SKU（商品资产） 的一次“销售配置”（销售规则快照 + 履约预案）  订单是 履约 + 财务事实
-- DROP TABLE ecom_product_activity;
CREATE TABLE IF NOT EXISTS ecom_product_activity (
    activity_product_id INT8 PRIMARY KEY,
    tenant_id           VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    activity_id         INT8          NOT NULL,
    spu_id              INT8          NOT NULL,
    sku_id              INT8          NOT NULL,
    owner_type VARCHAR(20),  -- 经营归属（谁收钱） 收入 store 门店/ leader团长 /user商家/ platform平台/warehouse 仓库/warehouse 仓库/merchant 商家/franchisee  加盟商
    owner_id   INT8,
    fulfillment_type VARCHAR(20),  --履约执行（谁发货）发货配送   'store_delivery','store_pickup','warehouse_delivery','merchant_delivery','platform_delivery', 'third_party_logistics',/'leader_distribution'
    fulfillment_id    INT8,
    activity_title      VARCHAR(100)  NOT NULL,
    activity_price         INT4 DEFAULT 0,
    activity_stock      INT4          DEFAULT 0,  -- 活动配额库存，不是物理库存  该活动最多允许卖多少  活动库存 = 虚拟隔离层。活动开始：锁定一部分真实库存 活动售卖：消耗活动库存 活动结束：未卖完 → 释放回真实库存
    min_group_size      INT4          DEFAULT 2,
    max_group_size      INT4          DEFAULT 999,
    limit_per_user      INT4          DEFAULT 999,
    status              CHAR          DEFAULT '0'::BPCHAR,
    audit_status        CHAR          DEFAULT '0'::BPCHAR,
    del_flag            CHAR          DEFAULT '0'::BPCHAR,
    create_dept         INT8,
    create_by           INT8,
    create_time         TIMESTAMP,
    update_by           INT8,
    update_time         TIMESTAMP,
    remark              VARCHAR(200)  DEFAULT NULL::VARCHAR,
    CONSTRAINT fk_act_product_activity  FOREIGN KEY (activity_id) REFERENCES ecom_activity(activity_id),
    CONSTRAINT fk_act_product_spu       FOREIGN KEY (spu_id)      REFERENCES ecom_product_spu(spu_id),
    CONSTRAINT fk_act_product_sku       FOREIGN KEY (sku_id)      REFERENCES ecom_product_sku(sku_id)
);

COMMENT ON TABLE ecom_product_activity IS '活动商品关联表';
COMMENT ON COLUMN ecom_product_activity.activity_product_id IS '关联ID';
COMMENT ON COLUMN ecom_product_activity.tenant_id           IS '租户编号';
COMMENT ON COLUMN ecom_product_activity.activity_id         IS '活动ID';
COMMENT ON COLUMN ecom_product_activity.spu_id              IS 'SPU ID';
COMMENT ON COLUMN ecom_product_activity.sku_id              IS 'SKU ID';
COMMENT ON COLUMN ecom_product_activity.owner_type              IS '活动所属类型';
COMMENT ON COLUMN ecom_product_activity.owner_id              IS '活动所属ID';
COMMENT ON COLUMN ecom_product_activity.fulfillment_type              IS '履约类型';
COMMENT ON COLUMN ecom_product_activity.fulfillment_id              IS '履约ID';
COMMENT ON COLUMN ecom_product_activity.activity_title      IS '活动商品标题';
COMMENT ON COLUMN ecom_product_activity.activity_price         IS '活动价格';
COMMENT ON COLUMN ecom_product_activity.activity_stock      IS '活动配额库存';
COMMENT ON COLUMN ecom_product_activity.min_group_size      IS '最小成团人数';
COMMENT ON COLUMN ecom_product_activity.max_group_size      IS '最大成团人数';
COMMENT ON COLUMN ecom_product_activity.limit_per_user      IS '每人限购数量';
COMMENT ON COLUMN ecom_product_activity.status              IS '状态';
COMMENT ON COLUMN ecom_product_activity.audit_status        IS '审核状态';
COMMENT ON COLUMN ecom_product_activity.del_flag            IS '删除标志';
COMMENT ON COLUMN ecom_product_activity.create_dept         IS '创建部门';
COMMENT ON COLUMN ecom_product_activity.create_by           IS '创建者';
COMMENT ON COLUMN ecom_product_activity.create_time         IS '创建时间';
COMMENT ON COLUMN ecom_product_activity.update_by           IS '更新者';
COMMENT ON COLUMN ecom_product_activity.update_time         IS '更新时间';
COMMENT ON COLUMN ecom_product_activity.remark              IS '备注';

-- ==================== 拼团模块 ====================

-- 团购参团记录表（优化：保留作为团单头）
CREATE TABLE IF NOT EXISTS ecom_group_record (
    record_id      INT8 PRIMARY KEY,
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    activity_product_id INT8     NOT NULL,
    leader_id      INT8          NOT NULL,
    current_count  INT4          DEFAULT 1,
    target_count   INT4          NOT NULL,
    group_status   CHAR          DEFAULT '0'::BPCHAR,
    expire_time    TIMESTAMP,
    version INT4 DEFAULT 0,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP,
    remark         VARCHAR(500)  DEFAULT NULL::VARCHAR,
    CONSTRAINT fk_group_activity_product FOREIGN KEY (activity_product_id) REFERENCES ecom_product_activity(activity_product_id),
    CONSTRAINT fk_group_leader          FOREIGN KEY (leader_id)           REFERENCES sys_user(user_id)
);
--UPDATE ecom_group_record
--SET current_count = current_count + 1, version = version + 1
--WHERE record_id = ? AND version = ?

COMMENT ON TABLE ecom_group_record IS '团购参团记录表';
COMMENT ON COLUMN ecom_group_record.record_id          IS '参团记录ID';
COMMENT ON COLUMN ecom_group_record.tenant_id          IS '租户编号';
COMMENT ON COLUMN ecom_group_record.activity_product_id IS '活动商品ID';
COMMENT ON COLUMN ecom_group_record.leader_id          IS '团长用户ID责任人';
COMMENT ON COLUMN ecom_group_record.current_count      IS '当前参团人数';
COMMENT ON COLUMN ecom_group_record.target_count       IS '目标成团人数';
COMMENT ON COLUMN ecom_group_record.group_status       IS '成团状态';
COMMENT ON COLUMN ecom_group_record.expire_time        IS '成团截止时间';
COMMENT ON COLUMN ecom_group_record.version        IS '乐观版本';
COMMENT ON COLUMN ecom_group_record.del_flag           IS '删除标志';
COMMENT ON COLUMN ecom_group_record.create_dept        IS '创建部门';
COMMENT ON COLUMN ecom_group_record.create_by          IS '创建者';
COMMENT ON COLUMN ecom_group_record.create_time        IS '创建时间';
COMMENT ON COLUMN ecom_group_record.update_by          IS '更新者';
COMMENT ON COLUMN ecom_group_record.update_time        IS '更新时间';
COMMENT ON COLUMN ecom_group_record.remark             IS '备注';



-- ==================== 订单与物流模块 ====================

--订单 = 交易结果订单： 谁买的  买了什么 在哪个门店完成  用什么履约方式：怎么交付 / 在哪里完成
-- 订单表 一切的核心 销售结果
-- DROP TABLE ecom_order;
CREATE TABLE IF NOT EXISTS ecom_order (
    order_id           INT8 PRIMARY KEY,
    tenant_id          VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    order_sn           VARCHAR(64)   NOT NULL UNIQUE,
    buyer_user_id            INT8          NOT NULL,
    record_id    INT8,
    parent_order_id    INT8,
    org_id     INT8,
    leader_id     INT8,
    seller_user_id     INT8,
    product_type VARCHAR(20) DEFAULT 'physical', -- 'normal'普通 | 'food_delivery'外卖 | 'ticket'门票 | 'service'服务
    extend_json JSONB,   -- (用于存储不同业务比如下单时用户主要信息等的特殊字段)

    activity_id        INT8          NOT NULL,
    activity_product_id INT8         NOT NULL,
    owner_type VARCHAR(20),  -- 经营归属（谁收钱） 收入 store 门店/ leader团长 /user商家/ platform平台/warehouse 仓库/warehouse 仓库/merchant 商家/franchisee  加盟商
    owner_id   INT8,
    fulfillment_type VARCHAR(20),  --履约执行（谁发货）发货配送   'store_delivery','store_pickup','warehouse_delivery','merchant_delivery','platform_delivery', 'third_party_logistics',/'leader_distribution'
    fulfillment_id    INT8,
    responsible_type VARCHAR(20),  --法律责任主体    'platform_company', 'merchant_company', 'franchisee_company'
    responsible_id    INT8,
    currency            CHAR(3) DEFAULT 'JPY',
    locale              VARCHAR(10) DEFAULT 'ja_JP',

    order_status       CHAR          DEFAULT '0'::BPCHAR,
    pay_status         CHAR          DEFAULT '0'::BPCHAR,
    pay_amount         INT4 DEFAULT 0,
    pay_time           TIMESTAMP,
    pay_way            VARCHAR(20)   DEFAULT ''::VARCHAR,
    buyer_message      VARCHAR(500)  DEFAULT ''::VARCHAR,
    auto_confirm_days  INT4          DEFAULT 7,
    confirm_time       TIMESTAMP,
    country_code CHAR(2) NOT NULL, -- ISO 3166-1 alpha-2
    administrative_area VARCHAR(50), -- 省/州
    locality VARCHAR(50), -- 城市
    dependent_locality VARCHAR(50), -- 区县
    street_detail VARCHAR(50), -- 街道类型
    postal_code VARCHAR(20), -- 邮编
    addresses_name      VARCHAR(50)   NOT NULL,
    phone     VARCHAR(20)   NOT NULL,  -- 电话
    other     VARCHAR(60)   NOT NULL,  -- 其他附加信息
    formatted_address VARCHAR(300) , -- 格式化地址
    lang VARCHAR(10) DEFAULT 'zh', -- 默认语言
    latitude DECIMAL(10, 8) ,  -- 纬度 (-90 to 90)
    longitude DECIMAL(11, 8) , -- 经度 (-180 to 180)

    refund_status      CHAR          DEFAULT '0'::BPCHAR,
    refund_amount      INT4 DEFAULT 0,
    source             VARCHAR(50)   DEFAULT '小程序'::VARCHAR,
    del_flag           CHAR          DEFAULT '0'::BPCHAR,
    create_dept        INT8,
    create_by          INT8,
    create_time        TIMESTAMP,
    update_by          INT8,
    update_time        TIMESTAMP,
    remark             VARCHAR(500)  DEFAULT NULL::VARCHAR,
    CONSTRAINT fk_order_buyer_user            FOREIGN KEY (buyer_user_id)            REFERENCES sys_user(user_id),
    CONSTRAINT fk_order_seller_user            FOREIGN KEY (seller_user_id)            REFERENCES sys_user(user_id),
    CONSTRAINT fk_order_activity        FOREIGN KEY (activity_id)        REFERENCES ecom_activity(activity_id),
    CONSTRAINT fk_order_activity_product FOREIGN KEY (activity_product_id) REFERENCES ecom_product_activity(activity_product_id),
    CONSTRAINT fk_order_record    FOREIGN KEY (record_id)    REFERENCES ecom_group_record(record_id),
    CONSTRAINT fk_order_org_id       FOREIGN KEY (org_id)      REFERENCES sys_org(org_id),
    CONSTRAINT fk_order_parent          FOREIGN KEY (parent_order_id)    REFERENCES ecom_order(order_id)
);
COMMENT ON TABLE ecom_order IS '订单表';
COMMENT ON COLUMN ecom_order.order_id              IS '订单ID';
COMMENT ON COLUMN ecom_order.tenant_id             IS '租户编号';
COMMENT ON COLUMN ecom_order.order_sn              IS '订单唯一号';
COMMENT ON COLUMN ecom_order.buyer_user_id               IS '买家用户id';
COMMENT ON COLUMN ecom_order.record_id       IS '参团记录ID';
COMMENT ON COLUMN ecom_order.parent_order_id       IS '父订单ID';
COMMENT ON COLUMN ecom_order.org_id       IS '公司ID';
COMMENT ON COLUMN ecom_order.leader_id       IS '团长ID';
COMMENT ON COLUMN ecom_order.seller_user_id       IS '卖家ID';
COMMENT ON COLUMN ecom_order.product_type     IS '产品业务类型';
COMMENT ON COLUMN ecom_order.extend_json     IS '扩展';

COMMENT ON COLUMN ecom_order.activity_id       IS '活动Id';
COMMENT ON COLUMN ecom_order.activity_product_id       IS '活动产品Id';
COMMENT ON COLUMN ecom_order.owner_type       IS '经营归属类型';
COMMENT ON COLUMN ecom_order.owner_id       IS '经营归属ID';
COMMENT ON COLUMN ecom_order.fulfillment_type       IS '履约执行类型';
COMMENT ON COLUMN ecom_order.fulfillment_id       IS '履约执行ID';
COMMENT ON COLUMN ecom_order.responsible_type       IS '责任主体类型';
COMMENT ON COLUMN ecom_order.responsible_id       IS '责任主体ID';
COMMENT ON COLUMN ecom_order.currency       IS '货币地区';
COMMENT ON COLUMN ecom_order.locale       IS '货币位置';
COMMENT ON COLUMN ecom_order.order_status          IS '订单状态';
COMMENT ON COLUMN ecom_order.pay_status            IS '支付状态';
COMMENT ON COLUMN ecom_order.pay_amount            IS '支付金额';
COMMENT ON COLUMN ecom_order.pay_time              IS '支付时间';
COMMENT ON COLUMN ecom_order.pay_way               IS '支付方式';
COMMENT ON COLUMN ecom_order.buyer_message         IS '买家留言';
COMMENT ON COLUMN ecom_order.auto_confirm_days     IS '自动确认天数';
COMMENT ON COLUMN ecom_order.confirm_time          IS '确认收货时间';
comment on column ecom_order.country_code      is '国家代码';
comment on column ecom_order.administrative_area      is '都道府县';
comment on column ecom_order.locality      is '一级行政区';

comment on column ecom_order.dependent_locality      is '二级行政区';
comment on column ecom_order.street_detail      is '街道';
comment on column ecom_order.postal_code      is '邮编';
comment on column ecom_order.addresses_name      is '地址名';
comment on column ecom_order.phone      is '电话';
comment on column ecom_order.other      is '其他附加信息';
comment on column ecom_order.formatted_address    is '格式化地址';
comment on column ecom_order.lang    is '语言类型';
comment on column ecom_order.latitude    is '纬度';
comment on column ecom_order.longitude    is '经度';

COMMENT ON COLUMN ecom_order.refund_status         IS '退款状态';
COMMENT ON COLUMN ecom_order.refund_amount         IS '退款金额';
COMMENT ON COLUMN ecom_order.source                IS '订单来源（小程序/H5/APP）';
COMMENT ON COLUMN ecom_order.del_flag              IS '删除标志';
COMMENT ON COLUMN ecom_order.create_dept           IS '创建部门';
COMMENT ON COLUMN ecom_order.create_by             IS '创建者';
COMMENT ON COLUMN ecom_order.create_time           IS '创建时间';
COMMENT ON COLUMN ecom_order.update_by             IS '更新者';
COMMENT ON COLUMN ecom_order.update_time           IS '更新时间';
COMMENT ON COLUMN ecom_order.remark                IS '备注';

-- DROP TABLE ecom_order_item;
CREATE TABLE IF NOT EXISTS ecom_order_item (
    item_id             INT8 PRIMARY KEY,
    order_id            INT8 NOT NULL,
    activity_product_id INT8 NOT NULL,
    spu_id              INT8 NOT NULL,
    sku_id              INT8 NOT NULL,
    spu_name            VARCHAR(50) NOT NULL,
    sku_name            VARCHAR(50) NOT NULL,
    sku_image          JSONB DEFAULT '{}'::JSONB,

    activity_id        INT8          NOT NULL,
    owner_type VARCHAR(20),  -- 经营归属（谁收钱） 收入 store 门店/ leader团长 /user商家/ platform平台/warehouse 仓库/warehouse 仓库/merchant 商家/franchisee  加盟商
    owner_id   INT8,
    price               INT4 DEFAULT 0,
    quantity            INT4 DEFAULT 0,
    total_amount        INT4 DEFAULT 0,
    refund_status       CHAR DEFAULT '0'::BPCHAR,
    refund_amount       INT4 DEFAULT 0,
    tenant_id   varchar(20)  default '000000'::varchar,
    del_flag    char         default '0'::bpchar,
    create_dept int8,
    create_by   int8,
    create_time timestamp,
    update_by   int8,
    update_time timestamp,
    remark      varchar(100) default null::varchar,
    CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES ecom_order(order_id),
    CONSTRAINT fk_item_activity_product FOREIGN KEY (activity_product_id) REFERENCES ecom_product_activity(activity_product_id)
);
COMMENT ON TABLE ecom_order_item IS '订单明细表';
COMMENT ON COLUMN ecom_order_item.item_id IS '明细ID';
COMMENT ON COLUMN ecom_order_item.order_id IS '订单ID';
COMMENT ON COLUMN ecom_order_item.activity_product_id IS '活动商品ID';
COMMENT ON COLUMN ecom_order_item.spu_id IS 'SPU ID';
COMMENT ON COLUMN ecom_order_item.spu_name IS '商品spu';
COMMENT ON COLUMN ecom_order_item.sku_id IS 'SKU ID';
COMMENT ON COLUMN ecom_order_item.sku_name IS '商品名称';
COMMENT ON COLUMN ecom_order_item.sku_image IS '商品图片';

COMMENT ON COLUMN ecom_order_item.activity_id       IS '活动Id';
COMMENT ON COLUMN ecom_order_item.owner_type       IS '经营归属类型';
COMMENT ON COLUMN ecom_order_item.owner_id       IS '经营归属ID';

COMMENT ON COLUMN ecom_order_item.price IS '单价';
COMMENT ON COLUMN ecom_order_item.quantity IS '数量';
COMMENT ON COLUMN ecom_order_item.total_amount IS '小计';
COMMENT ON COLUMN ecom_order_item.refund_status IS '退款状态';
COMMENT ON COLUMN ecom_order_item.refund_amount IS '退款金额';
comment on column ecom_order_item.tenant_id    is '租户编号';
comment on column ecom_order_item.del_flag     is '删除标志';
comment on column ecom_order_item.create_dept  is '创建部门';
comment on column ecom_order_item.create_by    is '创建者';
comment on column ecom_order_item.create_time  is '创建时间';
comment on column ecom_order_item.update_by    is '更新者';
comment on column ecom_order_item.update_time  is '更新时间';
comment on column ecom_order_item.remark       is '备注';


-- 永远先写 log，再改主表
-- DROP TABLE ecom_order_log;
CREATE TABLE ecom_order_log (
  order_log_id INT8 PRIMARY KEY,
  order_id INT8 NOT NULL,
  order_event_type VARCHAR(20) DEFAULT 'order_status',  -- “发生了什么事件？”,pay_status,refund_status,order_item,
  from_status VARCHAR(20),
  to_status VARCHAR(20),
  change_reason VARCHAR(100),     -- 状态变更原因（系统/骑手/异常）
  operator_type VARCHAR(20),      -- system / rider / admin
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(100)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP,
  CONSTRAINT fk_food_order FOREIGN KEY (order_id) REFERENCES ecom_order(order_id)
);
COMMENT ON TABLE ecom_order_log IS '订单变更动态日志';
COMMENT ON COLUMN ecom_order_log.order_log_id IS '订单变更动态日志ID';
COMMENT ON COLUMN ecom_order_log.order_id IS '订单ID';
COMMENT ON COLUMN ecom_order_log.order_event_type IS '订单事件类型';
COMMENT ON COLUMN ecom_order_log.from_status IS '起始状态';
COMMENT ON COLUMN ecom_order_log.to_status IS '终止状态';
COMMENT ON COLUMN ecom_order_log.change_reason IS '改变原因';
COMMENT ON COLUMN ecom_order_log.operator_type IS '操作类型';
comment on column ecom_order_log.tenant_id    is '租户编号';
comment on column ecom_order_log.del_flag     is '删除标志';
comment on column ecom_order_log.create_dept  is '创建部门';
comment on column ecom_order_log.create_by    is '创建者';
comment on column ecom_order_log.create_time  is '创建时间';
comment on column ecom_order_log.update_by    is '更新者';
comment on column ecom_order_log.update_time  is '更新时间';
comment on column ecom_order_log.remark       is '备注';

-- 拼团团员表（新增：记录参与用户）
-- DROP TABLE ecom_group_member;
CREATE TABLE IF NOT EXISTS ecom_group_member (
    member_id   INT8 PRIMARY KEY,
    tenant_id   VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    record_id   INT8          NOT NULL,
    user_id     INT8          NOT NULL,
    order_id    INT8          NOT NULL,
    join_time   TIMESTAMP     DEFAULT NOW(),
    status      CHAR          DEFAULT '0'::BPCHAR,
    del_flag    CHAR          DEFAULT '0'::BPCHAR,
    create_dept INT8,
    create_by   INT8,
    create_time TIMESTAMP,
    update_by   INT8,
    update_time TIMESTAMP,
    remark      VARCHAR(100)  DEFAULT NULL::VARCHAR,
    CONSTRAINT fk_member_record FOREIGN KEY (record_id) REFERENCES ecom_group_record(record_id),
    CONSTRAINT fk_member_user   FOREIGN KEY (user_id)   REFERENCES sys_user(user_id),
    CONSTRAINT fk_member_order  FOREIGN KEY (order_id)  REFERENCES ecom_order(order_id)
);

COMMENT ON TABLE ecom_group_member IS '拼团团员表';
COMMENT ON COLUMN ecom_group_member.member_id   IS '团员ID';
COMMENT ON COLUMN ecom_group_member.tenant_id   IS '租户编号';
COMMENT ON COLUMN ecom_group_member.record_id   IS '参团记录ID';
COMMENT ON COLUMN ecom_group_member.user_id     IS '用户ID';
COMMENT ON COLUMN ecom_group_member.order_id    IS '订单ID';
COMMENT ON COLUMN ecom_group_member.join_time   IS '加入时间';
COMMENT ON COLUMN ecom_group_member.status      IS '状态';
COMMENT ON COLUMN ecom_group_member.del_flag    IS '删除标志';
COMMENT ON COLUMN ecom_group_member.create_dept IS '创建部门';
COMMENT ON COLUMN ecom_group_member.create_by   IS '创建者';
COMMENT ON COLUMN ecom_group_member.create_time IS '创建时间';
COMMENT ON COLUMN ecom_group_member.update_by   IS '更新者';
COMMENT ON COLUMN ecom_group_member.update_time IS '更新时间';
COMMENT ON COLUMN ecom_group_member.remark      IS '备注';




--ecom_fulfillment_task（履约单）
--一条履约任务 = 一次可追责的交付行为  履约任务可以失败 / 重试 / 取消
--“谁，用什么方式，在什么地方，对哪些订单项，完成什么履约”
--设计原则：履约一定落到 item 粒度  一个 item → 多个 fulfillment_task（拆单 / 换执行者）
-- DROP TABLE ecom_fulfillment_task;
CREATE TABLE ecom_fulfillment_task (
    fulfillment_task_id INT8 PRIMARY KEY,
    /* ========= 订单关联 ========= */
    order_id            INT8 NOT NULL,
    order_item_id       INT8 NOT NULL,

    executor_type       VARCHAR(30) NOT NULL,  -- 履约执行者（who）
    executor_id         INT8 NOT NULL,
    fulfillment_type VARCHAR(20),  -- 怎么交付？（方式）   履约执行（谁发货）发货配送   'store_delivery','store_pickup','warehouse_delivery','merchant_delivery','platform_delivery', 'third_party_logistics',/'leader_distribution'
    fulfillment_id    INT8,


    /* ========= 履约责任归属 ========= */
    responsible_type   VARCHAR(30) NOT NULL,
    responsible_id     INT8 NOT NULL,

    /* ========= 配送 / 核销信息 ========= */
    logistics_company   VARCHAR(50),
    logistics_no        VARCHAR(50),
    rider_id            INT8,
    pickup_code         VARCHAR(50),
    verify_code         VARCHAR(50),
    -- 七、优先级
    priority             INT4 DEFAULT 100,
    /* ========= 履约状态 ========= */
    fulfillment_status VARCHAR(30) DEFAULT 'pending',  -- INIT / ASSIGNED / DELIVERING / DONE / CANCELED

    /* ========= 时间 ========= */
    assign_time            TIMESTAMP,
    start_time             TIMESTAMP,
    complete_time          TIMESTAMP,
    cancel_time            TIMESTAMP,


    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(100)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP
);
COMMENT ON TABLE ecom_fulfillment_task IS '履约单';
COMMENT ON COLUMN ecom_fulfillment_task.fulfillment_task_id   IS '履约单ID';
COMMENT ON COLUMN ecom_fulfillment_task.order_id   IS '订单ID';
COMMENT ON COLUMN ecom_fulfillment_task.order_item_id   IS '订单明细ID';
COMMENT ON COLUMN ecom_fulfillment_task.executor_type   IS '履约者类型';
COMMENT ON COLUMN ecom_fulfillment_task.executor_id   IS '履约者Id';
COMMENT ON COLUMN ecom_fulfillment_task.fulfillment_type   IS '履约方式类型';
COMMENT ON COLUMN ecom_fulfillment_task.fulfillment_id   IS '履约方式Id';
COMMENT ON COLUMN ecom_fulfillment_task.responsible_type   IS '履约责任归属';
COMMENT ON COLUMN ecom_fulfillment_task.responsible_id   IS '履约责任归属ID';
COMMENT ON COLUMN ecom_fulfillment_task.logistics_company   IS '物流公司';
COMMENT ON COLUMN ecom_fulfillment_task.logistics_no   IS '物流公司ID';
COMMENT ON COLUMN ecom_fulfillment_task.rider_id   IS '骑手ID';
COMMENT ON COLUMN ecom_fulfillment_task.pickup_code   IS '自提码';
COMMENT ON COLUMN ecom_fulfillment_task.verify_code   IS '验证码';

COMMENT ON COLUMN ecom_fulfillment_task.priority   IS '优先级别';
COMMENT ON COLUMN ecom_fulfillment_task.fulfillment_status      IS '履约状态';
COMMENT ON COLUMN ecom_fulfillment_task.assign_time     IS '分配时间';
COMMENT ON COLUMN ecom_fulfillment_task.start_time    IS '开始时间';
COMMENT ON COLUMN ecom_fulfillment_task.complete_time   IS '完成时间';
COMMENT ON COLUMN ecom_fulfillment_task.cancel_time   IS '取消时间';

COMMENT ON COLUMN ecom_fulfillment_task.tenant_id   IS '租户编号';
COMMENT ON COLUMN ecom_fulfillment_task.del_flag    IS '删除标志';
COMMENT ON COLUMN ecom_fulfillment_task.create_dept IS '创建部门';
COMMENT ON COLUMN ecom_fulfillment_task.create_by   IS '创建者';
COMMENT ON COLUMN ecom_fulfillment_task.create_time IS '创建时间';
COMMENT ON COLUMN ecom_fulfillment_task.update_by   IS '更新者';
COMMENT ON COLUMN ecom_fulfillment_task.update_time IS '更新时间';
COMMENT ON COLUMN ecom_fulfillment_task.remark      IS '备注';



-- 结算  钱从哪里来 → 属于谁 → 扣了什么 → 最终给多少  这笔钱，谁该拿多少？什么时候结？
-- DROP TABLE ecom_settlement;
CREATE TABLE ecom_settlement (
    settlement_id       INT8 PRIMARY KEY,
    /* ========= 订单来源 ========= */
    order_id            INT8 NOT NULL,
    order_item_id       INT8 NOT NULL,

    /* ========= 收入归属 ========= */
    owner_type          VARCHAR(30) NOT NULL, -- 公司类型等
    owner_id            INT8 NOT NULL,

    /* ========= 金额 ========= */
    order_amount        INT NOT NULL,   -- 订单原价
    pay_amount          INT NOT NULL,   -- 实付金额
    platform_fee        INT DEFAULT 0,  -- 平台服务费
    delivery_fee        INT DEFAULT 0,  -- 配送费
    commission_fee      INT DEFAULT 0,  -- 抽佣
    subsidy_amount      INT DEFAULT 0,  -- 平台补贴
    settlement_amount   INT NOT NULL,   -- 实际应结算

    /* ========= 结算周期 ========= */
    settlement_batch_no VARCHAR(50),
    settlement_time     TIMESTAMP,
    /* ========= 结算状态 ========= */
    status   VARCHAR(20) DEFAULT 'pending',
    /* ========= 审计 ========= */
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(100)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP
);
COMMENT ON TABLE ecom_settlement IS '结算表';
COMMENT ON COLUMN ecom_settlement.settlement_id   IS '结算ID';
COMMENT ON COLUMN ecom_settlement.order_id   IS '订单ID';
COMMENT ON COLUMN ecom_settlement.order_item_id   IS '订单明细ID';
COMMENT ON COLUMN ecom_settlement.owner_type       IS '经营归属类型';
COMMENT ON COLUMN ecom_settlement.owner_id       IS '经营归属ID';

COMMENT ON COLUMN ecom_settlement.order_amount       IS '订单原价';
COMMENT ON COLUMN ecom_settlement.pay_amount       IS '实付金额';
COMMENT ON COLUMN ecom_settlement.platform_fee       IS '平台服务费';
COMMENT ON COLUMN ecom_settlement.delivery_fee       IS '配送费';
COMMENT ON COLUMN ecom_settlement.commission_fee       IS '抽佣';
COMMENT ON COLUMN ecom_settlement.subsidy_amount       IS '平台补贴';
COMMENT ON COLUMN ecom_settlement.settlement_amount       IS '实际应结算';
COMMENT ON COLUMN ecom_settlement.settlement_batch_no       IS '结算批次';
COMMENT ON COLUMN ecom_settlement.settlement_time       IS '结算周期时间';
COMMENT ON COLUMN ecom_settlement.status       IS '结算状态';

COMMENT ON COLUMN ecom_settlement.tenant_id   IS '租户编号';
COMMENT ON COLUMN ecom_settlement.del_flag    IS '删除标志';
COMMENT ON COLUMN ecom_settlement.create_dept IS '创建部门';
COMMENT ON COLUMN ecom_settlement.create_by   IS '创建者';
COMMENT ON COLUMN ecom_settlement.create_time IS '创建时间';
COMMENT ON COLUMN ecom_settlement.update_by   IS '更新者';
COMMENT ON COLUMN ecom_settlement.update_time IS '更新时间';
COMMENT ON COLUMN ecom_settlement.remark      IS '备注';

--订单只是交易意图
--履约决定责任归属
--结算决定钱归谁
--法律责任是最终被冻结的事实

--法律责任快照表
-- DROP TABLE ecom_legal_responsibility;
CREATE TABLE ecom_legal_responsibility (
    responsibility_id INT8 PRIMARY KEY,
    order_id INT8,
    order_item_id INT8,
    fulfillment_task_id INT8,

    responsibility_org_type VARCHAR(30) DEFAULT 'platform',
    responsibility_org_id INT8,

    responsibility_scope VARCHAR(50)  ,  -- 'sales', 'fulfillment', 'after_sale', 'refund',  'compensation'
    effective_time TIMESTAMP,
    frozen BOOLEAN DEFAULT TRUE, -- 是否冻结为法律事实
    /* ========= 审计 ========= */
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(100)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP
);
COMMENT ON TABLE ecom_legal_responsibility IS '法律责任表';
COMMENT ON COLUMN ecom_legal_responsibility.responsibility_id   IS '法律责任ID';
COMMENT ON COLUMN ecom_legal_responsibility.order_id   IS '订单ID';
COMMENT ON COLUMN ecom_legal_responsibility.order_item_id   IS '订单明细ID';
COMMENT ON COLUMN ecom_legal_responsibility.fulfillment_task_id   IS '履约单ID';
COMMENT ON COLUMN ecom_legal_responsibility.responsibility_org_type       IS '担责组织类型';
COMMENT ON COLUMN ecom_legal_responsibility.responsibility_org_id       IS '担责组织ID';
COMMENT ON COLUMN ecom_legal_responsibility.responsibility_scope       IS '担责范围';
COMMENT ON COLUMN ecom_legal_responsibility.effective_time       IS '有效时间';
COMMENT ON COLUMN ecom_legal_responsibility.frozen       IS '是否冻结';

COMMENT ON COLUMN ecom_legal_responsibility.tenant_id   IS '租户编号';
COMMENT ON COLUMN ecom_legal_responsibility.del_flag    IS '删除标志';
COMMENT ON COLUMN ecom_legal_responsibility.create_dept IS '创建部门';
COMMENT ON COLUMN ecom_legal_responsibility.create_by   IS '创建者';
COMMENT ON COLUMN ecom_legal_responsibility.create_time IS '创建时间';
COMMENT ON COLUMN ecom_legal_responsibility.update_by   IS '更新者';
COMMENT ON COLUMN ecom_legal_responsibility.update_time IS '更新时间';
COMMENT ON COLUMN ecom_legal_responsibility.remark      IS '备注';


-- 通用库存表 （在哪 + 谁的 + 什么库存） 一个 stock_type = 一种扣减规则
-- DROP TABLE ecom_stock;
CREATE TABLE ecom_stock (
    stock_id        int8 PRIMARY KEY,
    /* ========= 商品 ========= */
    sku_id              int8 NOT NULL,

    /* ========= 库存所有者 ========= */
    owner_type          VARCHAR(30) NOT NULL, -- 'platform','store','warehouse','merchant','franchisee'
    owner_id            INT8 NOT NULL,

    /* ========= 库存所在地 ========= */
    location_type       VARCHAR(30) NOT NULL,  -- 'store','warehouse','merchant_warehouse','third_party_warehouse'  PHYSICAL        实物库存 VIRTUAL         虚拟库存（卡密、券）SERVICE         服务型库存（预约次数）
    location_address_id         int8 NOT NULL,
    stock_type          VARCHAR(20) NOT NULL, -- 库存类型 扣减策略（How） 1 REAL真实库存（进销存） 2 QUOTA  可售额度（配额库存 活动 / 门店） 3 PREALLOCATED    预分配库存 PRESELL         预售库存  4 INFINITE        无限库存
    /* ========= 数量 ========= */
    stock_total         INT NOT NULL DEFAULT 0, -- 物理存在的总数
    stock_available     INT NOT NULL DEFAULT 0, -- 可卖库存（= total - locked）
    stock_locked        INT NOT NULL DEFAULT 0,  -- 已下单未完成履约

    /* ========= 安全 ========= */
    safety_stock        INT DEFAULT 0,

    /* ========= 状态 ========= */
    status              CHAR(1) DEFAULT '0',

    /* ========= 审计 ========= */
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(100)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP,
    CONSTRAINT uk_stock UNIQUE (
        sku_id,
        owner_type,
        owner_id,
        location_type,
        location_address_id
    )
);
COMMENT ON TABLE ecom_stock IS '通用库存表';
COMMENT ON COLUMN ecom_stock.stock_id    IS '库存ID';
COMMENT ON COLUMN ecom_stock.sku_id      IS 'sku Id';
COMMENT ON COLUMN ecom_stock.owner_type       IS '经营归属类型';
COMMENT ON COLUMN ecom_stock.owner_id       IS '经营归属ID';
COMMENT ON COLUMN ecom_stock.location_type       IS '库存所在类型';
COMMENT ON COLUMN ecom_stock.location_address_id       IS '库存所在地ID';
COMMENT ON COLUMN ecom_stock.stock_type       IS '库存类型';
COMMENT ON COLUMN ecom_stock.stock_total     IS '物理存在的总数';
COMMENT ON COLUMN ecom_stock.stock_available   IS '可卖库存';
COMMENT ON COLUMN ecom_stock.stock_locked IS '未完成履约';
COMMENT ON COLUMN ecom_stock.safety_stock      IS '安全库存';
COMMENT ON COLUMN ecom_stock.status      IS '状态';
COMMENT ON COLUMN ecom_stock.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_stock.remark        IS '备注';
COMMENT ON COLUMN ecom_stock.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_stock.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_stock.create_by     IS '创建者';
COMMENT ON COLUMN ecom_stock.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_stock.update_by     IS '更新者';
COMMENT ON COLUMN ecom_stock.update_time   IS '更新时间';

设计原则：一切库存变化 = 一条流水不修改、不覆盖，只追加 永远可追溯
-- DROP TABLE ecom_stock_flow;
CREATE TABLE ecom_stock_flow (
    stock_flow_id   INT8 PRIMARY KEY,
    /* ========= 关联库存 ========= */
    stock_id        INT8 NOT NULL,
    sku_id              INT8 NOT NULL,

    /* ========= 归属 ========= */
    owner_type          VARCHAR(30) NOT NULL,
    owner_id            INT8 NOT NULL,
    location_type       VARCHAR(30) NOT NULL,
    location_address_id         INT8 NOT NULL,
    --flow_type = IN进货时 / OUT支付成功 发货 / LOCK / UNLOCK / ADJUST
    --一级分类（业务原因）
    --INIT            -- 初始化
    --INBOUND         -- 入库
    --OUTBOUND        -- 出库
    --LOCK            -- 锁定
    --RELEASE         -- 解锁
    --ADJUST          -- 盘点调整
    --二级业务原因（可选）
    --PURCHASE_IN        -- 采购入库
    --RETURN_IN          -- 退货入库
    --ORDER_CREATE       -- 下单锁库
    --ORDER_PAY          -- 支付扣库
    --ORDER_CANCEL       -- 取消解锁
    --SHIPMENT           -- 发货扣库
    --REFUND_IN          -- 退款回库
    /* ========= 业务来源 flow_type 为什么变========= */
    stock_biz_type            VARCHAR(30) NOT NULL,
    biz_id              INT8,   -- order_id / order_item_id / adjustment_id

    /* ========= 数量变化 ========= */
    change_qty          INT NOT NULL,       -- 正数增加，负数减少
    before_qty          INT NOT NULL,
    after_qty           INT NOT NULL,

    /* ========= 库存类型 ========= */
    stock_type          VARCHAR(20) NOT NULL, -- 库存类型 扣减策略（How） 1 REAL真实库存（进销存） 2 QUOTA  可售额度（配额库存 活动 / 门店） 3 PREALLOCATED    预分配库存 PRESELL         预售库存  4 INFINITE        无限库存
    /* ========= 备注 ========= */
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(100)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR         DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP
);
COMMENT ON TABLE ecom_stock_flow IS '库存流水表';
COMMENT ON COLUMN ecom_stock_flow.stock_flow_id    IS '库存流水ID';
COMMENT ON COLUMN ecom_stock_flow.stock_id    IS '库存ID';
COMMENT ON COLUMN ecom_stock_flow.sku_id      IS 'sku Id';
COMMENT ON COLUMN ecom_stock_flow.owner_type       IS '经营归属类型';
COMMENT ON COLUMN ecom_stock_flow.owner_id       IS '经营归属ID';
COMMENT ON COLUMN ecom_stock_flow.location_type       IS '库存所在类型';
COMMENT ON COLUMN ecom_stock_flow.location_address_id       IS '库存所在地ID';
COMMENT ON COLUMN ecom_stock_flow.stock_biz_type     IS '库存业务类型';
COMMENT ON COLUMN ecom_stock_flow.biz_id   IS '业务ID';
COMMENT ON COLUMN ecom_stock_flow.change_qty IS '变化数量';
COMMENT ON COLUMN ecom_stock_flow.before_qty      IS '之前数量';
COMMENT ON COLUMN ecom_stock_flow.after_qty      IS '之后数量';
COMMENT ON COLUMN ecom_stock_flow.after_qty      IS '之后数量';
COMMENT ON COLUMN ecom_stock_flow.stock_type      IS '库存类型';
COMMENT ON COLUMN ecom_stock_flow.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_stock_flow.remark        IS '备注';
COMMENT ON COLUMN ecom_stock_flow.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_stock_flow.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_stock_flow.create_by     IS '创建者';
COMMENT ON COLUMN ecom_stock_flow.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_stock_flow.update_by     IS '更新者';
COMMENT ON COLUMN ecom_stock_flow.update_time   IS '更新时间';


-- 退款一定要独立表
-- DROP TABLE ecom_refund;
CREATE TABLE ecom_refund (
    refund_id           INT8 PRIMARY KEY,
    /* ========= 订单关联 ========= */
    order_id            INT8 NOT NULL,
    order_item_id       INT8,

    /* ========= 退款类型 ========= */
    refund_type         VARCHAR(30) NOT NULL  DEFAULT 'only_refund', -- 'only_refund',          -- 仅退款 'return_and_refund',    -- 退货退款 'partial_refund'        -- 部分退款
    /* ========= 金额 ========= */
    refund_amount       INT NOT NULL,
    refund_reason       VARCHAR(100),
    refund_desc         VARCHAR(200),

    /* ========= 退款状态 ========= */
    refund_status       VARCHAR(30) NOT NULL,

    /* ========= 逆向结算 ========= */
    reverse_settlement  BOOLEAN DEFAULT TRUE,
    reverse_stock   BOOLEAN DEFAULT TRUE,

    /* ========= 审核 ========= */
    audit_by            INT8,
    audit_time          TIMESTAMP,
    /* ========= 备注 ========= */
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(100)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP,
    CONSTRAINT fk_refund_order FOREIGN KEY (order_id) REFERENCES ecom_order(order_id)
);
COMMENT ON TABLE ecom_refund IS '退款表';
COMMENT ON COLUMN ecom_refund.refund_id    IS '退款ID';
COMMENT ON COLUMN ecom_refund.order_id   IS '订单ID';
COMMENT ON COLUMN ecom_refund.order_item_id   IS '订单明细ID';
COMMENT ON COLUMN ecom_refund.refund_type    IS '退款类型';
COMMENT ON COLUMN ecom_refund.refund_amount      IS '退款金额';
COMMENT ON COLUMN ecom_refund.refund_reason       IS '退款原因';
COMMENT ON COLUMN ecom_refund.refund_desc       IS '退款描述';
COMMENT ON COLUMN ecom_refund.refund_status       IS '退款状态';
COMMENT ON COLUMN ecom_refund.reverse_settlement       IS '逆向结算';
COMMENT ON COLUMN ecom_refund.reverse_stock       IS '逆向库存';
COMMENT ON COLUMN ecom_refund.audit_by       IS '审核人员';
COMMENT ON COLUMN ecom_refund.audit_time       IS '审核时间';
COMMENT ON COLUMN ecom_refund.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_refund.remark        IS '备注';
COMMENT ON COLUMN ecom_refund.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_refund.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_refund.create_by     IS '创建者';
COMMENT ON COLUMN ecom_refund.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_refund.update_by     IS '更新者';
COMMENT ON COLUMN ecom_refund.update_time   IS '更新时间';


-- 物流跟踪记录表（新增：独立物流轨迹）
CREATE TABLE IF NOT EXISTS ecom_logistics_history (
    history_id     INT8 PRIMARY KEY,
    order_id       INT8          NOT NULL,
    node_time      TIMESTAMP     NOT NULL,
    node_status    VARCHAR(50)   NOT NULL,  -- （已揽收/运输中/派送中/已签收）
    node_location  JSONB         DEFAULT '{}'::JSONB, -- {"province":"广东","city":"深圳","address":"南山营业部"}
    operator       VARCHAR(100)  DEFAULT ''::VARCHAR,
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(500)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP,
    CONSTRAINT fk_logistics_order FOREIGN KEY (order_id) REFERENCES ecom_order(order_id)
);
COMMENT ON TABLE ecom_logistics_history IS '物流跟踪记录表';
COMMENT ON COLUMN ecom_logistics_history.history_id    IS '跟踪ID';
COMMENT ON COLUMN ecom_logistics_history.order_id      IS '订单ID';
COMMENT ON COLUMN ecom_logistics_history.node_time     IS '节点时间';
COMMENT ON COLUMN ecom_logistics_history.node_status   IS '节点状态';
COMMENT ON COLUMN ecom_logistics_history.node_location IS '节点位置JSON';
COMMENT ON COLUMN ecom_logistics_history.operator      IS '操作人';
COMMENT ON COLUMN ecom_logistics_history.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_logistics_history.remark        IS '备注';
COMMENT ON COLUMN ecom_logistics_history.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_logistics_history.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_logistics_history.create_by     IS '创建者';
COMMENT ON COLUMN ecom_logistics_history.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_logistics_history.update_by     IS '更新者';
COMMENT ON COLUMN ecom_logistics_history.update_time   IS '更新时间';



--created        已创建（订单生成，未派单）
--pending        等待接单
--accepted       骑手已接单
--arrived_store  已到店
--picked_up      已取餐
--in_transit     配送中
--delivered      已送达
--cancelled      已取消
--timeout        超时
--exception      异常

-- 外卖配送专用表 只和订单 1 —— 1 绑定
CREATE TABLE ecom_order_delivery (
  order_delivery_id INT8 PRIMARY KEY,
  order_id INT8 NOT NULL,
  order_type VARCHAR(50) NOT NULL, -- TAKEOUT / LOGISTICS / PICKUP 外卖 / 物流 / 自取
  rider_name VARCHAR(50),
  rider_id VARCHAR(50),
  rider_phone VARCHAR(20),
  delivery_distance INT4,        -- 骑手距离门店多少米
  delivery_status VARCHAR(20) DEFAULT 'pending', -- 'pending'等待中 | 'in_transit'配送中| 'delivered'已送达
  expected_time TIMESTAMP,  -- 期望时间
  actual_time TIMESTAMP,  -- 交付时间

    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(500)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP,
  CONSTRAINT fk_food_order FOREIGN KEY (order_id) REFERENCES ecom_order(order_id)
);
COMMENT ON TABLE ecom_order_delivery IS '订单配送专用表';
comment on column ecom_order_delivery.order_delivery_id      is '订单配送Id';
comment on column ecom_order_delivery.order_id      is '订单ID';
comment on column ecom_order_delivery.order_type    is '订单业务类型';
comment on column ecom_order_delivery.rider_name    is '骑手名字';
comment on column ecom_order_delivery.rider_phone    is '骑手电话';
comment on column ecom_order_delivery.delivery_distance    is '距离目的';
comment on column ecom_order_delivery.delivery_status    is '配送状态';
comment on column ecom_order_delivery.expected_time    is '期望时间';
comment on column ecom_order_delivery.actual_time    is '交付时间';
COMMENT ON COLUMN ecom_order_delivery.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_order_delivery.remark        IS '备注';
COMMENT ON COLUMN ecom_order_delivery.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_order_delivery.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_order_delivery.create_by     IS '创建者';
COMMENT ON COLUMN ecom_order_delivery.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_order_delivery.update_by     IS '更新者';
COMMENT ON COLUMN ecom_order_delivery.update_time   IS '更新时间';

-- 永远先写 log，再改主表
CREATE TABLE ecom_order_delivery_log (
  delivery_log_id INT8 PRIMARY KEY,
  delivery_id INT8 ,
  order_id INT8 NOT NULL,
  from_status VARCHAR(20),
  to_status VARCHAR(20),
  change_reason VARCHAR(200),     -- 状态变更原因（系统/骑手/异常）
  operator_type VARCHAR(20),      -- system / rider / admin
    tenant_id      VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    remark         VARCHAR(500)  DEFAULT NULL::VARCHAR,
    del_flag       CHAR          DEFAULT '0'::BPCHAR,
    create_dept    INT8,
    create_by      INT8,
    create_time    TIMESTAMP,
    update_by      INT8,
    update_time    TIMESTAMP,
  CONSTRAINT fk_food_order FOREIGN KEY (order_id) REFERENCES ecom_order(order_id)
);
COMMENT ON TABLE ecom_order_delivery_log IS '配送状态变更日志表';
comment on column ecom_order_delivery_log.delivery_log_id      is '配送日志Id';
comment on column ecom_order_delivery_log.delivery_id      is '外卖配送Id';
comment on column ecom_order_delivery_log.order_id      is '订单ID';
comment on column ecom_order_delivery_log.from_status    is '起初状态';
comment on column ecom_order_delivery_log.to_status    is '目前状态';
comment on column ecom_order_delivery_log.change_reason    is '状态变更原因（系统/骑手/异常）';
comment on column ecom_order_delivery_log.operator_type    is '操作类型：system / rider / admin';
COMMENT ON COLUMN ecom_order_delivery_log.tenant_id     IS '租户编号';
COMMENT ON COLUMN ecom_order_delivery_log.remark        IS '备注';
COMMENT ON COLUMN ecom_order_delivery_log.del_flag      IS '删除标志';
COMMENT ON COLUMN ecom_order_delivery_log.create_dept   IS '创建部门';
COMMENT ON COLUMN ecom_order_delivery_log.create_by     IS '创建者';
COMMENT ON COLUMN ecom_order_delivery_log.create_time   IS '创建时间';
COMMENT ON COLUMN ecom_order_delivery_log.update_by     IS '更新者';
COMMENT ON COLUMN ecom_order_delivery_log.update_time   IS '更新时间';







-- 门票/服务专用表
CREATE TABLE ecom_order_ticket (
  ticket_id INT8 PRIMARY KEY,
  order_id INT8 NOT NULL,
  code_no VARCHAR(50) NOT NULL,  -- 门票券码
  qr_code BYTEA,                 -- 二维码二进制
  valid_start TIMESTAMP,
  valid_end TIMESTAMP,
  verify_status CHAR DEFAULT '0',  -- ：0未核销 1已核销2已经过期
    del_flag CHAR DEFAULT '0',
    tenant_id VARCHAR(20) DEFAULT '000000',
    create_dept INT8,
    create_by INT8,
    create_time TIMESTAMP,
    update_by INT8,
    update_time TIMESTAMP,
    remark VARCHAR(500) DEFAULT NULL,
  CONSTRAINT fk_ticket_order FOREIGN KEY (order_id) REFERENCES ecom_order(order_id)
);
COMMENT ON TABLE ecom_order_ticket IS '门票/服务专用表';
comment on column ecom_order_ticket.ticket_id      is '门票或服务Id';
comment on column ecom_order_ticket.order_id      is '订单ID';
comment on column ecom_order_ticket.code_no    is '门票券码';
comment on column ecom_order_ticket.qr_code    is '二维码二进制';
comment on column ecom_order_ticket.valid_start    is '验证开始时间';
comment on column ecom_order_ticket.valid_end    is '验证结束时间';
comment on column ecom_order_ticket.verify_status    is '状态';
comment on column ecom_order_ticket.tenant_id    is '租户编号';
comment on column ecom_order_ticket.del_flag     is '删除标志';
comment on column ecom_order_ticket.create_dept  is '创建部门';
comment on column ecom_order_ticket.create_by    is '创建者';
comment on column ecom_order_ticket.create_time  is '创建时间';
comment on column ecom_order_ticket.update_by    is '更新者';
comment on column ecom_order_ticket.update_time  is '更新时间';
comment on column ecom_order_ticket.remark       is '备注';





-- 机构组织商品

-- DROP TABLE ecom_product_org;
CREATE TABLE ecom_product_org (
    org_product_id INT8 NOT NULL PRIMARY KEY,
    org_id INT8 NOT NULL,
    sku_id INT8 NOT NULL,
    owner_type VARCHAR(20),  -- 经营归属（谁收钱） 收入 store 门店/ leader团长 /user商家/ platform平台/warehouse 仓库/warehouse 仓库/merchant 商家/franchisee  加盟商
    owner_id   INT8,
    fulfillment_type VARCHAR(20),  --履约执行（谁发货）发货配送   'store_delivery','store_pickup','warehouse_delivery','merchant_delivery','platform_delivery', 'third_party_logistics',/'leader_distribution'
    fulfillment_id    INT8,
    expire_time TIMESTAMP,
    org_title VARCHAR(20),
    org_price INT4 NOT NULL, -- 门店价
    org_stock INT4 NOT NULL,  -- 门店可售额度
    CONSTRAINT uk_org_sku UNIQUE (org_id, sku_id),
    FOREIGN KEY (org_id) REFERENCES sys_org(org_id),
    FOREIGN KEY (sku_id) REFERENCES ecom_product_sku(sku_id)
);
COMMENT ON TABLE ecom_product_org IS '机构组织商品表';
comment on column ecom_product_org.org_product_id      is '机构组织商品Id';
comment on column ecom_product_org.org_id      is '机构组织ID';
comment on column ecom_product_org.sku_id    is '商品skuID';
COMMENT ON COLUMN ecom_product_org.owner_type              IS '活动所属类型';
COMMENT ON COLUMN ecom_product_org.owner_id              IS '活动所属ID';
COMMENT ON COLUMN ecom_product_org.fulfillment_type              IS '履约类型';
COMMENT ON COLUMN ecom_product_org.fulfillment_id              IS '履约ID';
COMMENT ON COLUMN ecom_product_org.expire_time      IS '过期时间';
COMMENT ON COLUMN ecom_product_org.org_title      IS '活动商品标题';
COMMENT ON COLUMN ecom_product_org.org_price         IS '活动价格';
COMMENT ON COLUMN ecom_product_org.org_stock      IS '活动配额库存';









---------------------------------------------------






















-- 新建地址翻译表
CREATE TABLE address_i18n(
    address_i18n_id INT8 PRIMARY KEY,
    address_id INT8 NOT NULL,
    lang VARCHAR(10) NOT NULL,
    translated_value TEXT NOT NULL,
    FOREIGN KEY (address_id) REFERENCES sys_addresses(address_id)
);
COMMENT ON TABLE address_i18n IS '国际地址表翻译';
comment on column address_i18n.address_i18n_id      is '国际地址翻译ID';
comment on column address_i18n.address_id      is '国际地址ID';
comment on column address_i18n.lang    is '语言类型';
comment on column address_i18n.translated_value      is '翻译后的地址';


门店自提
门店堂食
门店服务预约
平台团购
门店专属团购
外卖 + 团购混合

-- 表的定位是 谁来做这个订单 外卖的“履约主体”  提供商品
CREATE TABLE ecom_store (
    store_id INT8 PRIMARY KEY,
--    store_type (餐饮 / 零售 / 服务)
    capability_type VARCHAR(50) NOT NULL,  -- delivery / dine_in / service / self_pick  外卖 自提 堂食 服务
    address_id  INT8 ,
    store_name VARCHAR(50) NOT NULL,
    address VARCHAR(200) NOT NULL,
    business_hours VARCHAR(50) DEFAULT '09:00-22:00', -- 营业时间
    delivery_radius INT4 DEFAULT 5000, -- 配送半径（米）
    status CHAR DEFAULT 'open', -- 'open'营业中 | 'close'打烊
    lang VARCHAR(10) DEFAULT 'zh', -- 默认语言
    del_flag CHAR DEFAULT '0',
    tenant_id VARCHAR(20) DEFAULT '000000',
    create_dept INT8,
    create_by INT8,
    create_time TIMESTAMP,
    update_by INT8,
    update_time TIMESTAMP,
    remark VARCHAR(500) DEFAULT NULL,
    CONSTRAINT fk_ecom_store_address_id FOREIGN KEY (address_id) REFERENCES sys_addresses(address_id)
);
COMMENT ON TABLE ecom_store IS '门店表';
comment on column ecom_store.store_id      is '门店Id';
comment on column ecom_store.capability_type      is '门店承接业务类型';
comment on column ecom_store.address_id      is '门店通用地址Id';
comment on column ecom_store.store_name    is '门店名';
comment on column ecom_store.business_hours    is '营业时间';
comment on column ecom_store.delivery_radius    is '配送半径';
comment on column ecom_store.status    is 'open营业中 | close打烊 倒闭';
comment on column ecom_store.lang    is '默认语言';
comment on column ecom_store.tenant_id    is '租户编号';
comment on column ecom_store.del_flag     is '删除标志';
comment on column ecom_store.create_dept  is '创建部门';
comment on column ecom_store.create_by    is '创建者';
comment on column ecom_store.create_time  is '创建时间';
comment on column ecom_store.update_by    is '更新者';
comment on column ecom_store.update_time  is '更新时间';
comment on column ecom_store.remark       is '备注';




-- 外卖商品扩展表
CREATE TABLE ecom_product_delivery(
    spu_id INT8 PRIMARY KEY,
    deliveryRadius INT4 DEFAULT 5000, -- 配送半径（米）
    minDeliveryTime INT4, -- 最短配送时间（分钟）
    maxDeliveryTime INT4, -- 最长配送时间（分钟）
    deliveryOptions JSONB DEFAULT '{"standard": true, "express": false}', -- 配送选项
    FOREIGN KEY (spu_id) REFERENCES ecom_product_spu(spu_id)
);

-- 门票商品扩展表
CREATE TABLE ecom_product_ticket(
    spu_id INT8 PRIMARY KEY,
    validityPeriod JSONB DEFAULT '{"start": null, "end": null}', -- 有效期
    verificationRules JSONB DEFAULT '{"location": null, "codeType": "qr"}', -- 核销规则
    admissionOptions JSONB DEFAULT '{"entryPoint": null, "groupSize": 1}', -- 入场选项
    FOREIGN KEY (spu_id) REFERENCES ecom_product_spu(spu_id)
);

-- 秒杀商品扩展表
CREATE TABLE ecom_product_seckill(
    spu_id INT8 PRIMARY KEY,
    stockSnapshot INT4 NOT NULL, -- 库存快照
    seckillPeriod JSONB DEFAULT '{"start": null, "end": null}', -- 秒杀时间段
    priceStrategy JSONB DEFAULT '{"original": 0, "seckill": 0}', -- 价格策略
    FOREIGN KEY (spu_id) REFERENCES ecom_product_spu(spu_id)
);


CREATE TABLE ecom_seckill_stock_lock (
  lock_id INT8 PRIMARY KEY,
  sku_id INT8 NOT NULL,
  activity_product_id INT8 NOT NULL,
  quantity INT4 NOT NULL,
  user_id INT8 NOT NULL,
  expire_time TIMESTAMP NOT NULL,  -- 锁定5秒后过期
  status CHAR DEFAULT 'locked',   -- 'locked'锁定中 | 'released'释放
  CONSTRAINT fk_lock_sku FOREIGN KEY (sku_id) REFERENCES ecom_product_sku(sku_id)
);


-- ==================== 团长管理模块 ====================

-- 团长申请表（保留原结构，补充外键）
CREATE TABLE IF NOT EXISTS ecom_leader_apply (
    apply_id        INT8 PRIMARY KEY,
    tenant_id       VARCHAR(20)   DEFAULT '000000'::VARCHAR,
    user_id         INT8          NOT NULL,
    apply_status    CHAR          DEFAULT '0'::BPCHAR,
    commission_rate NUMERIC(5,2)  DEFAULT 0.00,
    apply_reason    VARCHAR(500)  DEFAULT ''::VARCHAR,
    audit_remark    VARCHAR(500)  DEFAULT ''::VARCHAR,
    del_flag        CHAR          DEFAULT '0'::BPCHAR,
    create_dept     INT8,
    create_by       INT8,
    create_time     TIMESTAMP,
    update_by       INT8,
    update_time     TIMESTAMP,
    remark          VARCHAR(500)  DEFAULT NULL::VARCHAR,
    CONSTRAINT fk_apply_user FOREIGN KEY (user_id) REFERENCES sys_user(user_id)
);

COMMENT ON TABLE ecom_leader_apply IS '团长申请表';
COMMENT ON COLUMN ecom_leader_apply.apply_id        IS '申请ID';
COMMENT ON COLUMN ecom_leader_apply.tenant_id       IS '租户编号';
COMMENT ON COLUMN ecom_leader_apply.user_id         IS '用户ID（外键）';
COMMENT ON COLUMN ecom_leader_apply.apply_status    IS '申请状态（0待审核 1已通过 2已拒绝）';
COMMENT ON COLUMN ecom_leader_apply.commission_rate IS '佣金比例（%）';
COMMENT ON COLUMN ecom_leader_apply.apply_reason    IS '申请理由';
COMMENT ON COLUMN ecom_leader_apply.audit_remark    IS '审核备注';
COMMENT ON COLUMN ecom_leader_apply.del_flag        IS '删除标志';
COMMENT ON COLUMN ecom_leader_apply.create_dept     IS '创建部门';
COMMENT ON COLUMN ecom_leader_apply.create_by       IS '创建者';
COMMENT ON COLUMN ecom_leader_apply.create_time     IS '创建时间';
COMMENT ON COLUMN ecom_leader_apply.update_by       IS '更新者';
COMMENT ON COLUMN ecom_leader_apply.update_time     IS '更新时间';
COMMENT ON COLUMN ecom_leader_apply.remark          IS '备注';


-- 创建多级分销关系表
CREATE TABLE ecom_distribution_relation (
    relation_id INT8 PRIMARY KEY,
    user_id INT8 NOT NULL,
    parent_id INT8 NOT NULL,
    level INT4 NOT NULL, -- 分销层级（1级、2级等）
    commission_rate NUMERIC(5,2) DEFAULT 0.00,
    FOREIGN KEY (user_id) REFERENCES sys_user(user_id),
    FOREIGN KEY (parent_id) REFERENCES sys_user(user_id)
);

-- 创建分销关系索引
CREATE INDEX idx_distribution_relation ON ecom_distribution_relation (user_id, parent_id);




CREATE TABLE ecom_order_delivery (
    order_id INT8 PRIMARY KEY,
    address_id INT8 NOT NULL,
    store_id INT8 NOT NULL,
    rider_id INT8, -- 骑手ID
    rider_name VARCHAR(50), -- 骑手姓名
    rider_phone VARCHAR(20), -- 骑手电话
    delivery_status VARCHAR(20) DEFAULT 'pending', -- 'pending'等待中 | 'in_transit'配送中 | 'delivered'已送达
    expected_time TIMESTAMP,  -- 交付时间
    actual_time TIMESTAMP,  -- 交付时间
    FOREIGN KEY (order_id) REFERENCES ecom_order(order_id),
    FOREIGN KEY (address_id) REFERENCES gmtDelivery_address(address_id),
    FOREIGN KEY (store_id) REFERENCES ecom_store(store_id)
);

CREATE TABLE ecom_ticket (
    ticket_id INT8 PRIMARY KEY,
    order_id INT8 NOT NULL,
    code_no VARCHAR(50) NOT NULL,  -- 券码
    qr_code BYTEA,                 -- 二维码二进制
    valid_start TIMESTAMP,
    valid_end TIMESTAMP,
    verify_status CHAR DEFAULT '0',  -- 0未核销 1已核销
    verify_time TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES ecom_order(order_id)
);

-- 创建门票国际化翻译表
CREATE TABLE ecom_ticket_i18n (
    ticket_id INT8 NOT NULL,
    lang VARCHAR(10) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (ticket_id, lang),
    FOREIGN KEY (ticket_id) REFERENCES gmtTicket(ticket_id)
);

CREATE TABLE ecom_verify (
    verify_id INT8 PRIMARY KEY,
    ticket_id INT8 NOT NULL,
    verify_time TIMESTAMP,
    verify_location VARCHAR(200),  -- 核销地点
    operator VARCHAR(100),         -- 操作人
    FOREIGN KEY (ticket_id) REFERENCES gmtTicket(ticket_id)
);


-- 通用属性表（EAV）
CREATE TABLE ecom_attributes (
  attr_id      BIGSERIAL PRIMARY KEY,
  tenant_id    VARCHAR(20) DEFAULT '000000'::varchar,
  resource_type VARCHAR(50) NOT NULL,   -- e.g. 'sku','spu','activity'
  resource_id  INT8 NOT NULL,         -- 指向对应表的 id
  attr_key     VARCHAR(100) NOT NULL,   -- 标准化 key，例如 'color','size'
  attr_value   VARCHAR(1000) NOT NULL,  -- 值的 code 或原始字符串
  attr_type    VARCHAR(50) DEFAULT 'string', -- 'string','int','json','bool'
  created_at   TIMESTAMP DEFAULT now(),
  updated_at   TIMESTAMP DEFAULT now(),
  UNIQUE(resource_type, resource_id, attr_key)
);


-- i18n 表（增加 resource_type 以避免冲突）
CREATE TABLE ecom_i18n (
  i18n_id      BIGSERIAL PRIMARY KEY,
  tenant_id    VARCHAR(20) DEFAULT '000000'::varchar,
  resource_type VARCHAR(50) NOT NULL,   -- 'sku_attr_key', 'sku_attr_value', 'spu_name', ...
  resource_key  VARCHAR(200) NOT NULL,  -- 比如 attr_key 或 attr_value_code 或 'spu:123:title'
  lang         VARCHAR(10) NOT NULL,    -- 'zh','en','ja'
  text         VARCHAR(1000) NOT NULL,  -- 显示文本
  created_at   TIMESTAMP DEFAULT now(),
  UNIQUE(resource_type, resource_key, lang)
);

-- product_display_snapshot_lang: 商品展示快照表  每个 product(spu) 每种语言一条最新快照
CREATE TABLE IF NOT EXISTS product_display_snapshot_lang (
  snapshot_id      BIGSERIAL PRIMARY KEY,
  product_id       INT8 NOT NULL,            -- spu_id
  lang             VARCHAR(10) NOT NULL DEFAULT 'zh',
  version INT8 NOT NULL DEFAULT 1,
  snapshot_data    JSONB NOT NULL,             -- 最终展示 JSON（含规格/价格/图片等）
  updated_at       TIMESTAMP NOT NULL DEFAULT NOW(),
  UNIQUE (product_id, lang)
);

-- 新建汇率表
CREATE TABLE exchange_rate(
    rate_id BIGSERIAL PRIMARY KEY,
    currency_code VARCHAR(10) NOT NULL, -- 货币代码
    base_currency VARCHAR(10) NOT NULL, -- 基准货币
    rate NUMERIC(10,6) NOT NULL, -- 汇率
    effective_date TIMESTAMP NOT NULL, -- 生效日期
    expiration_date TIMESTAMP, -- 失效日期
    UNIQUE (currency_code, base_currency, effective_date)
);

-- 新建国家/地区信息表
CREATE TABLE country_info(
    country_code CHAR(2) PRIMARY KEY, -- ISO 3166-1 alpha-2
    country_name VARCHAR(100) NOT NULL,
    currency VARCHAR(10) NOT NULL, -- 默认货币
    decimal places INT4 DEFAULT 2, -- 小数位数
    thousand separator VARCHAR(10) DEFAULT ',', -- 千位分隔符
    decimal separator VARCHAR(10) DEFAULT '.', -- 小数点分隔符
    address_format VARCHAR(200) NOT NULL -- 地址格式模板
);

七、可替代策略（混合更稳妥）

小范围动态字段使用 JSONB

对“非常少量且读多写少”的动态扩展属性，用 jsonb 存在主表（SPU/SKU）里，优点：查询简单、无行膨胀、可用 GIN 索引。缺点：跨实体共性搜索困难。

适合不需要在索引或筛选中频繁使用的字段。

核心字段列化 + 扩展表

重要且常筛选的字段继续列化（如 color、size、material、weight）——性能最好。

次要或很少用的字段放到 ecom_attributes。

为 i18n 建“资源命名规则”

例如 resource_type 使用精细枚举：spu:title, sku:desc, attr:key, attr:value，避免混淆。

即使存在折扣、费率等导致小数的场景，“分 + 整数”方案依然适用且推荐！
只需做到：
中间计算用高精度（DECIMAL）
最终结果按业务规则舍入到整数分
全系统统一单位（分）和舍入逻辑
关键金额可追溯计算过程
在数据库注释或数据字典中明确标注：“所有金额单位为分，整数存储”
在 API 文档中强调：“price: integer, unit: cent”
对账系统需支持“舍入差异 ≤ 1 分”的容错
这样既能保证精度安全，又能保持系统简洁高效。
所有业务代码禁止直接使用 Math.round() 或 (int)(x + 0.5) 等不安全方式。







create table if not exists test_bob
(
    bob_id     int8,
    tenant_id   varchar(20) default '000000'::varchar,
    bob_name   varchar(30) default ''::varchar,
    bob_category varchar(100) default null::varchar,
    order_num   int4        default 0,
    leader      int8        default null,
    phone       varchar(11) default null::varchar,
    email       varchar(50) default null::varchar,
    status      char        default '0'::bpchar,
    del_flag    char        default '0'::bpchar,
    create_bob int8,
    create_by   int8,
    create_time timestamp,
    update_by   int8,
    update_time timestamp,
    constraint "test_bob_pk" primary key (bob_id)
);

comment on table test_bob               is '鲍勃表';
comment on column test_bob.bob_id      is '鲍勃ID';
comment on column test_bob.tenant_id    is '租户编号';
comment on column test_bob.bob_name    is '鲍勃名称';
comment on column test_bob.bob_category    is '鲍勃类别编码';
comment on column test_bob.order_num    is '显示顺序';
comment on column test_bob.leader       is '负责人';
comment on column test_bob.phone        is '联系电话';
comment on column test_bob.email        is '邮箱';
comment on column test_bob.status       is '鲍勃状态';
comment on column test_bob.del_flag     is '删除标志';
comment on column test_bob.create_bob  is '创建鲍勃';
comment on column test_bob.create_by    is '创建者';
comment on column test_bob.create_time  is '创建时间';
comment on column test_bob.update_by    is '更新者';
comment on column test_bob.update_time  is '更新时间';


三、未来扩展性分析（按业务维度）

下面分析不同业务引入后你的结构是否支持。

1）外卖扩展

外卖核心关键字段：

配送范围

起送价

配送费

配送时间

商家地点

✔ 商品体系：适配
✔ SKU 库存：适配 daily_limit 和 infinite 模式
✔ 订单：需增加配送字段（extend_json）
✔ 活动：OK

→ 可支持

2）旅游 & 门票

门票关键：

有效期

验证方式（二维码）

一票多用

是否实名

→ 建议全部放 extend_json 中
→ SKU infinite 库存模式

→ 你的设计完全能支持

3）吃喝玩乐（服务券、代金券）

服务型商品往往：

无库存

需要预约时间

可退款规则不同

→ 你的结构完全能支持
→ 订单需要 extend_json

4）秒杀

秒杀需要：

活动库存

秒杀价格

限购

高并发库存扣减

你的 activity + activity_product 正好完美支持秒杀：

秒杀商品=一种特殊 activity

秒杀价 = activity_product.group_price

秒杀库存 = stock_quantity

限购 = limit_per_user

唯一需要加强：

秒杀库存扣减必须使用 PostgreSQL 行级锁或函数

推荐使用：

UPDATE ecom_product_activity
SET stock_quantity = stock_quantity - 1
WHERE activity_product_id = ?
AND stock_quantity > 0;
