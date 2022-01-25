package com.example.spider.core.dao.mapper;

import com.example.spider.bean.HouseBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {
	int batchInsert(List<HouseBean> tasks);
}
