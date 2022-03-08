# 美年旅游项目总结
## 1.前言
 美年旅游管理系统是为了方便用户进行旅游套餐预约，并方便各大旅行社为用户提供旅游套餐而设计开发的一款旅游管理系统。
 
 ---
##  2.项目总体介绍
**美年旅游项目**是基于Spring+SpringMVC+MyBatis+Redis+MySQL+Vue+Dubbo+zookeeper+Quartz定时任务构建的基于Restful风格的旅游管理微服务系统。
项目基于前后端分离的开发模式，使用zookeeper作为注册中心，后台包括旅行社管理，旅游管理、旅游后台、旅游评估、信息系统等五大功能模块。

 1. 基于MyBatis完成自由行、跟团游、套餐游、分公司地址信息的增删改，并将查询出的信息进行分页显示，完成用户登陆的审核、锁定等功能。
2. 实现旅游预约模板文件上传下载功能，使用Apache POI将用户上传的存储在Excel文件中的旅游预约设置信息批量导入到数据库中，简化导入数据的步骤，还可将数据导出到Excel文件中备份。
3. 整合阿里云短信服务，完成用户手机短信注册登录功能；使用网关对用户进行登录认证，在登陆后才能请求服务；
4. 整合七牛云对象存储KODO，云存储用户认证图片，缓解主机内存压力；编写接口，从token中解析用户信息，实现实名认证。
5. 集成SpringSecurity进行权限控制，对用户进行认证管理并授予用户访问页面的权限。
6. 编写接口查询会员信息，配合前端使用ECharts将会员数量和会员增长数量以折线图的方式展现。
7. 整合百度地图API,在前端页面展示分公司地址。并完成分公司地址的添加和删除功能。

---
## 3.系统后台部分功能剪影
【1】系统后台主页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/6b3ab60cba2f4ac98ea6961d30b17b99.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAbGlqaWFuXzM2NQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
【2】主菜单
![在这里插入图片描述](https://img-blog.csdnimg.cn/aff6d0d0e749426a806f1e93c1ffbe73.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAbGlqaWFuXzM2NQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
【3】分公司的地址
![在这里插入图片描述](https://img-blog.csdnimg.cn/e22c79c7b2184e8698ba69db2aa92d1f.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAbGlqaWFuXzM2NQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
【4】以日历的形式展示预约数据，并设置预约人数
![在这里插入图片描述](https://img-blog.csdnimg.cn/2a45c0794bd847d2bb9864ef17259d84.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAbGlqaWFuXzM2NQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
【5】会员数量统计
![在这里插入图片描述](https://img-blog.csdnimg.cn/8f8158f9ad0c44c0b790880d21aaa7e8.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAbGlqaWFuXzM2NQ==,size_20,color_FFFFFF,t_70,g_se,x_16)


## 4.系统前台部分剪影
【1】前台首页
![在这里插入图片描述](https://img-blog.csdnimg.cn/3a2df6ba6a2b4248a62ae63dae986f82.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAbGlqaWFuXzM2NQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

【2】旅游套餐预约
![在这里插入图片描述](https://img-blog.csdnimg.cn/f466473d25fb4267a79adbba065b07c7.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAbGlqaWFuXzM2NQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

【3】信息填写
![在这里插入图片描述](https://img-blog.csdnimg.cn/23585b8c6e064365a847a3414ea5afc3.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAbGlqaWFuXzM2NQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

等等。


