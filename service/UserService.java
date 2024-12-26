package service;

import dao.UserDAO;
import entity.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    /**
     * 用户登录验证服务方法
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功返回true，失败返回false
     */
    public static boolean login(String userName, String password) {
        return UserDAO.login(userName, password);
    }

    /**
     * 判断用户是否为系统管理员服务方法
     *
     * @param userName 用户名
     * @return 是系统管理员返回true，否则返回false
     */
    public static boolean isSysAdmin(String userName) {
        return UserDAO.isSysAdmin(userName);
    }

    /**
     * 判断用户是否为报名管理员服务方法
     *
     * @param userName 用户名
     * @return 是报名管理员返回true，否则返回false
     */
    public static boolean isEnrolAdmin(String userName) {
        return UserDAO.isEnrolAdmin(userName);
    }

    /**
     * 根据用户ID获取用户信息服务方法
     *
     * @param id 用户ID
     * @return 对应的用户对象，如果不存在则返回null
     */
    public static User getUser(int id) {
        return UserDAO.getUser(id);
    }

    /**
     * 根据用户ID获取用户密码服务方法
     *
     * @param id 用户ID
     * @return 对应的用户密码，如果用户不存在则返回null
     */
    public static String getPasswordById(int id) {
        return UserDAO.getPasswordById(id);
    }

    /**
     * 注册新用户服务方法（示例，可根据实际需求完善更多逻辑，比如密码加密、输入验证等）
     *
     * @param user 要注册的新用户对象
     * @return 注册成功返回true，失败返回false
     */
    public static boolean register(User user) {
        String sql = "insert into user (id, password, identity) values (?,?,?)";
        try {
            // 这里假设用户名就是用户ID，实际中可能需要更合理的处理方式，比如自增ID等
            int userId = Integer.parseInt(user.getUserName());
            String password = user.getPassword();
            int identity = user.getIdentity();

            try (
                    Connection c = DBUtil.getConnection();
                    PreparedStatement ps = c.prepareStatement(sql);
            ) {
                ps.setInt(1, userId);
                ps.setString(2, password);
                ps.setInt(3, identity);

                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

        public static boolean changePassword(int id, String oldPassword, String newPassword) {
            Connection connection = null;
            try {
                // 获取数据库连接
                connection = DBUtil.getConnection();

                // 先验证旧密码是否正确
                String checkOldPasswordSql = "SELECT password FROM user WHERE id =?";
                try (PreparedStatement checkPs = connection.prepareStatement(checkOldPasswordSql)) {
                    checkPs.setInt(1, id);
                    try (ResultSet rs = checkPs.executeQuery()) {
                        if (rs.next()) {
                            String storedOldPassword = rs.getString("password");
                            // 此处可以根据实际密码存储情况进行更严格的密码比较，比如密码加密后的比较等
                            if (!storedOldPassword.equals(oldPassword)) {
                                return false;
                            }
                        } else {
                            // 如果根据用户ID查询不到对应的用户记录，返回false
                            return false;
                        }
                    }
                }

                // 先创建临时表（每次需要使用临时表时先检查并创建，如果已存在则不会重复创建，因为使用了IF NOT EXISTS）
                String createTempTableSql = "CREATE TEMPORARY TABLE IF NOT EXISTS temp_user_password_update (" +
                        "user_id INT," +
                        "new_password VARCHAR(255)" +
                        ")";
                try (Statement statement = connection.createStatement()) {
                    statement.execute(createTempTableSql);
                }

                // 在system_admin_update_trigger触发器中会将相关数据插入此临时表（此处假设触发器已正确创建并按预期工作）

                // 从临时表中查询是否有对应此用户的待更新数据
                String checkTempTableSql = "SELECT user_id, new_password FROM temp_user_password_update WHERE user_id =?";
                List<Integer> userIdsToUpdate = new ArrayList<>();
                List<String> newPasswordsToUpdate = new ArrayList<>();
                try (PreparedStatement checkPs = connection.prepareStatement(checkTempTableSql)) {
                    checkPs.setInt(1, id);
                    try (ResultSet rs = checkPs.executeQuery()) {
                        while (rs.next()) {
                            userIdsToUpdate.add(rs.getInt("user_id"));
                            newPasswordsToUpdate.add(rs.getString("new_password"));
                        }
                    }
                }

                if (!userIdsToUpdate.isEmpty()) {
                    // 如果有需要更新的数据，执行更新user表的操作
                    String updateUserSql = "UPDATE user SET password =? WHERE id =?";
                    try (PreparedStatement ps = connection.prepareStatement(updateUserSql)) {
                        for (int i = 0; i < userIdsToUpdate.size(); i++) {
                            ps.setString(1, newPasswordsToUpdate.get(i));
                            ps.setInt(2, userIdsToUpdate.get(i));
                            ps.executeUpdate();
                        }
                    }
                }

                // 执行修改密码的主要操作（更新user表中对应id的密码字段）
                String updatePasswordSql = "UPDATE user SET password =? WHERE id =?";
                try (PreparedStatement ps = connection.prepareStatement(updatePasswordSql)) {
                    ps.setString(1, newPassword);
                    ps.setInt(2, id);
                    int rowsAffected = ps.executeUpdate();
                    return rowsAffected > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                // 关闭数据库连接，释放资源（这里假设DBUtil类中有合适的关闭连接方法，你可根据实际情况完善这部分）
                if (connection!= null) {
                    try {
                        DBUtil.closeConnection(connection);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    /**
     * 修改用户信息服务方法（示例，可根据实际需求完善具体可修改的字段等逻辑）
     *
     * @param user 修改后的用户对象，包含了要更新的各项信息
     * @return 修改成功返回true，失败返回false
     */
    public static boolean updateUserInfo(User user) {
        String sql = "update user set password =?, identity =? where id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getIdentity());
            ps.setInt(3, user.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除用户服务方法（示例，可添加更多权限验证等逻辑，比如只有系统管理员有权限删除用户等）
     *
     * @param id 用户ID
     * @return 删除成功返回true，失败返回false
     */
    public static boolean deleteUser(int id) {
        String sql = "delete from user where id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 添加用户信息服务方法（更通用的添加功能，可根据实际传入的完整User对象进行添加操作）
     *
     * @param user 要添加的用户对象，包含了完整的用户信息（如ID、用户名、密码、身份等）
     * @return 添加成功返回true，失败返回false
     */
    public static boolean add(User user) {
        String sql = "insert into user (id, userName, password, identity) values (?,?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdentity());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除user表中指定ID的用户信息的服务方法
     *
     * @param id 用户ID
     * @return 删除成功返回true，失败返回false
     */
    public boolean delete(int id) {
        return UserDAO.delete(id);
    }


}