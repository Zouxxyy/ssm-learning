package com.zouxxyy.ssm.mapper;

import com.zouxxyy.ssm.po.ItemsCustom;
import com.zouxxyy.ssm.po.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustom {
    //商品查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}

