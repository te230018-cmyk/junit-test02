package jp.te4a.spring.boot.myapp6;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class UserManagerTest {

    // ----------------------------------------------------
    // 課題5 で指定されたテストメソッド
    // ----------------------------------------------------

    // 1. 正常系：MapList初期生成
    @Test
    void 正常系_MapList初期生成() {
        UserManager userManager = UserManager.getInstance();
        
        // 取得したListとMapがnullではなく、中身が空であることを検証
        assertThat(userManager.getUserList()).isNotNull().isEmpty();
        assertThat(userManager.getUserMap()).isNotNull().isEmpty();
    }

    // 2. 正常系：List登録順序保持
    @Test
    void 正常系_List登録順序保持() {
        UserManager userManager = UserManager.getInstance();
        
        // テスト前に一度Listをクリア（シングルトンで状態が残る対策）
        userManager.getUserList().clear();

        User u1 = new User("U001");
        User u2 = new User("U002");
        User u3 = new User("U003");

        // 複数のユーザーを順番に登録
        userManager.setUserToList(u1);
        userManager.setUserToList(u2);
        userManager.setUserToList(u3);

        // 登録した順番（u1 -> u2 -> u3）でListに格納されているかを検証
        List<User> list = userManager.getUserList();
        assertThat(list).containsExactly(u1, u2, u3);
    }

    // 3. 正常系：Mapキー確認
    @Test
    void 正常系_Mapキー確認() {
        UserManager userManager = UserManager.getInstance();
        
        User userA = new User("USER_A");
        User userB = new User("USER_B");

        // マップに登録
        userManager.setUserToMap(userA);
        userManager.setUserToMap(userB);

        // ユーザー管理コード（"USER_A", "USER_B"）がキーとして正しくMapに登録されているかを検証
        Map<String, User> map = userManager.getUserMap();
        assertThat(map).containsOnlyKeys("USER_A", "USER_B");
    }


    // ----------------------------------------------------
    // （参考）課題4 で実装したテストメソッド
    // ----------------------------------------------------

    @Test
    void 正常系_UserManagerインスタンス同一() {
        UserManager instance1 = UserManager.getInstance();
        UserManager instance2 = UserManager.getInstance();
        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    void 正常系_userList登録参照() {
        UserManager userManager = UserManager.getInstance();
        User user = new User("U100");
        userManager.setUserToList(user);
        assertThat(userManager.getUserList()).contains(user);
    }

    @Test
    void 正常系_userMap登録参照() {
        UserManager userManager = UserManager.getInstance();
        User user = new User("U200");
        userManager.setUserToMap(user);
        assertThat(userManager.getUserMap()).containsKey("U200");
    }

    @Test
    void 正常系_ユーザー情報削除機能の検証() {
        UserManager userManager = UserManager.getInstance();
        User user = new User("U300");
        userManager.setUserToList(user);
        userManager.setUserToMap(user);

        userManager.deleteUser("U300");

        assertThat(userManager.getUserList()).doesNotContain(user);
        assertThat(userManager.getUserMap()).doesNotContainKey("U300");
    }
}