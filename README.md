# Ava8
**河北工大软件课设之在线报考系统的设计与实现**  

**----------------------------------------------------------------------------------------------------------------**  
**2024/12/26**  
先放页面图吧：  
1、这是登陆页面：    
![image](/result/login.png)  
2、这是招生管理员页面：  
![image](/result/Enrollment_Admin.png)  
3、这是系统管理员页面：  
![image](/result/System_Admin.png)  
4、这是考生页面：  
![image](/result/examinee.png)   
5、这是数据库总览图：  
![image](/result/database.png)   
6、这是输入样例（账号密码及对应的身份）：  
![image](/result/input.png)   
这个代码是我上周五才开始写的（也就是12月19号），主要参照了我软工实验的代码框架，但是实现起来远比软工那个实验复杂得多。代码部分全程由我一个人完成，至于实验文档是我的组员进行编写，晚些时候我再上传这个实验的实验文档。  
直到昨天，我才完成了这个代码的基本部分，但还是存在很多问题，比如：  
**对于招生管理员而言**   
（1）招考信息维护这里一直有时间格式转换的问题，这个问题于昨天上午解决完毕；  
（2）现场确认缺少提示框，于昨天上午解决了这个问题；  
（3）学生密码管理没有实现，只有一个按钮，这个问题于今天早上解决了，我设计了一个视图，然后在框架中连接了这个视图，用它来显示与考生的密码有关的信息；  
**对于系统管理员而言**  
（1）修改密码部分没有实现，只有一个按钮，这个问题于昨天下午解决了，我增加了修改密码的框架，之后的考生修改密码部分也是参照了系统管理员部分的框架；  
（2）管理员维护分为添加管理员、删除管理员、更新管理员三部分，这三部分之前存在无法写入数据库的问题，于今天下午修改完毕（没有用上存储过程和触发器的方法，因为我不太会，所以我直接一个表一个表地进行修改）；  
（3）数据库维护部分之前也没有实验，只有一个空白的提示框，我今天上午建立了5个视图，然后在这部分功能让他展示这些视图；  
**对于考生而言**  
（1）考生注册功能的问题卡了我好久，一开始我用了触发器和存储过程来实现，后来发现太麻烦了，于是用了笨方法来实现，于昨天下午解决的；  
（2）查看登陆历史只有一个空白框，因为我数据库中没有建立与此相关的表，所以没实现这个功能；  
（3）报考相关的信息于昨天上午进行了补充；  
（4）录取相关于昨天上午进行了界面美化；  
