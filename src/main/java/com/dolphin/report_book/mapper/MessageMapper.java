package com.dolphin.report_book.mapper;
import com.dolphin.report_book.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    /**
     * 添加Message
     */
    int insert(Message message);

    /**
     * 删除Message
     */
    int deleteById(Integer id);

    /**
     * 删除我的Message
     */
    boolean deleteAllByTeacherId(Integer id);

    /**
     * 查询单条数据
     */
    Message getById(Integer id);

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Message> listMessages();

    /**
     * 实体作为筛选条件查询数据
     */
    List<Message> listMessages(Message message);

    /**
     * 实体作为筛选条件获取结果数量
     */
    int countMessages(Message message);

    /**
     * 修改Message, 根据 message 的主键修改数据
     */
    int update(Message message);
}
