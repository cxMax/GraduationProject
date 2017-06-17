# GraduationProject
帮一个同学做的简答毕业设计 , 有兴趣可以参考看看 .

## introduce 
* 课程管理App , 基于android平台 , 包含一些简单的功能 , 参见functions
* rxjava + dagger2 + butterknife + MVP + 自己封装的一些lib(参见component package)
* 但app还存在一些问题,例如性能和代码 , 我另开了一个project : <a herf="https://github.com/cxMax/Android-Clean-Architecture">Android-Clean-Architecture</a>利用自己在工作中积累的经验做优化.例如解决冷启动\内存\泄漏\GPU等问题.因此就没有在这个project继续修改了

## functions
功能模块 : 
* Course Component 对课程增删改查

* Remind Component 课程到点提醒功能

* Mute Mode Component 一键静音

* Alarm Component 闹钟提醒

* Refresh and LoadMore Component 下拉刷新 与 加载更多 模块

* Login Component (todo) 登录模块

## 部分截图
  ![image](https://github.com/cxMax/GraduationProject/blob/master/screenshot/index_page.png)

## Thanks
1. codeestX / GeekNews : 
https://github.com/codeestX/GeekNews 

## License
   Copyright (C) 2017 cxMax  
   Copyright (C) 2017 GraduationProject

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
