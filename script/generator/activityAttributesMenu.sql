-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840213031288833, '活动属性', '3', '1', 'activityAttributes', 'ecom/activityAttributes/index', 1, 0, 'C', '0', '0', 'ecom:activityAttributes:list', '#', 103, 1, now(), null, null, '活动属性菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840213031288834, '活动属性查询', 2012840213031288833, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activityAttributes:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840213031288835, '活动属性新增', 2012840213031288833, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activityAttributes:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840213031288836, '活动属性修改', 2012840213031288833, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activityAttributes:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840213031288837, '活动属性删除', 2012840213031288833, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activityAttributes:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840213031288838, '活动属性导出', 2012840213031288833, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activityAttributes:export',       '#', 103, 1, now(), null, null, '');

-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209361272833, '活动', '3', '1', 'activity', 'ecom/activity/index', 1, 0, 'C', '0', '0', 'ecom:activity:list', '#', 103, 1, now(), null, null, '活动菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209361272834, '活动查询', 2012840209361272833, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activity:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209361272835, '活动新增', 2012840209361272833, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activity:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209361272836, '活动修改', 2012840209361272833, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activity:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209361272837, '活动删除', 2012840209361272833, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activity:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209361272838, '活动导出', 2012840209361272833, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:activity:export',       '#', 103, 1, now(), null, null, '');



-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208526606337, '商品品牌', '3', '1', 'brand', 'ecom/brand/index', 1, 0, 'C', '0', '0', 'ecom:brand:list', '#', 103, 1, now(), null, null, '商品品牌菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208526606338, '商品品牌查询', 2012840208526606337, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:brand:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208526606339, '商品品牌新增', 2012840208526606337, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:brand:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208526606340, '商品品牌修改', 2012840208526606337, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:brand:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208526606341, '商品品牌删除', 2012840208526606337, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:brand:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208526606342, '商品品牌导出', 2012840208526606337, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:brand:export',       '#', 103, 1, now(), null, null, '');



-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840207935209474, '履约单', '3', '1', 'fulfillmentTask', 'ecom/fulfillmentTask/index', 1, 0, 'C', '0', '0', 'ecom:fulfillmentTask:list', '#', 103, 1, now(), null, null, '履约单菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840207935209475, '履约单查询', 2012840207935209474, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:fulfillmentTask:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840207935209476, '履约单新增', 2012840207935209474, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:fulfillmentTask:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840207935209477, '履约单修改', 2012840207935209474, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:fulfillmentTask:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840207935209478, '履约单删除', 2012840207935209474, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:fulfillmentTask:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840207935209479, '履约单导出', 2012840207935209474, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:fulfillmentTask:export',       '#', 103, 1, now(), null, null, '');



-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208644046850, '拼团团员', '3', '1', 'groupMember', 'ecom/groupMember/index', 1, 0, 'C', '0', '0', 'ecom:groupMember:list', '#', 103, 1, now(), null, null, '拼团团员菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208644046851, '拼团团员查询', 2012840208644046850, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupMember:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208644046852, '拼团团员新增', 2012840208644046850, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupMember:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208644046853, '拼团团员修改', 2012840208644046850, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupMember:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208644046854, '拼团团员删除', 2012840208644046850, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupMember:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840208644046855, '拼团团员导出', 2012840208644046850, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupMember:export',       '#', 103, 1, now(), null, null, '');




-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209164140546, '团购参团记录', '3', '1', 'groupRecord', 'ecom/groupRecord/index', 1, 0, 'C', '0', '0', 'ecom:groupRecord:list', '#', 103, 1, now(), null, null, '团购参团记录菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209164140547, '团购参团记录查询', 2012840209164140546, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupRecord:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209164140548, '团购参团记录新增', 2012840209164140546, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupRecord:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209164140549, '团购参团记录修改', 2012840209164140546, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupRecord:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209164140550, '团购参团记录删除', 2012840209164140546, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupRecord:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209164140551, '团购参团记录导出', 2012840209164140546, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:groupRecord:export',       '#', 103, 1, now(), null, null, '');



-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212112736258, '商品分类', '3', '1', 'i18n', 'ecom/i18n/index', 1, 0, 'C', '0', '0', 'ecom:i18n:list', '#', 103, 1, now(), null, null, '商品分类菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212112736259, '商品分类查询', 2012840212112736258, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:i18n:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212112736260, '商品分类新增', 2012840212112736258, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:i18n:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212112736261, '商品分类修改', 2012840212112736258, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:i18n:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212112736262, '商品分类删除', 2012840212112736258, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:i18n:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840212112736263, '商品分类导出', 2012840212112736258, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:i18n:export',       '#', 103, 1, now(), null, null, '');





-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210078498818, '法律责任', '3', '1', 'legalResponsibility', 'ecom/legalResponsibility/index', 1, 0, 'C', '0', '0', 'ecom:legalResponsibility:list', '#', 103, 1, now(), null, null, '法律责任菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210078498819, '法律责任查询', 2012840210078498818, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:legalResponsibility:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210078498820, '法律责任新增', 2012840210078498818, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:legalResponsibility:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210078498821, '法律责任修改', 2012840210078498818, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:legalResponsibility:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210078498822, '法律责任删除', 2012840210078498818, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:legalResponsibility:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210078498823, '法律责任导出', 2012840210078498818, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:legalResponsibility:export',       '#', 103, 1, now(), null, null, '');

-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210799919106, '物流跟踪记录', '3', '1', 'logisticsHistory', 'ecom/logisticsHistory/index', 1, 0, 'C', '0', '0', 'ecom:logisticsHistory:list', '#', 103, 1, now(), null, null, '物流跟踪记录菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210799919107, '物流跟踪记录查询', 2012840210799919106, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:logisticsHistory:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210799919108, '物流跟踪记录新增', 2012840210799919106, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:logisticsHistory:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210799919109, '物流跟踪记录修改', 2012840210799919106, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:logisticsHistory:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210799919110, '物流跟踪记录删除', 2012840210799919106, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:logisticsHistory:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210799919111, '物流跟踪记录导出', 2012840210799919106, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:logisticsHistory:export',       '#', 103, 1, now(), null, null, '');

-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209554210818, '配送状态变更日志', '3', '1', 'orderDeliveryLog', 'ecom/orderDeliveryLog/index', 1, 0, 'C', '0', '0', 'ecom:orderDeliveryLog:list', '#', 103, 1, now(), null, null, '配送状态变更日志菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209554210819, '配送状态变更日志查询', 2012840209554210818, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDeliveryLog:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209554210820, '配送状态变更日志新增', 2012840209554210818, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDeliveryLog:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209554210821, '配送状态变更日志修改', 2012840209554210818, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDeliveryLog:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209554210822, '配送状态变更日志删除', 2012840209554210818, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDeliveryLog:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840209554210823, '配送状态变更日志导出', 2012840209554210818, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDeliveryLog:export',       '#', 103, 1, now(), null, null, '');

-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210539872257, '订单配送专用', '3', '1', 'orderDelivery', 'ecom/orderDelivery/index', 1, 0, 'C', '0', '0', 'ecom:orderDelivery:list', '#', 103, 1, now(), null, null, '订单配送专用菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210539872258, '订单配送专用查询', 2012840210539872257, '1',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDelivery:query',        '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210539872259, '订单配送专用新增', 2012840210539872257, '2',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDelivery:add',          '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210539872260, '订单配送专用修改', 2012840210539872257, '3',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDelivery:edit',         '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210539872261, '订单配送专用删除', 2012840210539872257, '4',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDelivery:remove',       '#', 103, 1, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2012840210539872262, '订单配送专用导出', 2012840210539872257, '5',  '#', '', 1, 0, 'F', '0', '0', 'ecom:orderDelivery:export',       '#', 103, 1, now(), null, null, '');

