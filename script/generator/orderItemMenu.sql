-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208971202561, '订单明细', '3', '1', 'orderItem', 'ecom/orderItem/index', 1, 0, 'C', '0', '0', 'ecom:orderItem:list', '#', 103, 1, now(), null, null, '订单明细菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208971202562, '订单明细查询', 2012840208971202561, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderItem:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208971202563, '订单明细新增', 2012840208971202561, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderItem:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208971202564, '订单明细修改', 2012840208971202561, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderItem:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208971202565, '订单明细删除', 2012840208971202561, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderItem:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208971202566, '订单明细导出', 2012840208971202561, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderItem:export',       '#', 103, 1, now(), null, null, '');

-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208774070273, '订单变更动态日志', '3', '1', 'orderLog', 'ecom/orderLog/index', 1, 0, 'C', '0', '0', 'ecom:orderLog:list', '#', 103, 1, now(), null, null, '订单变更动态日志菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208774070274, '订单变更动态日志查询', 2012840208774070273, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderLog:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208774070275, '订单变更动态日志新增', 2012840208774070273, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderLog:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208774070276, '订单变更动态日志修改', 2012840208774070273, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderLog:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208774070277, '订单变更动态日志删除', 2012840208774070273, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderLog:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208774070278, '订单变更动态日志导出', 2012840208774070273, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderLog:export',       '#', 103, 1, now(), null, null, '');



-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211592642561, '订单', '3', '1', 'order', 'ecom/order/index', 1, 0, 'C', '0', '0', 'ecom:order:list', '#', 103, 1, now(), null, null, '订单菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211592642562, '订单查询', 2012840211592642561, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:order:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211592642563, '订单新增', 2012840211592642561, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:order:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211592642564, '订单修改', 2012840211592642561, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:order:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211592642565, '订单删除', 2012840211592642561, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:order:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211592642566, '订单导出', 2012840211592642561, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:order:export',       '#', 103, 1, now(), null, null, '');



-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212704133122, '门票/服务专用', '3', '1', 'orderTicket', 'ecom/orderTicket/index', 1, 0, 'C', '0', '0', 'ecom:orderTicket:list', '#', 103, 1, now(), null, null, '门票/服务专用菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212704133123, '门票/服务专用查询', 2012840212704133122, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderTicket:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212704133124, '门票/服务专用新增', 2012840212704133122, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderTicket:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212704133125, '门票/服务专用修改', 2012840212704133122, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderTicket:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212704133126, '门票/服务专用删除', 2012840212704133122, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderTicket:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212704133127, '门票/服务专用导出', 2012840212704133122, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderTicket:export',       '#', 103, 1, now(), null, null, '');




-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212309868546, '活动商品关联', '3', '1', 'productActivity', 'ecom/productActivity/index', 1, 0, 'C', '0', '0', 'ecom:productActivity:list', '#', 103, 1, now(), null, null, '活动商品关联菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212309868547, '活动商品关联查询', 2012840212309868546, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productActivity:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212309868548, '活动商品关联新增', 2012840212309868546, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productActivity:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212309868549, '活动商品关联修改', 2012840212309868546, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productActivity:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212309868550, '活动商品关联删除', 2012840212309868546, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productActivity:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212309868551, '活动商品关联导出', 2012840212309868546, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productActivity:export',       '#', 103, 1, now(), null, null, '');




-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211395510274, '商品分类', '3', '1', 'productCategory', 'ecom/productCategory/index', 1, 0, 'C', '0', '0', 'ecom:productCategory:list', '#', 103, 1, now(), null, null, '商品分类菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211395510275, '商品分类查询', 2012840211395510274, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productCategory:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211395510276, '商品分类新增', 2012840211395510274, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productCategory:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211395510277, '商品分类修改', 2012840211395510274, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productCategory:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211395510278, '商品分类删除', 2012840211395510274, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productCategory:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211395510279, '商品分类导出', 2012840211395510274, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productCategory:export',       '#', 103, 1, now(), null, null, '');

-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212507000833, '机构组织商品销售配置实体', '3', '1', 'productOrg', 'ecom/productOrg/index', 1, 0, 'C', '0', '0', 'ecom:productOrg:list', '#', 103, 1, now(), null, null, '机构组织商品销售配置实体菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212507000834, '机构组织商品销售配置实体查询', 2012840212507000833, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productOrg:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212507000835, '机构组织商品销售配置实体新增', 2012840212507000833, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productOrg:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212507000836, '机构组织商品销售配置实体修改', 2012840212507000833, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productOrg:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212507000837, '机构组织商品销售配置实体删除', 2012840212507000833, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productOrg:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212507000838, '机构组织商品销售配置实体导出', 2012840212507000833, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productOrg:export',       '#', 103, 1, now(), null, null, '');




-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212897071106, '商品SKU属性', '3', '1', 'productSkuAttributes', 'ecom/productSkuAttributes/index', 1, 0, 'C', '0', '0', 'ecom:productSkuAttributes:list', '#', 103, 1, now(), null, null, '商品SKU属性菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212897071107, '商品SKU属性查询', 2012840212897071106, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSkuAttributes:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212897071108, '商品SKU属性新增', 2012840212897071106, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSkuAttributes:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212897071109, '商品SKU属性修改', 2012840212897071106, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSkuAttributes:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212897071110, '商品SKU属性删除', 2012840212897071106, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSkuAttributes:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212897071111, '商品SKU属性导出', 2012840212897071106, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSkuAttributes:export',       '#', 103, 1, now(), null, null, '');




-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211198377986, 'SKU库存单元', '3', '1', 'productSku', 'ecom/productSku/index', 1, 0, 'C', '0', '0', 'ecom:productSku:list', '#', 103, 1, now(), null, null, 'SKU库存单元菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211198377987, 'SKU库存单元查询', 2012840211198377986, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSku:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211198377988, 'SKU库存单元新增', 2012840211198377986, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSku:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211198377989, 'SKU库存单元修改', 2012840211198377986, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSku:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211198377990, 'SKU库存单元删除', 2012840211198377986, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSku:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840211198377991, 'SKU库存单元导出', 2012840211198377986, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSku:export',       '#', 103, 1, now(), null, null, '');


-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210279825409, 'SPU标准产品单元', '3', '1', 'productSpu', 'ecom/productSpu/index', 1, 0, 'C', '0', '0', 'ecom:productSpu:list', '#', 103, 1, now(), null, null, 'SPU标准产品单元菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210279825410, 'SPU标准产品单元查询', 2012840210279825409, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSpu:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210279825411, 'SPU标准产品单元新增', 2012840210279825409, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSpu:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210279825412, 'SPU标准产品单元修改', 2012840210279825409, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSpu:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210279825413, 'SPU标准产品单元删除', 2012840210279825409, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSpu:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210279825414, 'SPU标准产品单元导出', 2012840210279825409, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:productSpu:export',       '#', 103, 1, now(), null, null, '');


-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210997051394, '退款', '3', '1', 'refund', 'ecom/refund/index', 1, 0, 'C', '0', '0', 'ecom:refund:list', '#', 103, 1, now(), null, null, '退款菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210997051395, '退款查询', 2012840210997051394, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:refund:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210997051396, '退款新增', 2012840210997051394, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:refund:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210997051397, '退款修改', 2012840210997051394, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:refund:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210997051398, '退款删除', 2012840210997051394, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:refund:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210997051399, '退款导出', 2012840210997051394, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:refund:export',       '#', 103, 1, now(), null, null, '');

-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208262365186, '结算', '3', '1', 'settlement', 'ecom/settlement/index', 1, 0, 'C', '0', '0', 'ecom:settlement:list', '#', 103, 1, now(), null, null, '结算菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208262365187, '结算查询', 2012840208262365186, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:settlement:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208262365188, '结算新增', 2012840208262365186, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:settlement:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208262365189, '结算修改', 2012840208262365186, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:settlement:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208262365190, '结算删除', 2012840208262365186, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:settlement:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208262365191, '结算导出', 2012840208262365186, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:settlement:export',       '#', 103, 1, now(), null, null, '');

-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209688428546, '库存流水', '3', '1', 'stockFlow', 'ecom/stockFlow/index', 1, 0, 'C', '0', '0', 'ecom:stockFlow:list', '#', 103, 1, now(), null, null, '库存流水菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209688428547, '库存流水查询', 2012840209688428546, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stockFlow:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209688428548, '库存流水新增', 2012840209688428546, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stockFlow:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209688428549, '库存流水修改', 2012840209688428546, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stockFlow:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209688428550, '库存流水删除', 2012840209688428546, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stockFlow:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209688428551, '库存流水导出', 2012840209688428546, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stockFlow:export',       '#', 103, 1, now(), null, null, '');



-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209885560833, '通用库存', '3', '1', 'stock', 'ecom/stock/index', 1, 0, 'C', '0', '0', 'ecom:stock:list', '#', 103, 1, now(), null, null, '通用库存菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209885560834, '通用库存查询', 2012840209885560833, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stock:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209885560835, '通用库存新增', 2012840209885560833, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stock:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209885560836, '通用库存修改', 2012840209885560833, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stock:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209885560837, '通用库存删除', 2012840209885560833, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stock:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209885560838, '通用库存导出', 2012840209885560833, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:stock:export',       '#', 103, 1, now(), null, null, '');



-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2021426911751434241, '用户地址关联', '3', '1', 'userAddress', 'ecom/userAddress/index', 1, 0, 'C', '0', '0', 'ecom:userAddress:list', '#', 103, 1, now(), null, null, '用户地址关联菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2021426911751434242, '用户地址关联查询', 2021426911751434241, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:userAddress:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2021426911751434243, '用户地址关联新增', 2021426911751434241, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:userAddress:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2021426911751434244, '用户地址关联修改', 2021426911751434241, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:userAddress:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2021426911751434245, '用户地址关联删除', 2021426911751434241, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:userAddress:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2021426911751434246, '用户地址关联导出', 2021426911751434241, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:userAddress:export',       '#', 103, 1, now(), null, null, '');
