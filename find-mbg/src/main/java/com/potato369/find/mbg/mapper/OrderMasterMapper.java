package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.OrderMaster;
import com.potato369.find.mbg.model.OrderMasterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMasterMapper {
    long countByExample(OrderMasterExample example);

    int deleteByExample(OrderMasterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    List<OrderMaster> selectByExample(OrderMasterExample example);

    List<OrderMaster> selectTimeOut(Integer minute);

    OrderMaster selectByPrimaryKey(Long id);

    OrderMaster selectByOrderId(String orderId);

    int updateByExampleSelective(@Param("record") OrderMaster record, @Param("example") OrderMasterExample example);

    int updateByExample(@Param("record") OrderMaster record, @Param("example") OrderMasterExample example);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);
}