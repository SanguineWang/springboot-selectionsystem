## springboot后端

### 项目描述
毕业设计双向选择系统
### 开发环境
* 开发工具
    * idea 2020
* 数据库
    * MySql WorkBench 8.0 CE
* 项目依赖
    * spring-data-jpa
    * test
    * lombok
    * junit
    * mySQL driver
* 版本


### 部署环境(未部署)
* 服务器硬件配置
* 系统版本
* 依赖的软件配置


### 2020/3/10
* Init
```text
项目初始化
    设计实体类关系，建立:学生，课程，选课表，老师实体，账户密码绑定到用户（学生和老师）实体,设计成绩到选课表，
    Student{
            int id;
            String password ="123456";
            String name;
            String extra;
            //加权
            Float grade;
            Boolean isSelected;
            List<Elective> electiveList;
            Teacher teacher;
           }
    teacher{
            int id;
            String password = "123456";
            String name;
            String extra;
            //学生上限
            int upper_limit;
            List<Student> studentList;
            List<Course> courseList;
           }
    Course{
            int id;
            String name;
            String extra;
            float weight;
            float lowest;
            List<Elective> electiveList;
            Teacher teacher;
          }
    Elective{
            int id;
            String extra;
            float grade;
            Course course;
            Student student;
            }
```
* BUG 
```log
//实体创建 String key属性时报错，可能与底层实现sql语句冲突
Error executing DDL "create table student (id integer not null, extra varchar(255), grade float, is_selected bit, key varchar(255), name varchar(255), teacher_id integer, primary key (id)) engine=InnoDB" via JDBC Statement
```
### 2020/3/13
* Improvements

```text
//添加方向类，与教师类为many to one 关系
 Direction{
             int id;
             String name;
             float weight;
             Teacher teacher;
          }
 teacher{
            int id;
            String password = "123456";
            String name;
            String extra;
            //学生上限
            int upper_limit;
            List<Student> studentList;
            List<Course> courseList;
            List<Direction> directionList;
           }
```
### 2020/3/21 0:01
* improvenments 
```text
改进表属性为引用类型
```
* New Features 新功能
```text
业务逻辑层分角色开发
teacher：修改密码
         修改其他信息
         添加/修改毕设方向
         创建、修改课程
         指定课程添加/修改学生和成绩
         添加指定学生
         登录  
student：登录     
```   