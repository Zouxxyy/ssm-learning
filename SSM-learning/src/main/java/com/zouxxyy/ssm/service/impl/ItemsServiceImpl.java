package com.zouxxyy.ssm.service.impl;

import com.zouxxyy.ssm.mapper.ItemsMapper;
import com.zouxxyy.ssm.mapper.ItemsMapperCustom;
import com.zouxxyy.ssm.po.Items;
import com.zouxxyy.ssm.po.ItemsCustom;
import com.zouxxyy.ssm.po.ItemsQueryVo;
import com.zouxxyy.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Autowired
    private ItemsMapper itemsMapper;


    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        // 通过ItemsMapperCustom查询数据库
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items = itemsMapper.selectByPrimaryKey(id);
        // 业务处理
        // ...
        // 返回ItemsCustom
        ItemsCustom itemsCustom = null;
        // 将items拷贝到itemsCustom中
        if(items != null) {
            itemsCustom = new ItemsCustom();
            BeanUtils.copyProperties(items, itemsCustom);
        }
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        // 添加业务校验， 如id是否为空
        // 使用它可以更新所有字段，包括大文本类型
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}
