项目笔记

# 整体介绍

![Snipaste_2023-05-14_00-25-25](README.assets/Snipaste_2023-05-14_00-25-25.png)

![Snipaste_2023-05-14_00-26-45](README.assets/Snipaste_2023-05-14_00-26-45.png)

![Snipaste_2023-05-14_00-27-50](README.assets/Snipaste_2023-05-14_00-27-50.png)

![image-20230529171754142](README.assets/image-20230529171754142.png)

![image-20230529174713377](README.assets/image-20230529174713377.png)

![image-20230529175626247](README.assets/image-20230529175626247.png)

# 环境搭建

## 数据库

![image-20230529175752640](README.assets/image-20230529175752640.png)

or

![image-20230529175823210](README.assets/image-20230529175823210.png)

### 导入sql

![image-20230529180021006](README.assets/image-20230529180021006.png)

or

![image-20230529180003940](README.assets/image-20230529180003940.png)

命令行方式导入 不要有中文，不然可能失败

### 数据库说明

![image-20230529180257585](README.assets/image-20230529180257585.png)

## Maven项目搭建

> 在 Spring Boot 项目中，将默认网页放置在 `resources/static` 或 `resources/public` 目录下，它们将被作为静态资源自动加载并可以被访问。例如，将 `index.html` 文件放置在 `resources/static` 或 `resources/public` 文件夹下，那么在启动 Spring Boot 项目后，可以通过浏览器访问 `http://localhost:8080/index.html` 来查看该页面。如果将 `index.html` 放在这两个目录之外，则无法正常访问。
>
> **想要别的目录被访问怎么办**
>
> >通过配置类的方式设置
> >
> >```java
> >//仰晨study 创建时间2023/5/30 3:38 星期二
> >package org.ikun.config;
> >
> >import lombok.extern.slf4j.Slf4j;
> >import org.springframework.context.annotation.Configuration;
> >import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
> >import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
> >@Slf4j
> >@Configuration
> >public class WebMvcConfig extends WebMvcConfigurationSupport {
> >    /**
> >     * 设置静态资源映射
> >     * @param registry
> >     * */
> >
> >    @Override
> >    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
> >        log.info("开始静态资源映射");
> >        // 访问backend及下面的路径就映射到resources下面的backend文件夹
> >        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
> >        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
> >    }
> >}
> >
> >```
> >





# 业务开发（登录模块）

![image-20230605020344527](README.assets/image-20230605020344527.png)

![image-20230605171610940](README.assets/image-20230605171610940.png)

![image-20230607012530614](README.assets/image-20230607012530614.png)

# 新增员工

![image-20230607034852189](README.assets/image-20230607034852189.png)

![image-20230607035205053](README.assets/image-20230607035205053.png)

![image-20230607223657201](README.assets/image-20230607223657201.png)

![image-20230607230043113](README.assets/image-20230607230043113.png)

![image-20230607230214007](README.assets/image-20230607230214007.png)

# 员工信息分页查询

![image-20230607230828987](README.assets/image-20230607230828987.png)

![image-20230608010059310](README.assets/image-20230608010059310.png)



# 启or禁用员工账号

![image-20230608025202418](README.assets/image-20230608025202418.png)

![image-20230608032038100](README.assets/image-20230608032038100.png)

## ![image-20230608031950544](README.assets/image-20230608032032752.png)mvc的消息转换器

js里面的long类型16位后会丢失精度，所以我们传过去之前就可以先变成字符串

![image-20230608034128912](README.assets/image-20230608034128912.png)

# 编辑员工信息

![image-20230608162431006](README.assets/image-20230608162431006.png)

# 分类管理业务开发

![image-20230608172252796](README.assets/image-20230608172252796.png)

## 公共字段自动填充

![image-20230609004011245](README.assets/image-20230609004011245.png)

### 代码实现

![image-20230609004139705](README.assets/image-20230609004139705.png)

![image-20230609004819637](README.assets/image-20230609004819637.png)

![image-20230609011426267](README.assets/image-20230609011426267.png)

![image-20230609011631742](README.assets/image-20230609011631742.png)

 ![image-20230609011830469](README.assets/image-20230609011830469.png)

## 新增分类

![image-20230609020605274](README.assets/image-20230609020605274.png)

### 代码开发

![image-20230609021949647](README.assets/image-20230609021949647.png)

### 分页信息分页查询

![image-20230609153412871](README.assets/image-20230609153412871.png)

### 删除分类

![image-20230609160040211](README.assets/image-20230609160040211.png)

![image-20230609160253654](README.assets/image-20230609160253654.png)

![image-20230609161123064](README.assets/image-20230609161123064.png)

# 菜品管理业务开发

![image-20230609175504354](README.assets/image-20230609175504354.png)

## 文件上传下载

![image-20230609175610693](README.assets/image-20230609175610693.png)

![image-20230609175835583](README.assets/image-20230609175835583.png)

```java
//仰晨study 创建时间2023/6/10 0:55 星期六
package org.ikun.controller;

import lombok.extern.slf4j.Slf4j;
import org.ikun.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件的上传和下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${reggit.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file 是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
     */
    @PostMapping("/upload")
    public R<String> upLoad(MultipartFile file) throws IOException {
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));  //后缀名

        log.info("文件上传:"+ originalFilename);

        //UUID生成文件名，防止文件被覆盖
        String fileName = UUID.randomUUID() + suffix;

        //判断目录是否存在
        File dir = new File(basePath);
        if (!dir.exists()) {
            //不存在，要创建
            dir.mkdirs();
        }

        file.transferTo(new File( basePath + fileName));

        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name 文件名
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        //通过输入流读取文件内容
        FileInputStream fileInputStream = new FileInputStream(basePath + name);

        //通过输出流把文件响应给浏览器，在浏览器展示
        ServletOutputStream outputStream = response.getOutputStream();

        response.setContentType("image/jpeg");

        int len;
        byte[] bytes = new byte[1024];
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes,0,len);
        }

        //关闭资源
        fileInputStream.close();
        outputStream.close();
    }

}

```

## 新增菜品

![image-20230610023032902](README.assets/image-20230610023032902.png)

![image-20230610025414410](README.assets/image-20230610025414410.png)

![image-20230610030751374](README.assets/image-20230610030751374.png)

![image-20230611172030205](README.assets/image-20230611172030205.png)

![image-20230611182358106](README.assets/image-20230611182358106.png)



## *菜品信息的分页请求*

```java
/**
     * 菜品信息的分页请求
     * @param page 第几页
     * @param pageSize 每页几条
     * @param name 是否带名称查询
     * @return 列表
     */
    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize, String name) {
        //构造分页构造器对象
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        Page<DishDto> dishDtopage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行分页查询
        dishService.page(pageInfo, queryWrapper);

        //对象拷贝----把pageInfo的属性值拷贝到dishDtoPage但是不拷贝"records"属性
        BeanUtils.copyProperties(pageInfo,dishDtopage,"records");

        List<Dish> records = pageInfo.getRecords();

        //对records属性进行处理，本类是dish然后变成dishDto就有分类名字属性，然后查表赋值
        List<DishDto> list = records.stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId(); //分类ID
            //根据ID查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category!=null) dishDto.setCategoryName(category.getName());
            return dishDto;
        }).collect(Collectors.toList());

        //给上面没拷贝的records属性赋值（records属性在Page这个类里面就是实体类数据）
        dishDtopage.setRecords(list);

        return R.success(dishDtopage);
    }
```





## 修改菜品

![image-20230612023351513](README.assets/image-20230612023351513.png)





# 新增套餐

![image-20230613011405332](README.assets/image-20230613011405332.png)





![image-20230613011557627](README.assets/image-20230613011557627.png)



## 代码开发-准备工作

> 在开发业务功能前，先将需要用到的类和接口基本结构创建好:
> 实体类 SetmealDish (直接从课程资料中导入即可，Setmeal实体前面课程中已经导入过了)DTO SetmealDto (直接从课程资料中导入即可)
> Mapper接口 SetmealDishMapper
> 业务层接口 SetmealDishService
> 业务层实现类 SetmealDishServicelmpl
> 控制层 SetmealController



> **代码开发-梳理交互过程**
> 在开发代码之前，需要梳理一下新增套餐时前端页面和服务端的交互过程:
> 1、页面(backend/page/combo/add.htm)发送ajax请求，请求服务端获取**套餐分类**数据并展示到下拉框中
>
> 2、页面发送ajax请求，请求服务端获取**菜品分类**数据并展示到添加菜品窗口中
>
> 3、页面发送ajax请求，请求服务端，根据菜品分类查询对应的菜品数据并展示到添加菜品窗口中
>
> 4、页面发送请求进行**图片上传**，请求服务端将图片保存到服务器
>
> 5、页面发送请求进行**图片下载**，将上传的图片进行回显
>
> 6、点击保存按钮，发送ajax请求，将**套餐**相关数据以json形式提交到服务端开发新增套餐功能，其实就是在服务端编写代码去处理前端页面发送的这6次请求即可



## 套餐信息分页查询

系统中的套餐数据很多的时候，如果在一个页面中全部展示出来会显得比较乱，不便于查看，所以一般的系统中都会以分页的方式来展示列表数据

![image-20230614020627158](README.assets/image-20230614020627158.png)



## 套餐删除

**需求分析**
在套餐管理列表页面点击删除按钮，可以删除对应的套餐信息。也可以通过复选框选择多个套餐，点击批量删除按钮一次删除多个套餐。注意，对于状态为**售卖中的套餐不能删除**，需要先停售，然后才能删除。

![image-20230614230343041](README.assets/image-20230614230343041.png)



# 短信服务

- 短信发送
 - 短信服务介绍
 - 阿里云短信服务
 - 代码开发



## 短信服务介绍

> 目前市面上有很多第三方提供的短信服务，这些第三方短信服务会和各个运营商(移动、联通、电信)对接，我们只需要注册成为会员并且按照提供的开发文档进行调用就可以发送短信。需要说明的是，这些短信服务一般都是收费服务，
> 常用短信服务:
> 阿里云
> 华为云
> 腾讯云
> 京东
> 梦网
> 乐信
>
> >**阿里云短信服务-介绍**
> >阿里云短信服务(Short Message Service) 是广大企业客户快速触达手机用户所优选使用的通信能力。调用API或用群发助手，即可发送验证码、通知类和营销类短信;国内验证短信秒级触达，到达率最高可达99%;国际/港澳台短信覆盖200多个国家和地区，安全稳定，广受出海企业选用。
> >应用场景:
> >
> >- 验证码
> >
> >- 短信通知
> >
> >- 推广短信
> >
> >>---
> >>
> >>为了方便用户登录，移动端通常都会提供通过手机验证码登录的功能。
> >>手机验证码登录的优点
> >>方便快捷，无需注册，直接登录
> >>使用短信验证码作为登录凭证，无需记忆密码
> >>安全
> >>登录流程
> >>输入手机号>获取验证码>输入验证码>点击登录>登录成功
> >>注意:通过手机验证码登录，手机号是区分不同用户的标识。

>```java
>package org.ikun.utils;
>
>import com.aliyuncs.DefaultAcsClient;
>import com.aliyuncs.IAcsClient;
>import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
>import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
>import com.aliyuncs.exceptions.ClientException;
>import com.aliyuncs.profile.DefaultProfile;
>
>/**
> * 短信发送工具类
> */
>public class SMSUtils {
>
>	/**
>	 * 发送短信
>	 * @param signName 签名
>	 * @param templateCode 模板
>	 * @param phoneNumbers 手机号
>	 * @param param 参数
>	 */
>	public static void sendMessage(String signName, String templateCode,String phoneNumbers,String param){
>		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tGFDcrqx1jpmavr4NrY", "dLICzDYIQZCB1DiCwooJnGXVVGWOpl");
>		IAcsClient client = new DefaultAcsClient(profile);
>
>		SendSmsRequest request = new SendSmsRequest();
>		request.setSysRegionId("cn-hangzhou");
>		request.setPhoneNumbers(phoneNumbers);
>		request.setSignName("阿里云短信测试");//短信签名名称---测试专用签名和模版必须结合使用
>		request.setTemplateCode("SMS_154950909");//测试模板
>		request.setTemplateParam("{\"code\":\""+param+"\"}");
>		try {
>			SendSmsResponse response = client.getAcsResponse(request);
>			System.out.println("短信发送成功");
>		}catch (ClientException e) {
>			e.printStackTrace();
>		}
>	}
>
>}
>
>```





# 菜品展示、购物车、下单

> **导入功能代码**
> 功能代码清单
>
> - 实体类AddressBook (直接从课程资料中导入即可)
>
> - Mapper接口 AddressBookMapper
>
> - 业务层接口 AddressBookService
>
> - 业务层实现类 AddressBookServicelmpl控制层 AddressBookController (直接从课程资料中导入即可)

## 菜品展示

![image-20230616015933928](README.assets/image-20230616015933928.png)



## 购物车

![image-20230616024639775](README.assets/image-20230616024639775.png)

> 代码开发-梳理交互过程
> 在开发代码之前，需要梳理一下购物车操作时前端页面和服务端的交互过程:
>
> 1、点击(加入购物车）或者 （+）按钮，页面发送ajax请求，请求服务端，将菜品或者套餐添加到购物车
>
> 2、点击购物车图标，页面发送ajax请求，请求服务端查询购物车中的菜品和套餐
>
> 3、点击清空购物车按钮，页面发送ajax请求，请求服务端来执行清空购物车操作
>
> 开发购物车功能，其实就是在服务端编写代码去处理前端页面发送的这3次请求即可
>
> >代码开发-准备工作
> >在开发业务功能前，先将需要用到的类和接口基本结构创建好
> >
> >实体类shoppingCart (直接从课程资料中导入即可
> >
> >Mapper接口 ShoppingCartMapper
> >
> >业务层接口 shoppingCartService
> >
> >业务层实现类shoppingCartServicelmpl
> >
> >控制层 ShoppingCartController
> >
> >



## 用户下单

> 代码开发-准备工作
> 在开发业务功能前，先将需要用到的类和接口基本结构创建好:
>
> 实体类 Orders、OrderDetail (直接从课程资料中导入即可)
>
> Mapper接口 OrderMapper、OrderDetailMapper
>
> 业务层接口 OrderService、OrderDetailService
>
> 业务层实现类 OrderServicelmpl、OrderDetailServicelmpl
>
> 控制层 OrderController、OrderDetailController





# Spring Cache

![image-20230815091541278](README.assets/image-20230815091541278.png)

![image-20230815091734828](README.assets/image-20230815091734828.png)





## 缓存套餐数据

**实现思路**

前面我们已经实现了移动端套餐查看功能，对应的服务端方法为SetmealController的list方法，此方法会根据前端提交的查询条件进行数据库查询操作。在高并发的情况下，频繁查询数据库会导致系统性能下降，服务端响应时间增长现在需要对此方法进行缓存优化，提高系统的性能。
具体的实现思路如下:
1、导入Spring Cache和Redis相关maven坐标

2、在application.yml中配置缓存数据的过期时间

3、在启动类上加入@EnableCaching注解，开启缓存注解功能

4、在SetmealController的list方法上加入@Cacheable注解

5、在SetmealController的save和delete方法上加入CacheEvict注解



> <dependency>
>     <groupId>org.springframework.boot</groupId>
>     <artifactId>spring-boot-starter-cache</artifactId>
> </dependency>
>
> <dependency>
>     <groupId>org.springframework.boot</groupId>
>     <artifactId>spring-boot-starter-data-redis</artifactId>
> </dependency>



> redis:
>   host: 127.0.0.1
>   port: 6379
>   password: 123456
>   database: 0
> cache:
>   redis:
>     time-to-live: 1800000 *#设置缓存过期时间，可选*



启动类

> @EnableCaching  //开启缓存注解功能



```diff
 /**
      * 根据套餐分类id给出套餐列表
      */
     @GetMapping("/list")
+    @Cacheable(value = "setmealCache",key = "#setmeal.categoryId+'_'+ #setmeal.status")
     public R<List<Setmeal>> list(Setmeal setmeal) {
         LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
         queryWrapper.eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus());
         queryWrapper.orderByDesc(Setmeal::getUpdateTime);
 
         return R.success(setmealService.list(queryWrapper));
     }
```



```diff
 /**
     * 新增套餐
     */
    @PostMapping
+   @CacheEvict(value = "setmealCache",allEntries = true)
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("新增套餐信息:{}", setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("添加套餐成功");
    }
```



```diff
/**
     * 批量/删除套餐
     * 不能简单就删套餐表，还有菜品关联表，还要判断是不是停售（停售才允许删）
     * @param ids 套餐id（可以多个）
     * @return 简单提示
     */
    @DeleteMapping
+   @CacheEvict(value = "setmealCache",allEntries = true)
    public R<String> delete(@RequestParam("ids")List<Long> ids) {
        log.info("准备要删除的套餐id有:{}",ids);
        setmealService.removeWithDish(ids);
        return R.success("删除套餐成功");
    }
```





# 读写分离

![image-20230816094151356](README.assets/image-20230816094151356.png)







## 主从复制

> MySQL主从复制是一个异步的复制过程，底层是基于Mysql数据库自带的**二进制日志**功能。就是一台或多台MySOL数据库(slave，即**从库**)从另一台MySL数据库(master，即**主库**)进行日志的复制然后再解析日志并应用到自身，最终实现**从库**的数据和**主库**的数据保持一致。MVSOL主从复制是MVSOL数据库自带功能，无需借助第三方工具。
>
> ![image-20230816113151411](README.assets/image-20230816113151411.png)



### 配置-主库Master

![image-20230816124424925](README.assets/image-20230816124424925.png)

```toml
[mysqld]
server-id=100		# 主库唯一ID 
log-bin=mysql-bin 	# 开启二进制日志 
```

```bash
# 方法一：正常用这个命令就行
systemctl restart mysqld

# 方法二：在一个非systemd的系统上,比如较老的Debian/Ubuntu版本默认使用sysvinit,或者在Docker容器环境下。
service mysql restart
```



![image-20230816124508213](README.assets/image-20230816124508213.png)

```sql
GRANT REPLICATION SLAVE ON *.* TO 'xiaoheizi'@'%' IDENTIFIED BY 'Root@123456';
```



![image-20230816124644452](README.assets/image-20230816124644452.png)

```sql
show master status;
```






### 配置-从库Slave

![image-20230816125150697](README.assets/image-20230816125150697.png)

![image-20230816125427249](README.assets/image-20230816125427249.png)

```bash
CHANGE MASTER TO
MASTER_HOST='172.23.72.37',  
MASTER_PORT=3333,
MASTER_USER='xiaoheizi',
MASTER_PASSWORD='Root@123456',  
MASTER_LOG_FILE='mysql-bin.000001',
MASTER_LOG_POS=442;
```

```sql
start slave;
```





> 注意事项
>
> ![image-20230816125833343](README.assets/image-20230816125833343.png)

​		

![image-20230816130048575](README.assets/image-20230816130048575.png)

​				（会有很多，看上面4列就好了，自己看不清可以复制到文本编辑器里面看）

```sql
show slave status;
```



## 读写分离

> **背景**
>
> 面对日益增加的系统访问量，数据库的吞吐量面临着巨大瓶颈。对于同一时刻有大量并发读操作和较少写操作类型的应用系统来说，将数据库拆分为**主库**和**从库**，主库负责处理事务性的增删改操作，从库负责处理查询操作，能够有效的避免由数据更新导致的行锁，使得整个系统的查询性能得到极大的改善。

### Sharding-JDBC

> sharding-JDBC定位为轻量级java框架，在java的JDBC层提供的额外服务。它使用客户端直连数据库，以jar包形式提供服务，无需额外部署和依赖，可理解为增强版的JDBC驱动，完全兼容JDBC和各种ORM框架使用sharding-JDBC可以在程序中轻松的实现数据库读写分离。
>
> - 适用于任何基于DBC的ORM框架，如: JPA,Hibernate, Mybatis,Spring JDBCTemplate或直接使用JDBC。
> - 支持任何第三方的数据库连接池，如: DBCP,C3PO,BoneCP,Druid,HikariCP等。
> - 支持任意实现IDBC规范的数据库。目前支持MySQL，Oracle，SQLServer，PostgreSQL以及任何遵循SQL92标准的数据库。



**开始示例**

> 使用ShardingJDBC实现读写分离步骤:
> 1、导入maven坐标
>
> 2、在配置文件中配置读写分离规则
>
> 3、在配置文件中配置**允许bean定义覆盖**配置项

主库创建数据库，看看从库会不会

![image-20230816221757380](README.assets/image-20230816221757380.png)





>         <dependency>
>             <groupId>org.apache.shardingsphere</groupId>
>             <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
>             <version>4.0.0-RC1</version>
>         </dependency>



```yaml
spring:
  shardingsphere:
    datasource:
      names:
        master,slave
      # 主数据源
      master:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://192.168.138.100:3306/rw?characterEncoding=utf-8
        username: root
        password: root
      # 从数据源
      slave:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://192.168.138.101:3306/rw?characterEncoding=utf-8
        username: root
        password: root
    masterslave:
      # 读写分离配置
      load-balance-algorithm-type: round_robin #轮询
      # 最终的数据源名称
      name: dataSource
      # 主库数据源名称
      master-data-source-name: master
      # 从库数据源名称列表，多个逗号分隔
      slave-data-source-names: slave
    props:
      sql:
        show: true #开启SQL显示，默认false
  main:
    allow-bean-definition-overriding: true
```

> 评论区：mysql8版本记得在url配置useSSL=false





在配置文件中配置**允许bean定义覆盖**配置项

```diff
    masterslave:
      # 读写分离配置
      load-balance-algorithm-type: round_robin #轮询
      # 最终的数据源名称
      name: dataSource
      # 主库数据源名称
      master-data-source-name: master
      # 从库数据源名称列表，多个逗号分隔
      slave-data-source-names: slave
    props:
      sql:
        show: true #开启SQL显示，默认false
+ main:
+   allow-bean-definition-overriding: true
```









