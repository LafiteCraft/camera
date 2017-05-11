|接口名|接口路径|参数列表|返回结果|
|:---|:---|:---|:---|
|查询一个用户信息|```user/findById.action```|```userId```|```{"id":1,"name":"LafiteHao","birth":"80-01-07","sex":"M","qq":"375199496","phone":"18554653013"}```|
|查询多个用户信息|```user/findAll.action```||参上|
|登陆验证|```user/login.action```|```login_name|password```|0-代码异常，1-登陆成功，2-用户名不存在，3-密码错误
|用户注册|```user/register.action```|```login_name|password```|注册成功|
|用户名重复性校验|```user/vaildate_name.action```|```login_name```|该用户名的个数|
|完善用户信息，其实也就是写入user表一些数据|```user/full_info.action```|```name|birth|sex|qq|phone```|无|