# kungfu-admin

A system management platform implemented using the Kungfu framework. Make kungfu-admin one of the best practice examples of the Kungfu framework.

## Experience address
https://admin.kungfu.wang/

## Operating steps

1. create MySQL database
```shell
create database database_name character set utf8mb4 collate utf8mb4_unicode_ci;

# ddl
mysql -u username -p database_name <  resources/sql/sys.sql

# init data
mysql -u username -p database_name < resources/sql/sys_init.sql

```

2. project config
update app-prod.txt with the correct database/username/password

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
update undertow.txt with the correct port\hot-swapping class
```shell
# config undertow

undertow.devMode=true
undertow.host=0.0.0.0
undertow.port=9090

# outer hot swap class
undertow.hotSwapClassPrefix=com.lastb7.swagger.,org.kungfu.
```
3. package

```shell
mvn clean package -Dmaven.test.skip=true
```

4. deploy
```shell
copy local/target/kungfu-admin-release/kungfu-admin to remote/data/deploy/
```

5. run

```shell
./start.sh
```


Open source is not easy, please give it a thumbs up, favorite, and follow. thanks!