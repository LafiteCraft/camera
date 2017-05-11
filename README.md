|接口名|接口路径|参数列表|返回结果|
|:---|:---|:---|:---|
|查询一个用户信息|```user/findById.action```|```userId```|```[{"id":1,"loginName":"lafite","password":"lafite123","user":{"id":1,"name":"LafiteHao","birth":"80-01-07","sex":"M","qq":"375199496","phone":"18554653013"}}```|
|查询多个用户信息|```user/findAll.action```||参上|
|登陆验证|```user/login.action```|```login_name,password```|0-代码异常，1-登陆成功，2-用户名不存在，3-密码错误
|用户注册/更新|```user/register.action```|```login_name,password,name,birth,sex,qq,phone```|注册成功|
|用户名重复性校验|```user/vaildate_name.action```|```login_name```|该用户名的个数|
|查看单条日报|```daily/findInfo.action```|```daily_id```|```[{"id":1,"loginName":"lafite","password":"lafite123","user":{"id":1,"name":"LafiteHao","birth":"80-01-07","sex":"M","qq":"375199496","phone":"18554653013"}}```|
|查看全部日报|```daily/findAll.action```||参上|
|通过标题查找|```daily/findByTitle.action```|```title```|参上|
|删除日报，不好意思，我实在懒得做逻辑删除。|```daily/remove.action```|```daily_id```|无|
|保存日报|```daily/save.action```|```content,title```|无|
|查看单条摄像头信息|```camera/findInfo.action```|```camera_id```|```[{"id":1,"name":"测试摄像头","place":"我想我也不知道这是在什么地方","url":"place001/camera001"}]```|
|查看全部摄像头|```camera/findAll.action```||参上|
|通过标题查找|```camera/findByName.action```|```name```|参上|
|删除摄像头，不好意思，我实在懒得做逻辑删除。|```camera/remove.action```|```camera_id```|无|
|保存日报|```camera/save.action```|```name,place,url```|无|
