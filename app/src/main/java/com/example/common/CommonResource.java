package com.example.common;

public class CommonResource {
    //public static final String BASEURL_8089 = "http://192.168.0.125:8090";

    public static final String BASEURL_8089 = "http://121.40.146.147:8095";

    //登录
    public static final String LOGIN = "/public/index.php/api/login/login";
    //注册
    public static final String USERREG = "/public/index.php/api/login/registerCommit";
    //获取验证码
    public static final String SENDSMS = "/public/index.php/api/login/sendSms";
    //退出登录
    public static final String LOGOUT = "/public/index.php/api/user/logout";
    // 忘记密码
    public static final String FORGETPASSWORD = "/public/index.php/api/login/forgetPassword";
    //  获取推荐人信息
    public static final String RECOMMENDINFO = "/public/index.php/api/login/recommendInfo";
    // 注册协议
    public static final String TREATY = "/public/index.php/api/login/treaty";
    //公告信息
    public static final String GETNOTICE = "/public/index.php/api/pet/getNotice";
    //首页轮播图
    public static final String BANNER = "/public/index.php/api/index/bannerlist";
    //市场列表
    public static final String PETLIST = "/public/index.php/api/pet/petList";
    //获取个人信息
    public static final String USERINFO = "/public/index.php/api/user/userInfo";
    //修改个人信息
    public static final String USERINFOCOMMIT = "/public/index.php/api/user/userInfoCommit";
    //base64图片上传
    public static final String BASEUPLOAD = "/public/index.php/api/upload/baseUpload";
    //
    public static final String NEWVERSION = "/public/index.php/api/user/newVersion";
    //修改密码
    public static final String UPPWD = "/public/index.php/api/user/resetPassword";
    //修改支付密码
    public static final String UPPAYPWD = "/public/index.php/api/user/resetPayPassword";
    //实名认证
    public static final String MINESURE = "/public/index.php/api/user/identification";
    //获取会员等级
    public static final String RANKLIST = "/public/index.php/api/user/rankList";
    //  代售出
    public static final String SALE = "/public/index.php/api/pet/onSaleList";
    //转让列表 已完成 申诉
    public static final String SALELIST = "/public/index.php/api/pet/saleList";
    //发件箱  获取条目
    public static final String FAJIANXIANG = "/public/index.php/api/mail/userSend";
    //发送邮件
    public static final String SENDEMAIL = "/public/index.php/api/mail/sendMail";
    //收件箱列表
    public static final String SHUJIANGXIANG = "/public/index.php/api/mail/userReceive";
    //发件箱类目
    public static final String FALEIMU = "/public/index.php/api/mail/categoryList";
    //交易记录
    public static final String JIAOYILIST = "/public/index.php/api/user/billList";
    //  已领养记录
    public static final String LINGYANG = "/public/index.php/api/pet/pets";
    //  领养记录 申诉
    public static final String LINGZHONG = "/public/index.php/api/pet/Adoption";
    //预约记录
    public static final String APPOINTLIST = "/public/index.php/api/pet/appointList";
    //用户资产
    public static final String USERACCOUNT = "/public/index.php/api/user/userAccount";
    //分享注册地址
    public static final String RETURNREGISTER = "/public/index.php/api/user/returnRegister";
    // 转盘设置
    public static final String WHEELCONFIG = "/public/index.php/api/activity/wheel_config";
    //收款方式提交
    public static final String DATHERCOMMIT = "/public/index.php/api/user/gatheringCommit";
    //大转盘抽奖结果
    public static final String WHEELDO = "/public/index.php/api/activity/wheel_do";
    // 抽奖记录
    public static final String WHEELRECOED = "/public/index.php/api/activity/wheel_record";
    //获取支付信息  /
    public static final String GATHERING = "/public/index.php/api/user/gathering";
    //获取实际支付金额
    public static final String RUELMONEY = "/public/index.php/api/Remittance/getRechargeMoney";
    //汇款申请
    public static final String PAYCOMMIT = "/public/index.php/api/Remittance/remittanceCommit";
    //验证支付密码
    public static final String CHECKPASSWORD = "/public/index.php/api/user/checkPayPassword";
    //获取用户昵称
    public static final String GETNICKNAME = "/public/index.php/api/user/getNickname";
    //微分转出
    public static final String TRANSFERCOMMIT = "/public/index.php/api/Transfer/transferCommit";
    ///api/user/totalInfo  我的团队
    public static final String MYTEAMLIST = "/public/index.php/api/user/teamInfo";
    //兑换宠物列表
    public static final String CONVERLIST = "/public/index.php/api/convert/convertList";
    //获取抢购结果
    public static final String ROBRESULT = "/public/index.php/api/pet/robResult";
    //  预约宠物
    public static final String APPOINT = "/public/index.php/api/pet/appoint";
    //抢购宠物
    public static final String ADDORDER = "/public/index.php/api/pet/addOrder";
    //卖家确认订单
    public static final String CONFIRM = "/public/index.php/api/pet/confirm";
    //买家上传支付凭证
    public static final String PAY = "/public/index.php/api/pet/pay";
    //订单详情
    public static final String ORDER_DETAIL = "/public/index.php/api/pet/orderInfo";
    //兑换宠物需要jine
    public static final String GETPRICE = "/public/index.php/api/convert/getPrice";
    // 兑换宠物
    public static final String CONVER = "/public/index.php/api/convert/convert";
    //我的兑换宠物列表  /api/convert/convertRecord
    public static final String CONVERRECORD = "/public/index.php/api/convert/convertRecord";
    //宠物提前售出
    public static final String SELL = "/public/index.php/api/pet/sell";
    //卖家申诉
    public static final String APPEALSELL = "/public/index.php/api/pet/appeal";
    //背景音乐
    public static final String MUSIC = "/public/index.php/api/lead/gbm";
    //获取新版本
    public static final String CHECKVERSION = "/public/index.php/api/lead/newVersion";
    //玩法介绍
    public static final String INTRODUCE="/public/index.php/api/index/introduce";
    //首页广告 /public/index.php/api/lead/config
    public static final String CONFIG="/public/index.php/api/lead/config";


    public static final String WXAPPID = "wxf08fd2965ac9ac30";  //本：wxf08fd2965ac9ac30  2：wx7df9caffc7db4493
    public static final String CODE_SUCCESS = "0";  //联网成功
    public static final String TOKEN_EXPIRE = "2";    //token过期
    public static final String ERROR = "ERROR";
    public static final String USER_BACK = "USER_BACK";
    public static final String JUMP_CLASSIFY = "JUMP_CLASSIFY";
    public static final String JUMP_CART = "JUMP_CART";
    public static final String JUMP_LOCAL_SHOP = "JUMP_LOCAL_SHOP";
    public static final String JUMP_OPERATOR = "JUMP_OPERATOR";
    public static final String TOKEN = "token";
    public static final String MERMER = "mermer";
    public static final String PHONE = "phone";
    public static final String USER_NAME = "name";
    public static final String USER_PIC = "head";
    public static final String USER_INVITE = "userInvite";
    public static final String USER_PHONE = "userPhone";
    public static final String BACKBL = "back";     //佣金比例
    public static final String LEVELID = "levelId";
    public static final String LEVELIDENDTIME = "levelEndTime";
    public static final String FRISTFANSNUM = "firstFansNum";
    public static final String U_APPKEY = "5d0c57294ca35786440001c6";
    public static final String HISTORY_USER = "user";
    public static final String HISTORY_TBK = "tbk";
    public static final String HISTORY_LOCAL = "local";
    public static final String PAYSUCCESS_OPERATOR = "paySuccess";
    public static final String WXPAY_CANCEL = "wxpay_cancel";   //微信支付  取消支付
    public static final String WXPAY_SUCCESS = "wxpay_success"; //礼包微信支付成功
    public static final String WXPAY_SUCCESS_UP = "wxpay_up";   //金银铜微信支付成功
    public static final String WXPAY_SUCCESS_LOCAL = "wxpay_local"; //本地商城微信支付成功
    public static final String WXPAY_SUCCESS_NET = "wxpay_net"; //本地商城微信支付成功
    public static final String CART_REFRESH = "cartRefresh";        //从购物车的商品推荐进入商品详情，又跳到购物车时刷新购物车
    public static final String NETCHANGED = "net_changed";      //网络发生变化，重新定位
    public static final String CITY = "city";      //网络发生变化，重新定位
    public static final String TAN_CONTENT = "tan_content";     //粘贴板内容
    public static final String ISTAN = "isTan";     //粘贴板内容是否弹过popupwindow
    public static final String SELLERID = "sellerId";   //店铺ID
    public static final String SELLERNAME = "sellerName";   //店铺名字
    public static final String SUBMIT_ORDER = "submitOrder";    //提交订单
    public static final String DINGWEI = "dingwei";
    public static final String UPCART = "up_cart";      //刷新购物车
    public static final String PROJECTNAME = "枫林淘客";
    public static final String PRICE = "totalAmount";
    public static final String VIPQUANYI = "membershipDiscount";
    public static final String RATIO = "ratio";     //积分兑换比例
    public static final String OPENMUSIC = "openMusic";     //是否开启音乐
    public static final String MUSICURL = "music_url";      //音乐链接


    public static final String UUID = "uuid";
    public static final String HEADIMAGE = "head_image";
    public static final String MENBER = "member";
    public static final String NICKNAME = "nick_name";
    public static final String PARENTNICKNAME = "parent_nick_name";
    public static final String PARENTNAME = "parent_name";
    public static final String PARENTMENBER = "parent_member";
    public static final String NAME = "name";
    public static final String CARD = "card";
    public static final String SHOUKUAN = "shoukuan";
    public static final String SHOUKUANFANGSHI = "shoukuanfangshi";
    public static final String QIANGGOU = "qianggou";
}
