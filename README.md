# 海洋生物知识库系统 - 后端框架

## 项目概述

本项目是海洋生物知识库系统的后端框架，基于Spring Boot 2.4.2构建，集成了MyBatis-Plus、Swagger、参数校验、异常处理、XSS防护等功能。

## 功能特性

### 3.1 启动日志优化 ✅
- 自定义启动Banner
- 优化日志输出格式
- 日志文件自动滚动
- 错误日志单独输出

### 3.2 逆向生成代码及测试 ✅
- MyBatis-Plus代码生成器
- 支持MySQL数据库
- 自动生成Entity、Mapper、Service、Controller
- 数据库连接测试

### 3.3 创建自定义异常类 ✅
- 业务异常枚举 `BusinessExceptionCode`
- 自定义异常类 `BusinessException`
- 异常信息统一管理

### 3.4 定义后端统一返回对象 ✅
- 统一响应格式 `CommonResp<T>`
- 支持泛型数据返回
- 成功/失败状态标识

### 3.5 利用Swagger搭建REST API ✅
- Swagger2自动生成API文档
- JWT Token支持
- 在线API测试界面
- 访问地址：http://localhost:8880/swagger-ui.html

### 3.6 后端校验功能 ✅
- JSR303参数校验
- 自定义校验规则
- 校验错误信息统一返回

### 3.7 统一异常处理 ✅
- 全局异常处理器
- 参数校验异常处理
- 业务异常处理
- 系统异常处理

### 3.8 抵御跨站脚本(XSS)攻击 ✅
- 基于Hutool的XSS过滤
- 请求参数自动转义
- JSON数据过滤
- 防止恶意脚本注入

## 项目结构

```
src/main/java/com/gec/obwiki/
├── config/
│   ├── SwaggerConfig.java          # Swagger配置
│   └── xss/
│       ├── XssFilter.java          # XSS过滤器
│       └── XssHttpServletRequestWrapper.java  # XSS请求包装器
├── controller/
│   └── TestController.java         # 测试控制器
├── exception/
│   ├── BusinessException.java      # 自定义异常类
│   ├── BusinessExceptionCode.java  # 异常枚举
│   └── ObwikiExceptionAdvice.java  # 全局异常处理器
├── req/
│   └── DemoReq.java               # 请求参数类
├── resp/
│   └── CommonResp.java            # 统一返回对象
└── ObwikiApplication.java         # 启动类

src/main/resources/
├── application.properties         # 应用配置
├── banner.txt                    # 启动Banner
└── logback-spring.xml           # 日志配置
```

## 技术栈

- **Spring Boot**: 2.4.2
- **MyBatis-Plus**: 3.5.2
- **MySQL**: 8.0.13
- **Swagger**: 2.9.2
- **Hutool**: 5.7.14
- **Lombok**: 最新版本
- **Validation**: Spring Boot Starter Validation

## 快速开始

### 1. 环境要求
- JDK 11+
- Maven 3.6+
- MySQL 8.0+

### 2. 数据库配置
1. 创建数据库 `obwiki`
2. 导入SQL文件 `obwiki.sql`
3. 修改 `application.properties` 中的数据库连接信息

### 3. 启动项目
```bash
mvn spring-boot:run
```

### 4. 访问地址
- 应用首页: http://localhost:8880
- Swagger文档: http://localhost:8880/swagger-ui.html

## API测试

### 测试接口
- **POST** `/test/sayHello`
  - 功能：参数校验测试
  - 参数：`DemoReq` (name, phone, password)
  - 返回：`CommonResp<DemoReq>`

### 测试示例
```json
{
  "name": "张三",
  "phone": "13800138000",
  "password": "123456"
}
```

## 代码生成

运行测试类中的 `generateCode()` 方法可以自动生成数据库表对应的代码：

```java
@Test
public void generateCode() {
    // 代码生成逻辑
}
```

生成后的代码需要手动移动到对应的包目录中。

## 安全特性

### XSS防护
- 自动过滤所有请求参数
- 支持GET、POST参数过滤
- JSON数据自动转义
- 防止恶意脚本注入

### 参数校验
- 前端参数后端二次校验
- 自定义校验规则
- 统一错误信息返回

## 日志配置

- 控制台彩色日志输出
- 日志文件自动滚动
- 错误日志单独存储
- 日志级别可配置

## 注意事项

1. 确保MySQL服务已启动
2. 数据库连接信息正确配置
3. 端口8880未被占用
4. 首次运行需要执行代码生成

## 开发建议

1. 使用Swagger进行API测试
2. 遵循统一的异常处理规范
3. 合理使用参数校验
4. 注意XSS防护的有效性

## 扩展功能

- 用户认证授权
- 文件上传下载
- 缓存机制
- 定时任务
- 消息队列

---

**项目状态**: ✅ 基础框架完成  
**最后更新**: 2024年  
**维护者**: 开发团队 