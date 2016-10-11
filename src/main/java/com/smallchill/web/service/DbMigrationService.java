package com.smallchill.web.service;

/**
 * 数据迁移
 * Created by Administrator on 2016/10/10 0010.
 */
public interface DbMigrationService {
    // 表处理方式
    // actcomment --> 保留
    // 因为活动模块取消，但是老APP版本还在，所以改变保留但是停止更新
    //=============================
    // activities --> 保留
    // 因为活动模块取消，但是老APP版本还在，所以改变保留但是停止更新
    //=============================
    // acttype --> 保留
    // 因为活动模块取消，但是老APP版本还在，所以改变保留但是停止更新
    //=============================
    // city --> 保留
    //=============================
    // coinlog --> 保留
    // 字段修改规范
    // groupId --> group_id
    // stuUserId --> stuuser_id
    // createTime : longtext --> long
    //=============================
    // feedback --> 保留
    // backTime --> createTime : varchar --> long
    //=============================
    // filtration --> 归入dict表
    // friend --> 保留
    // 新增ID主键
    // stuUserId--> stuuser_id : 取消主键，设置索引
    // friendId --> friend_id: 取消主键，设置索引
    //=============================
    // gift --> 保留
    // createTime --> create_time: varchar --> long
    // 去掉qrCode字段
    // modifyTime --> modify_time : varchar --> long
    //=============================
    // gift_exachange --> 保留
    // create_time: varchar --> long
    //=============================
    // groupinfo --> 保留
    // 新增ID主键,还信返回ID作为字段设置索引
    // scaleId -- > quantity 直接保存数量
    // groupType --> group_type
    // provinceId --> province_id
    // cityId --> city_id
    // schoolId --> school_id
    // stuuserId --> stuuser_id
    // createTime --> create_time : longtext --> long
    // 取消qrCode
    //=============================
    // grouplivess --> 保留
    // 新增主键ID
    // groupId --> group_id
    // speakNum --> speak_quantity
    // stuUserId --> stuuser_id
    // createTime --> create_time : longtext --> long
    // 暂时没有好的办法保存用户在每个群的聊天数量能够实现高效率
    //=============================
    // groupmember --> 保留
    // 新增主键ID,group_id,stuuser_id 改为索引
    // groupId --> group_id
    // stuuserId --> stuuser_ids 保存所有加入群的用户
    // 当天是否签到，月签到数如何保存?
    //=============================
    // groupscale --> 归入dict表
    //=============================
    // grouptype --> 归入dict表
    //=============================
    // interests --> 归入dict表
    //=============================
    // lables --> 归入dict表
    //=============================
    // placard --> 保留
    // type --> isopen
    //
    //=============================
    // placard_record --> 保留
    // user_id --> user_ids 保存所有阅读该公告的用户
    //=============================
    // post --> 保留
    // post_bar --> 保留
    // post_bar_permissions --> 删除
    // post_comment --> 保留
    // post_image --> 保留
    // post_praise --> 保留
    // user_id --> user_ids
    //=============================
    // praise --> 保留
    // 因为活动模块取消，但是老APP版本还在，所以改变保留但是停止更新
    //=============================
    // province --> 保留
    //=============================
    // report --> 保留
    // 新增是否处理
    //=============================
    // sacollect --> 保留
    // 因为活动模块取消，但是老APP版本还在，所以改变保留但是停止更新
    //=============================
    // sajoin --> 保留
    // 因为活动模块取消，但是老APP版本还在，所以改变保留但是停止更新
    //=============================
    // scs --> 保留
    //=============================
    // stugroup --> 删除
    //=============================
    // stuinterests --> 保留
    // 新增ID主键
    // stuuserId --> stuuser_id
    // interestId --> interest_ids
    //=============================
    // stulables --> 保留
    // stuuserId --> stuuser_id
    // labelId --> labelsId
    //=============================
    // stuuser --> 保留
    // 去掉qrcode
    // 去掉是否在线
    //=============================
    // task --> 保留
    //=============================
    // task_record --> 保留
    //=============================
    // task_rep --> 保留
    //=============================
    // task_type --> 保留
    //=============================
    // tb_config --> 归入dict表
    //=============================
    // users --> 保留
    //=============================
    // yeas --> 归入dict表
}
