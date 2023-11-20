# easydoc

一个文档管理项目，采用markdown方式写作。此项目也是作为[easyopen](https://gitee.com/durcframework/easyopen "easyopen")项目的实践项目。

用到的技术：

- 服务端：spring-boot-2.0.3 + [easyopen](https://gitee.com/durcframework/easyopen "easyopen") + [fastmybatis](https://gitee.com/durcframework/fastmybatis "fastmybatis")
- 前端：jQuery + bootstrap + [jui](https://gitee.com/durcframework/jui "jui")
- 前后端分离，可独立部署，采用jwt交互

# 预览

![输入图片说明](https://gitee.com/uploads/images/2018/0525/135044_a35ff136_332975.png "1.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0525/135059_39835b4e_332975.png "2.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0525/135108_a0efe148_332975.png "3.png")

![输入图片说明](https://gitee.com/uploads/images/2018/0525/135117_b92a1cfe_332975.png "4.png")

# 工程说明

package结构：

```
com.gitee.easydoc
    api：服务端提供的接口
    bean：一些公共的类
    dao：数据库访问层
    entity：实体类
    interceptor：easyopen拦截器
    service：公共的service层
```

resources下的public文件夹是整个前端代码，如果要前后端分离，把整个public文件夹单独部署即可

# 使用说明

1. 导入项目到IDE中（导入maven项目）
2. 执行easydoc_ddl&data.sql中的脚本，创建数据库以及导入数据
3. 运行EasydocSpringbootApplication.java
4. 访问http://localhost:8081/

端口可在application-dev.properties中修改

登录用户：

admin/123456

admin2/123456

jim/123456 (只读权限)
