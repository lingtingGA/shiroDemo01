package com.shiro01.test;

import com.shiro01.DBUtil.DBUtil;
import com.shiro01.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

public class test {
    private static JdbcRealm jdbcRealm;

    static {
        jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(DBUtil.getDataSource());
        jdbcRealm.setPermissionsLookupEnabled(true);    // 使用JdbcRealm时要设置权限开关，默认为false

        // 自定义SQL语句（如果不设置自定义的SQL语句则按照JdbcRealm内置的模板SQL语句进行数据库操作，可以进入JdbcRealm查看）
        String sql = "select password from user where username = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        String roleSql = "select role_name from user_roles where username = ?";
        jdbcRealm.setUserRolesQuery(roleSql);

        String permissionSql = "select permission from roles_permissions where role_name = ?";
        jdbcRealm.setPermissionsQuery(permissionSql);
    }

    public static void main(String[] args) {

        // 1. 获取默认的SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        // 2. 设置到工具中
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 3. 获取主体
        Subject subject = SecurityUtils.getSubject();

        User admin = new User("admin", "123456");
        User test = new User("test", "123456");
        // 4. 创建token
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
//        UsernamePasswordToken token = new UsernamePasswordToken(test.getUsername(), test.getPassword());
//        UsernamePasswordToken token = new UsernamePasswordToken("AAAA", "XXX"); //UnknownAccountException 没有改账号
//        UsernamePasswordToken token = new UsernamePasswordToken(Users.TEST.getUsername(), "XXX"); //IncorrectCredentialsException 凭证错误（密码错误）

        // 5. 认证
        subject.login(token);

        System.out.println( "是否认证成功: " + subject.isAuthenticated() );
        System.out.println( "是否有super这个角色: " +  subject.hasRole("super") );
        System.out.println( "是否有default这个角色: " +  subject.hasRole("default") );

        // 6. 授权
        System.out.println( "是否有user:select权限 " + subject.isPermitted("user:select"));
        System.out.println( "是否有user:delete权限 " + subject.isPermitted("user:delete"));
        System.out.println( "是否有user:update权限 " + subject.isPermitted("user:update"));
        System.out.println( "是否有user:insert权限 " + subject.isPermitted("user:insert"));

    }
}
