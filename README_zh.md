

# kungfu-admin

使用Kungfu框架实现的系统管理平台。将 kungfu-admin 打造成 Kungfu 框架的最佳实践案例之一。

## 体验地址
https://admin.kungfu.wang/

## 操作步骤
1. 创建MySQL数据库
```shell
create database database_name character set utf8mb4 collate utf8mb4_unicode_ci;

# ddl
mysql -u username -p database_name <  resources/sql/sys.sql

# init data
mysql -u username -p database_name < resources/sql/sys_init.sql

```

2. 项目配置
   使用正确的数据库/用户名/密码更新app-prod.txt文件。

```shell
# config

mysql.url=jdbc:mysql://127.0.0.1:7229/kungfu_admin?characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull
mysql.username=root
mysql.password=******

# mode=prod
devMode=false

# baidu-ip-translate-api.sign=your-sign-here
ak=********
```
使用正确的端口和热更新类更新undertow.txt文件。
```shell
# config undertow

undertow.devMode=true
undertow.host=0.0.0.0
undertow.port=9090

# outer hot swap class
undertow.hotSwapClassPrefix=com.lastb7.swagger.,org.kungfu.
```
3. 打包

```shell
mvn clean package -Dmaven.test.skip=true
```

4. 部署
```shell
copy local/target/kungfu-admin-release/kungfu-admin to remote/data/deploy/
```

5. 运行

```shell
./start.sh
```


开源不易，请点个赞，收藏和关注。谢谢！