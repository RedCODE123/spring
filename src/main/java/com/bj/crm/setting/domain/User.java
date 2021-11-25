package com.bj.crm.setting.domain;

public class User {

    /**
     * 关于登录：
     *      验证登录账号和密码：
     *      执行sql语句 User user = select count(*) from tbl_user where loginAct=? and loginPwd =?
     *      如果user对象为null，则判断此时账号密码错误
     *      如果此时user对象为1，则证明账号密码正确
     *      如果大于1则有垃圾数据产生
     *      此时还需向下验证以下字段
     *      expireTime
     *      lockState
     *      allowIps
     *
     */
    private String id;//主键
    private String loginAct;//登录账号
    private String name;//用户名
    private String loginPwd;//登录密码
    private String email;//邮箱
    private String expireTime;//失效时间 19位
    private String lockState;//锁定状态 0 锁定，1 启用
    private String deptno;//部门编号
    private String allowIps;//允许访问的ip地址
    private String createTime;//创建时间 19位
    private String createBy;//创建人
    private String editTime;//修改时间 19位
    private String editBy;//修改人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginAct='" + loginAct + '\'' +
                ", name='" + name + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", email='" + email + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", lockState='" + lockState + '\'' +
                ", deptno='" + deptno + '\'' +
                ", allowIps='" + allowIps + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                '}';
    }
}