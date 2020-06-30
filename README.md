## SelectionSystem Backstage :couple:

### Development :oncoming_police_car:	
![Language](https://img.shields.io/badge/Java-11-yellow.svg)
![Idea](https://img.shields.io/badge/Idea-2019.3-blue.svg)
![MySql WorkBench](https://img.shields.io/badge/MySqlWorkBench-8.0CE-green.svg)
![VS Code](https://img.shields.io/badge/VSCode-1.43.0-pink.svg)
![CopyRight©](https://img.shields.io/badge/CopyrRight©2020-SanguineWang-success.svg)

* Maven
    * spring-data-jpa
    * test
    * lombok
    * junit
    * mySQL driver
* Version
    * version1.0

### Deployment environment :european_castle:

* Server
    * 114.115.181.66
* System
    * Linux  
* Software
    * Docker :penguin:

### :bookmark_tabs: Change Log 
#### v1.0.0（2020/6/30）
* New 
    * docker-compose部署前后端项目
    * https://blog.csdn.net/Wlikell/article/details/107035601
#### v1.0.0（2020/6/28）
* New 
    * studentControler
    * 前后端整合测试
#### V0.1.4 (2020/4/14 14:39) :flushed:
* New 
    * TeacherController
#### V0.1.4 (2020/4/5 23:56) :astonished:
* Improvenments 
    * 从student 和 teacher 分离出 user实体
    * 重写service层（大部分完整），对容器.ifpresentOrElse分别打印日志

#### V0.1.3 (2020/4/1 0:36) :yum:

* Bug 
    * 改进service层，空返回值优化，使用Optional.ofNullable,返回List.of()空集合

#### V0.1.2 (2020/3/29 16:06) :dizzy_face:
 
* New Features 
    * StudentService: 
        * 判断是否选过
        * 选择导师
    * TeacherController:
        * 添加，获取，修改课程

#### V0.1.1 (2020/3/26 10:11)  :flushed:

* Improvenments 
    * 改进业务逻辑层功能，参数优化
    
#### V0.1.0 (2020/3/21 0:01) :smile: 

* Improvenments 
    * 改进表属性为引用类型
* New Features
    * TeacherService：
        * 修改密码
        * 修改其他信息
        * 添加/修改毕设方向
        * 创建、修改课程
        * 指定课程添加/修改学生和成绩
        * 添加指定学生
        * 启动双选
        * 登录  
    * StudentService：
        * 选择导师    
        
#### V0.0.2 (2020/3/13 11:20) :smirk: 

* Improvements
    * 添加方向类，与教师类为many to one 关系
    * 跨域配置
    * 结合Vue前端服务器进行基础的数据展示 
    * 设计前台界面           
#### V0.0.1 (2020/3/10 0:00) :blush:

* Init
    * 设计实体类关系，建立 : 学生，课程，选课表，教师
    * 创建Vue项目(https://github.com/SanguineWang/Vue-TutorSelectionSysterm)
* Bug 
    * 实体创建 String key属性时报错，可能与底层实现sql语句冲突
```text
Error executing DDL "create table student (id integer not null, extra varchar(255), grade float, is_selected bit, key varchar(255), name varchar(255), teacher_id integer, primary key (id)) engine=InnoDB" via JDBC Statement
```

