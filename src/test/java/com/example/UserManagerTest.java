package jp.te4a.spring.boot.myapp6;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class UserManagerTest {

    // 1. 正常系：UserManagerインスタンス同一
    @Test
    void 正常系_UserManagerインスタンス同一() {
        UserManager instance1 = UserManager.getInstance();
        UserManager instance2 = UserManager.getInstance();

        // 複数回取得しても、すべて同一のインスタンスであることを検証
        assertThat(instance1).isSameAs(instance2);
    }

    // 2. 正常系：userList登録参照
    @Test
    void 正常系_userList登録参照() {
        UserManager userManager = UserManager.getInstance();
        User user1 = new User("U001");
        User user2 = new User("U002");

        userManager.setUserToList(user1);
        userManager.setUserToList(user2);

        // 登録したユーザーが、取得したListに含まれているかを検証
        List<User> list = userManager.getUserList();
        assertThat(list).contains(user1, user2);
    }

    // 3. 正常系：userMap登録参照
    @Test
    void 正常系_userMap登録参照() {
        UserManager userManager = UserManager.getInstance();
        User user = new User("U003");
        user.setName("テスト太郎");

        userManager.setUserToMap(user);

        // 登録したユーザーが、取得したMapに正しく含まれているかを検証
        Map<String, User> map = userManager.getUserMap();
        assertThat(map)
            .containsKey("U003")
            .containsValue(user);
    }

    // 4. 正常系：deleteUser（自由な名前付け）
    @Test
    void 正常系_ユーザー情報削除機能の検証() {
        UserManager userManager = UserManager.getInstance();
        User user = new User("U004");
        
        userManager.setUserToList(user);
        userManager.setUserToMap(user);

        // 削除メソッドを実行
        userManager.deleteUser("U004");

        // List と Map の両方からデータが消えているかを検証
        assertThat(userManager.getUserList()).doesNotContain(user);
        assertThat(userManager.getUserMap()).doesNotContainKey("U004");
    }
}