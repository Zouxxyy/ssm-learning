package com.zouxxyy.ssm.controller;
import com.zouxxyy.ssm.controller.vaildation.ValidGroup1;
import com.zouxxyy.ssm.exception.CustomException;
import com.zouxxyy.ssm.po.Items;
import com.zouxxyy.ssm.po.ItemsCustom;
import com.zouxxyy.ssm.po.ItemsQueryVo;
import com.zouxxyy.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    // 商品分类
    //itemtypes表示最终将方法返回值放在request中的key
    @ModelAttribute("itemtypes")
    public Map<String, String> getItemTypes() {

        Map<String, String> itemTypes = new HashMap<>();
        itemTypes.put("101", "数码");
        itemTypes.put("102", "母婴");

        return itemTypes;
    }


    // 商品查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItems(ItemsQueryVo itemsQueryVo) throws Exception {
        //调用service查找数据库，查询商品列表，这里使用静态数据模拟
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //相当于request的setAttribute方法,在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);
        //指定视图
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }

    // 商品修改页面展示：根据ID返回商品信息的页面
    // （1）用method 限制请求的方法
    // （2）用RequestParam对请求中的简单参数进行绑定
    // （3）用required指定该参数是否必须
    @RequestMapping(value = "/editItems", method = {RequestMethod.GET, RequestMethod.POST})
    public String editItems(Model model, @RequestParam(value = "id", required = true) Integer items_id) throws Exception {
        // 调用service查询商品信息
        ItemsCustom itemsCustom =  itemsService.findItemsById(items_id);

        // 方式1：返回ModelAndView
        // ModelAndView modelAndView = new ModelAndView();
        // modelAndView.addObject("itemsCustom", itemsCustom);
        // modelAndView.setViewName("items/editItems");
        // return modelAndView;

        // 方式2：返回String

        // 抛异常测试
        if(itemsCustom == null) {
            throw new CustomException("修改的商品不存在！");
        }


        model.addAttribute("items", itemsCustom);
        return "items/editItems";
    }


    // 商品信息提交：根据输入的信息，进行数据库更新
    // 在需要校验的pojo前 加@Validated，后面加BindingResult bindingResult，他们是配对出现。
    // 用@ModelAttribute("items")，设置自动回显
    @RequestMapping(value = "/editItemsSubmit", method = {RequestMethod.GET, RequestMethod.POST})
    public String editItemsSubmit(Model model, Integer id,
                                  @ModelAttribute("items")
                                  @Validated(value = {ValidGroup1.class}) ItemsCustom itemsCustom,
                                  BindingResult bindingResult) throws Exception {
        System.out.println(id);
        // 调用service更新商品信息

        // 获取校验信息
        if(bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for(ObjectError objectError : allErrors) {
                System.out.println(objectError.getDefaultMessage());
            }

            // 将错误信息传到页面，仍然是编辑页面
            model.addAttribute("allErrors", allErrors);

            // 可以直接使用model将提交pojo回显到页面
            // model.addAttribute("items", itemsCustom);
            return "items/editItems";
        }


        itemsService.updateItems(id, itemsCustom);
//        System.out.println(itemsCustom.getPrice());

        // 重定向到列表页面redirect
        return "redirect:queryItems.action";
    }

    // 批量删除商品信息
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception {
        for (int i :items_id) {
            System.out.println(i);
        }
        return "success";
    }

    // 批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo) throws Exception {

        //调用service查找数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //相当于request的setAttribute方法,在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        modelAndView.setViewName("items/editItemsQuery");

        return modelAndView;
    }

    // 批量修改商品提交
    // 通过ItemsQueryVo接收批量提交的商品信息，将商品信息存储到itemsQueryVo中itemsList属性(新加的)中。
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
        for (ItemsCustom itemsCustom: itemsQueryVo.getItemsList()) {
            System.out.println(itemsCustom.getName());
        }
        return "success";
    }

    //查询商品信息，输出json
    //itemsView/{id}里边的{id}表示占位符，通过@PathVariable获取占位符中的参数，
    //@PathVariable中名称要和占位符一致，形参名无需和其一致
    //如果占位符中的名称和形参名一致，在@PathVariable可以不指定名称
    @RequestMapping("/itemsView/{id}")
    public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer items_id)throws Exception{

        //调用service查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
        return itemsCustom;
    }
}
