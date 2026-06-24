package com.dtdx.dao;

import com.dtdx.entity.EdgeGateway;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EdgeGatewayDao {
    List<EdgeGateway> edgeGatewayList(EdgeGateway edgeGateway);

    EdgeGateway getById(@Param("id") Integer id);

    EdgeGateway getByGatewayCode(@Param("gatewayCode") String gatewayCode);

    void add(EdgeGateway edgeGateway);

    void update(EdgeGateway edgeGateway);

    void delete(@Param("id") Integer id);
}
