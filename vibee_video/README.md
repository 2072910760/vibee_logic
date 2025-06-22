# Vibee Video Platform

基于Spring Boot + MyBatis Plus + SQL Server的视频平台后端项目

## 技术栈

- **Spring Boot 3.2.12**
- **MyBatis Plus 3.5.6**
- **SQL Server**
- **Druid 连接池**
- **Knife4j API文档**

## 环境要求

- JDK 21+
- Maven 3.6+
- SQL Server 2019+

## 数据库配置

### 1. 安装SQL Server
确保您的系统已安装SQL Server，并且服务正在运行。

### 2. 创建数据库和用户
1. 使用SQL Server Management Studio (SSMS) 连接到您的SQL Server实例
2. 执行 `database_init.sql` 脚本来创建数据库和表结构
3. 或者手动创建用户：
   ```sql
   CREATE LOGIN vibee_user WITH PASSWORD = '123456';
   USE VibeeVideoPlatform;
   CREATE USER vibee_user FOR LOGIN vibee_user;
   GRANT ALL PRIVILEGES ON DATABASE::VibeeVideoPlatform TO vibee_user;
   ```

### 3. 配置连接参数
在 `application.yml` 中修改数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:sqlserver://YOUR_SERVER_NAME;databaseName=VibeeVideoPlatform;encrypt=true;trustServerCertificate=true
    username: vibee_user
    password: 123456
```

## 运行项目

### 1. 编译项目
```bash
mvn clean compile
```

### 2. 运行项目
```bash
mvn spring-boot:run
```

### 3. 访问应用
- 应用地址: http://localhost:9588
- API文档: http://localhost:9588/doc.html
  - 用户名: admin
  - 密码: 123456

## 项目结构

```
src/main/java/org/example/vibee/
├── config/          # 配置类
├── controller/      # 控制器
├── dao/            # 数据访问层
├── entity/         # 实体类
├── service/        # 服务层
├── util/           # 工具类
├── vo/             # 视图对象
└── web/            # Web相关配置
```

## 数据库表结构

- `user` - 用户表
- `video_category` - 视频分类表
- `video` - 视频表
- `friend_relation` - 好友关系表
- `watch_history` - 观看历史表
- `video_like` - 视频点赞表
- `video_collect` - 视频收藏表
- `video_commentary` - 视频评论表

## 常见问题

### 1. 数据库连接失败
- 检查SQL Server服务是否运行
- 确认服务器名称和端口
- 验证用户名和密码
- 检查防火墙设置

### 2. 端口占用
如果9588端口被占用，可以在 `application.yml` 中修改端口：
```yaml
server:
  port: 8080
```

### 3. 编译错误
确保使用JDK 21：
```bash
java -version
```

## 开发说明

### 生成代码
使用MyBatis Generator生成实体类和Mapper：
```bash
mvn mybatis-generator:generate
```

### 添加新功能
1. 在 `entity` 包中创建实体类
2. 在 `dao` 包中创建Mapper接口
3. 在 `service` 包中创建服务类
4. 在 `controller` 包中创建控制器
5. 在 `resources/mapper` 中添加XML映射文件

## 许可证

MIT License 