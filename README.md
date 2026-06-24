# 银发守护后台管理系统

银发守护后台管理系统是一个面向养老机构的老人健康与安全管理平台。系统基于 Spring Boot、Thymeleaf、MyBatis 和 MySQL 开发，围绕老人档案、健康数据、安全监护、设备管理、情感交互、行为报告和系统权限等场景，提供完整的后台管理功能。

## 技术栈

| 技术 | 版本 | 用途 |
| --- | --- | --- |
| Java | 1.8 | 项目开发语言 |
| Spring Boot | 2.6.13 | Web 应用启动、MVC、依赖注入和配置管理 |
| Thymeleaf | 3.0.15.RELEASE | 服务端页面模板渲染 |
| MyBatis Spring Boot Starter | 2.2.2 | 数据访问和 SQL 映射 |
| MySQL | 5.7 | 业务数据存储 |
| MySQL Connector/J | 8.0.31 | Java 连接 MySQL 驱动 |
| Maven | 3.x | 项目构建和依赖管理 |

## 功能模块

- 数据概览：展示老人数量、健康数据、事件提醒、用药统计等首页信息。
- 老人管理：维护老人档案、跌倒记录、健康数据、体检记录和用药提醒。
- 安全监护：管理事件列表、跌倒记录、对话记录、摄像头和实时监控页面。
- 设备管理：维护摄像头、语音终端和边缘网关设备信息。
- 情感交互：提供对话交互、提醒任务和陪伴记录管理。
- 行为报告：管理行为记录，并生成老人日常行为报告。
- 系统管理：包含健康阈值、角色权限、用户管理、操作日志和数据字典。

## 项目结构

```text
src/main/java/com/dtdx
├── config          # Web MVC 配置
├── controller      # 控制层
├── dao             # MyBatis Dao 接口
├── entity          # 实体类
├── interceptor     # 登录和权限拦截器
└── service         # 业务逻辑层

src/main/resources
├── mappers         # MyBatis XML 映射文件
├── static          # CSS 等静态资源
├── templates       # Thymeleaf 页面模板
└── application.properties
```

## 运行环境

- JDK 8 或以上
- Maven 3.x
- MySQL 5.7 或以上

## 数据库配置

默认数据库配置位于 `src/main/resources/application.properties`：

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/yinfashouhu?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=123456
```

项目提供数据库备份文件：

```text
database-backup/yinfashouhu-20260623-164354.sql
```

导入示例：

```bash
mysql -uroot -p123456 < database-backup/yinfashouhu-20260623-164354.sql
```

## 启动项目

```bash
mvn spring-boot:run
```

或打包后运行：

```bash
mvn clean package
java -jar target/yinfashouhu-0.0.1-SNAPSHOT.jar
```

访问地址：

```text
http://localhost:8080/
```

## 默认账号

```text
账号：admin
密码：123456
```

## 项目展示文档

项目展示文档位于：

```text
docs/项目展示文档.md
```

文档包含技术栈介绍、功能模块说明、核心页面展示、带数据截图、关键代码说明和开发过程总结。

## 截图目录

```text
docs/screenshots/live
```

该目录保存了系统运行后使用管理员账号登录截取的功能页面截图。

## 说明

本项目适合作为养老服务信息化、老人健康监护平台或 Java Web 毕业设计项目展示。项目采用传统后台管理系统分层结构，功能覆盖从页面交互到数据持久化的完整开发流程。
