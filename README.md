## SelectionSystem Backstage

### 毕业设计双向选择系统 

###
* Development tool
    * idea 2020
* DB
    * MySql WorkBench 8.0 CE
* Maven
    * spring-data-jpa
    * test
    * lombok
    * junit
    * mySQL driver
* Version
    * version1.0


### 部署环境(未部署)
* 服务器硬件配置
* 系统版本
    * linux
* 依赖的软件配置
    * docker

### Change Log
#### V0.1.2 (2020/3/29 16:06) :flush:
 :bowtie:
* New Features 
    * StudentService: 
         * 判断是否选过
         * 选择导师

#### V0.1.1 (2020/3/26 10:11)

* Improvenments 
    * 改进业务逻辑层功能，参数优化
    

#### V0.1.0 (2020/3/21 0:01)

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
        * 登录     
        
#### V0.0.2 (2020/3/13 11:20)

* Improvements
    * 添加方向类，与教师类为many to one 关系
            
#### V0.0.1 (2020/3/10 0:00)
* Init
    * 设计实体类关系，建立 : 学生，课程，选课表，教师，方向，选方向中间表
* Bug 
    * 实体创建 String key属性时报错，可能与底层实现sql语句冲突
```text
Error executing DDL "create table student (id integer not null, extra varchar(255), grade float, is_selected bit, key varchar(255), name varchar(255), teacher_id integer, primary key (id)) engine=InnoDB" via JDBC Statement
```

