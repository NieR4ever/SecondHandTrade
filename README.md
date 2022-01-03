# SecondHandTrade
二手信息发布平台
本项目所使用的技术：springboot+vue.js

## 安装与初始化

### 安装运行环境

| 软件名        | 官网                   | 版本号   |
| ------------- | ---------------------- | -------- |
| Node.js       | nodejs.cn              | v14.18.0 |
| Npm           | www.npmjs.com          | 6.14.15  |
| IntelliJ IDEA | www.jetbrains.com/idea | 2021.1   |
| Java jdk      | www.java.com           | 8        |
| Mysql         | www.mysql.com          | 8.0      |

### 安装项目

1. 将mysql文件夹下的second_hand_trade.sql导入到你的mysql数据库
2. 使用idea打开项目，等待maven导入依赖
3. 修改application.yml文件，将数据库名称、用户名、密码修改为对应的数据库名称、用户名、密码；将服务器端口修改为所需端口号；将文件上传的地址修改为所需地址；将文件上传的ip地址或域名和端口修改为所需的ip地址或域名和端口；
4. 点击运行
5. 将upload的9090.bat文件放入要进行图片映射的文件夹，右键编辑，将第一行的H:修改为你的对应盘符，保存后双击运行
6. 修改sht_vue文件夹下的vue.config.js文件，将端口号修改为所需端口号
7. 使用cmd打开sht_vue文件夹，使用命令npm install -g serve，再使用命令npm install，再使用命令npm run serve运行
8. 打开浏览器输入localhost:8080
