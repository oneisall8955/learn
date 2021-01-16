# 一些有用的工作的总结

## 代码
java 有用的模板或工具代码
### 实时汇率获取
- 中行
- 捷汇
- 智能获取
- 调度
- 缓存
### PNR解析
- 接口/抽象类/枚举/自动适配 
- 1E/1A/1S/1G/1W解析
### Jersey
- 配置
- Resource
### redis客户端
### 工具类
- CommonMatchUtil.java
- Result.java    

## IDEA 配置
开发工具 IDEA 的各种配置
### 模板
#### 创建class/enum/interface/shell模板
配置位置：`Setting(Ctrl+Alt+S)-Editor-File and code Templates`
配置tab栏：Files
##### Class
```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
/**
 * TODO :please describe it in one sentence
 * @author : oneisall
 * @version : v1 ${DATE} ${HOUR}:${MINUTE}
 */
public class ${NAME} {
}
```
##### Enum
```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
/**
 * TODO :please describe it in one sentence
 * @author : oneisall
 * @version : v1 ${DATE} ${HOUR}:${MINUTE}
 */
public enum ${NAME} {
}
```
##### Interface
```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
/**
 * TODO :please describe it in one sentence
 * @author : oneisall
 * @version : v1 ${DATE} ${HOUR}:${MINUTE}
 */
public interface ${NAME} {
}
```
##### AnnotationType
```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
/**
 * TODO :please describe it in one sentence
 * @author : oneisall
 * @version : v1 ${DATE} ${HOUR}:${MINUTE}
 */
public @interface ${NAME} {
}
``` 
##### Bash

可能需要自己添加

```bash
#!/bin/bash
# todo:please describe it in one sentence
# author:oneisall
# date:${DATE}
```

#### 创建代码模板
配置位置：`Setting(Ctrl+Alt+S)-Editor-Live Templates`
1. 添加自定义组：右上角`+`号选择`Template Group`，填写新增的组名如`0.custom`
2. 组内添加模板：选中新建完的组，如`0.custom`，右上角`+`号选择`Live Template`
3. 填写具体内容
    - abbreviation：引出该模板代码的快捷键一般 `@` 开头，如`@jt`为junit的模板 
    - Description：模板的描述，如junit Test 方法
    - Template text：模板内容，具体的模板，根据自己需要填写，`$END$`为导出模板后光标的位置
    - No applicable contexts yet.Define ：提示没有这个模板还没有定义适用的文件，选择Define进行选择，如选Java

模板例子：

以下为：`快捷键/描述/应用文件/模板内容`

##### @ajax-AJAX模板
@ajax/生成ajax模板/Html&JavaScript&Jsp&Vue
```js
$.ajax({
    url:'URL$END$',
    type:'GET',
    async:true,
    data: data,
    success:function(result){
        alert(result.msg);
        if(result.status){
            setTimeout(function(){
                // location.href=''
            },1500);
        }
    },
    error:function () {
        alert("系统发生错误！")
    }
});
```
##### @t-Jquery $this模板
@t/js 生成var $this=$(this)/Html&JavaScript&Jsp&Vue
```
var $this = $(this)$END$
```
##### @tt-thymeleaf模板
@tt/thymeleaf th:text模板/Html&JavaScript&Jsp&Vue
```
th:text="${$END$}"
```
##### @f-一行注释
@f/普通属性&字段javadoc注释/所有文件均适用
```
/** $END$*/
```
##### @jt-Junit
@jt/junit test template/Java
```
@Test
public void test$END$(){
    
}
```

##### @log
@log/日志/java
```
private static final Logger logger = LoggerFactory.getLogger($CLAZZ$.class);
```
Edit variable 将 CLAZZ 变量修改为 className() 函数

### JRebel
#### 下载JRebel插件后激活
- 选择Connect to online licensing service
- 地址：${SERVER}/${UUID}，如 http://jrebel.autoseasy.cn/db293adf-2076-4917-bdd6-e32271419591
- 邮箱：随便填写
- 

${SERVER}可选地址：（可能过期了）
- http://jrebel.autoseasy.cn/
- http://120.77.158.110:1008/
- http://140.143.12.222:8081/
- http://139.9.193.106:10000/

UUID在线生成：[GUIDs online](https://www.guidgen.com)

(176dd83d-20fb-4be2-ab89-f2aa46559c76)

#### 搭建
- 下载源码[https://gitee.com/gsls200808/JrebelLicenseServerforJava](https://gitee.com/gsls200808/JrebelLicenseServerforJava)
- 打包jar包：mvn clean ; mvn clean package
- 在target目录下找到打好的jar包，上传到服务器。
- 服务器执行`nohup java -jar JrebelBrainsLicenseServerforJava-1.0-SNAPSHOT-jar-with-dependencies.jar -p 10000 >/dev/null 2>&1 &`
- 可以使用已打包的jar在 lib/JrebelBrainsLicenseServerforJava-1.0-SNAPSHOT-jar-with-dependencies.jar
- `-p`参数指定端口

参考：

[JRebel激活服务搭建](https://www.cnblogs.com/jimoer/p/11161339.html)

### 编码
配置位置：`Setting(Ctrl+Alt+S)-Editor-File Encodings`
- Global Encoding:UTF-8
- Project Encoding:UTF-8
- Default encoding for for projects files:UTF-8
- Transparent native-to-ascii conversion:true
- Create UTF-8 files:with NO BOM

### 快捷键
```markdown
- Editor Actions
    - Duplicate Line or Selection : none
    - Duplicate Entire Lines : Ctrl + D
- Main menu
    - Edit
        - Duplicate Line or Selection : none
    - View
        - Compare Files : none
    - Code
        - Completion
            - Basic : Alt+/
            - Cyclic Expand Word : none
            - Cyclic Expand Word (Backward): none
    - Run
        - Debug with JRebel : Ctrl + Alt + Shift + F9
    - Tools
        - Vim Emulator : Ctrl + Shift + \
        - Start SSH session : Ctrl + Alt + Shift + F12
    - VCS
        - Mercurial
            - Annotate : Ctrl + Alt + Shift + F11
        - Subversion
            - Annotate : Ctrl + Alt + Shift + F11
        - Perforce
            - Annotate : Ctrl + Alt + Shift + F11
        - Git
            - Annotate : Ctrl + Alt + Shift + F11
        - CVS
            - Annotate : Ctrl + Alt + Shift + F11
        - TFS
            - Annotate : Ctrl + Alt + Shift + F11
- Version Control System
    - Annotate : Ctrl + Alt + Shift + F11
    - Show Diff : none
    - CVS
        - Annotate : Ctrl + Alt + Shift + F11
    - Git
        - Annotate : Ctrl + Alt + Shift + F11
    - Perforce
        - Annotate : Ctrl + Alt + Shift + F11
    - Subversion
        - Annotate : Ctrl + Alt + Shift + F11
    - TFS
        - Annotate : Ctrl + Alt + Shift + F11
    - Mercurial
        - Annotate : Ctrl + Alt + Shift + F11
    - Shelve
        - Show Diff : none
    - Diff & Merge
        - Show Diff : none
        - Compare Files : none
    - File History
        - Show Diff : none
        - Annotate : Ctrl + Alt + Shift + F11
        - Compare Files : none
- Plug-ins
    - IdeaVim
        - Vim Emulator : Ctrl + Shift + \
    - JavaScript Support
        - Fix ESLint Problems : Ctrl + Alt + Shift + ;
    - SSH Remote Run
        - Start SSH session : Ctrl + Alt + Shift + F12
- Other
    - Desktop Directory : none
    - Fix doc comment : Alt + M
    - Send EOF : none
    - View AssertEquals Difference : none
```

### 其他配置
#### 菜单字体
配置位置：`File | Settings | Appearance & Behavior | Appearance`
use custom font：Consolas
Size：18
#### 编辑字体
配置位置：`Setting(Ctrl+Alt+S)-Editor-Font`
Font：Consolas
Size：18
Line Spacing：1.0
Fallback Font：None

#### 增加TODO关键字
配置位置：`Setting(Ctrl+Alt+S)-Editor-TODO`

#### 鼠标滚轮控制字体
`Editor-General-Change font size(Zoom) with Ctrl+Mouse Wheel`

#### 编码一行120提示字符提示换行修改为170
`Editor-General-Code Style-Hard wrap at ${value} columns` 填写 value = 170 

`Editor-General-Code Style-wrap on typing` 不选择自动换行

#### 允许一行Javadoc注释，即不wrap一行
`File | Settings | Editor | Code Style | Java` 的 `Others` 中选中 `Do not wrap one line comments`

#### Terminal
配置路径：`Tools-Terminal`
Shell path:D:\Git\bin\bash.exe
Tab name :GitBash

#### 显示空格换行符等
`File | Settings | Editor | General | Appearance` 中选择 `Show whitsapces`

#### 热部署+取消双击
快捷键 ctrl + shift + alt + /,选择Registry

勾上 Compiler autoMake allow when app running
勾上 ide.suppress.double.click.handler

### 好用插件
- JRebel for IntelliJ
- Alibaba Java Coding Guidelines
- CamelCase
- CodeGlance
- ESLint
- GenerateAllSetter
- Grep Console
- IdeaVim
- Iedis
- Lombok
- Rainbow Brackets
- stackOverflow
- VisualVM Launcher
- Vue.js
- JB SDK Bintray Downloader

## 软件推荐

### 系统工具
- Chrome
- 360极速浏览器
- WinRAR 破解
- 护眼宝
- Dism++

### 开发工具
开发用的相关工具
- IDEA 2018.1.4/2019.3
- GitBash
- ConEmu
- FinalShell
- mongodbManage
- Studio 3T
- RedisDesktopManager
- Navicat
- Postman
- Regex Match Tracer
- SVN小乌龟
- window subsystem linux

### 效率工具
办公效率工具
- Everything
- Ditto
- snipaste
- Rolan
- Typora
- SmallPdf
- 有道词典（双击Ctrl/Ctrl+Shift+Y）
- Visio 2013
- Office 2013全家桶
- XMind
- 极速PDF破解版

### 在线网站
- [雁阵](https://app.geeseteam.com)
- [draw.io](https://draw.io)



### 个人
自己使用的工具
- WeChat
- TIM
- 向日葵
- SSTap-beta
- SSR
- PanDownload
- Best Trace
- OCR (截屏转文字)
- PicGo
- ScreenToGif
- ZeroTier One
- Bitwarden

## 软件破解总结

### IDEA
1. 将破解文件 lib/JetbrainsCrack.jar 放在idea安装目录的 bin 目录下，如 `D:\JetBrains\idea-2019.2.4\bin`
2. 修改 idea64.exe.vmoptions 文件 ，添加破解文件的配置，形式如`-javaagent:${破解文件路径}`, `-javaagent:D:\JetBrains\idea-2019.2.4\bin\JetbrainsCrack.jar`
3. hosts不能有jetbrains的回环设置，为避免不必要的错误，安装目录不能有中文和空格
### XMind
### Office2013/Visio
### mongodbManage
### Stdio 3T
### Navicat
