package com.example.caigouapp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.annotation.UserLoginToken;
import com.example.caigouapp.common.Constant;
import com.example.caigouapp.dao.TagDao;
import com.example.caigouapp.entity.*;
import com.example.caigouapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://106.53.148.37:8082", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    TokenService tokenService;

    @Autowired
    CartService cartService;

    @Autowired
    TagService tagService;


    /**
     * 用户登录
     * @param userDTO
     * @return JSONObject
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody UserDTO userDTO){
        JSONObject jsonObject=new JSONObject();

        User userForBase=userService.findUserByAccount(userDTO.getPhone());

        if(userForBase == null) {
            jsonObject.put("code", Constant.USER_NOTFOUND);
            jsonObject.put("msg","登录失败,用户不存在");
            return jsonObject;
        }
        else {
            if (!userForBase.getPassword().equals(userDTO.getPassword())){
                jsonObject.put("code", Constant.INVALID_PASSWORD);
                jsonObject.put("msg","登录失败,密码错误");
                return jsonObject;
            }
            else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                //jsonObject.remove("user.password");
                return jsonObject;
            }
        }
    }


    /**
     * 用户注册
     * @param userDTO
     * @return JSONObject
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSONObject userRegister(@RequestBody UserDTO userDTO){

        JSONObject jsonObject = new JSONObject();

        //判断用户是否已经存在
        if(userService.findUserByAccount(userDTO.getPhone()) != null) {

            jsonObject.put("msg","用户已存在，请直接登录");
            jsonObject.put("code", Constant.ALLREADY_EXSITED);

            System.out.println("2");
            return jsonObject;
        }
        else {
            User userBase = new User();
            userBase.setPassword(userDTO.getPassword());//用户密码
            userBase.setUser_name(userDTO.getPhone());//用户姓名，默认用手机号
            userBase.setAccount_num(userDTO.getPhone());//用户账号，默认用手机号
            userBase.setAvatar(Constant.DEAFULT_AVATAR);//用户头像，初始使用默认头像
            userBase.setPhone(userDTO.getPhone());//用户手机号
            System.out.println(userBase.toString());

            Cart cart = new Cart();
            cart.setCustom_menuid("");
            userService.addUser(userBase);

            cart.setUser_id(userBase.getId());//获取保存后的用户Id

            cartService.save(cart);//给用户添加购物车

            jsonObject.put("msg", "操作成功");
            jsonObject.put("code",Constant.SUCCESS);
            jsonObject.put("data", userBase);
            return jsonObject;
        }
    }

    /**
     * 查询用户信息
     * /findOneUser/{account}
     * @param account
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/findOneUser/{account}", method = RequestMethod.GET)
    public JSONObject findUserInfo(@PathVariable(value = "account") String account){
        User user = userService.findUserByAccount(account);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("msg", "操作成功");
        jsonObject.put("code",Constant.SUCCESS);
        jsonObject.put("data", user);

        return jsonObject;
    }


    /**
     * 获取用户收货地址
     * @param account
     * @return
     */
    //@UserLoginToken
    @UserLoginToken
    @RequestMapping(value = "/getUserAddress/{account}", method = RequestMethod.GET)
    public JSONObject findUserAddress(@PathVariable("account") String account){

        Integer userId = userService.findUserByAccount(account).getId();
        JSONObject jsonObject = new JSONObject();


        List<Address> addressList = new ArrayList<>();
        //用户不存在
        if(userId == null){
            jsonObject.put("code", Constant.USER_NOTFOUND);
            jsonObject.put("msg","用户不存在");

            return jsonObject;
        }
        else {
            addressList = addressService.findAddressByUserId(userId);

            //未找到该用户的收获地址
            if(addressList.size() == 0){
                jsonObject.put("code", Constant.NOT_FOUND);
                jsonObject.put("msg","该用户没有设置收获地址");
                return jsonObject;
            }
            else {
                jsonObject.put("code", Constant.SUCCESS);
                jsonObject.put("msg", "操作成功");
                jsonObject.put("Address", addressList);

                return jsonObject;
            }
        }
    }

    /**
     *获取用户的标签
     * @param account
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/getUserTags/{account}", method = RequestMethod.GET)
    public JSONObject findTags(@PathVariable("account") String account){
        JSONObject jsonObject =new JSONObject();

        String[] tags = userService.findTagsByAccount(account);

        System.out.println(tags[0]);
        //标签为空
        if(tags[0].equals("empty")){
            jsonObject.put("msg", "用户标签为空");
            return jsonObject;
        }

        //用户未找到
        else if(tags[0].equals(Constant.USER_NOTFOUND)){
            jsonObject.put("code", Constant.USER_NOTFOUND);
            jsonObject.put("msg", "用户未找到……");

            return jsonObject;
        }

        else {
            List<Tag> tagList = new ArrayList<>();
            for (String t : tags){
                Tag tag = new Tag();

                tag = tagService.findTagById(Integer.valueOf(t));

                tagList.add(tag);
            }
            jsonObject.put("code", "200");
            jsonObject.put("msg", "操作成功");
            jsonObject.put("tags", tagList);

            return jsonObject;
        }
    }

    /**
     * 更新用户的tag
     * @param body
     * @return JSON
     */
    @UserLoginToken
    @RequestMapping(value = "/addUserTags", method = RequestMethod.POST)
    public JSONObject setUserTgs(@RequestBody  String body){
        JSONObject par = JSONObject.parseObject(body);

        String account = par.get("account").toString();//获取用户账号
        JSONArray array = par.getJSONArray("tags");

        String[] tags = new String[20];

        for(int i = 0; i < array.size(); i++){
            tags[i] = array.getString(i);
        }
        List list =new ArrayList();
        //清空null
        for(String s:tags){
            if(s!=null){
                list.add(s);
            }
        }

        String[] tags0 = (String[]) list.toArray(new String[list.size()]);

        User user = userService.findUserByAccount(account);
        String userTags = "";
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < tags0.length; i++){
            userTags += tags0[i];
            userTags += ",";//使用“,”分割
        }
        jsonObject.put("code", Constant.SUCCESS);
        jsonObject.put("msg", "操作成功");

        user.setTags(userTags);

        //更新用户信息
        userService.addUser(user);
        return jsonObject;

    }


    /**
     *更新用户的地址信息
     * @param address
     * @return JSONObject
     */
    @UserLoginToken
    @RequestMapping(value = "/updateUserAddress", method = RequestMethod.POST)
    public JSONObject updateAddress(@RequestBody Address address){
        JSONObject jsonObject = new JSONObject();

        addressService.updateAddress(address);
        jsonObject.put("code", Constant.SUCCESS);
        jsonObject.put("msg", "操作成功");

        return jsonObject;
    }

    /**
     * 为用户添加一个地址
     * @param address
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/addUserAddress", method = RequestMethod.POST)
    public JSONObject addAddress(@RequestBody Address address){
        JSONObject jsonObject = new JSONObject();

        Address addressBase = new Address();

        addressBase.setName(address.getName());//获取收货人
        addressBase.setAddress(address.getAddress());//获取收货地址
        addressBase.setPhone(address.getPhone());//获取电话号码
        addressBase.setUser_id(address.getUser_id());//获取用户id

        addressService.updateAddress(addressBase);

        jsonObject.put("code", Constant.SUCCESS);
        jsonObject.put("msg", "操作成功");

        return jsonObject;
    }
}