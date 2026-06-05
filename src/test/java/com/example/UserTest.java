package jp.te4a.spring.boot.myapp6;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {

    // 1. 正常系：ユーザー管理コード登録参照
    @Test
    void 正常系_ユーザー管理コード登録参照() {
        // コンストラクタでコードを指定してインスタンス化
        User user = new User("U12345");
        
        // 登録したユーザー管理コードと同じ値が取得できることを検証
        assertThat(user.getCode()).isEqualTo("U12345");
    }

    // 2. 正常系：名前登録参照
    @Test
    void 正常系_名前登録参照() {
        User user = new User("U12345");
        user.setName("山田太郎");
        
        // 登録した名前と同じ値が取得できることを検証
        assertThat(user.getName()).isEqualTo("山田太郎");
    }

    // 3. 正常系：年齢登録参照
    @Test
    void 正常系_年齢登録参照() {
        User user = new User("U12345");
        user.setAge(25); // 有効範囲（1〜199）の内側の値
        
        // 登録した年齢と同じ値が取得できることを検証
        assertThat(user.getAge()).isEqualTo(25);
    }

    // 4. 異常系：範囲外年齢登録
    @Test
    void 異常系_範囲外年齢登録() {
        User user = new User("U12345");
        user.setAge(200); // 範囲外の年齢（199より大きい）
        
        // 既定のエラー値（-1）を取得することを確認する
        assertThat(user.getAge()).isEqualTo(-1);
    }

    // 5. 異常系：（各自でそれらしい名前をつける）
    // ★特定の順番（連続で範囲外入力）で実行し、内部状態が不正な値を保持してしまうバグを暴くテスト
    @Test
    void 異常系_連続範囲外入力による内部状態不正保持バグの検出() {
        User user = new User("U12345");

        // 【特定の順番で実行】
        // 1回目に範囲外（200）を入れる ➔ 内部で age は -1 になり、age2 に初期値(-1)が移る
        user.setAge(200); 
        // 2回目に別の範囲外（300）を入れる ➔ 内部で age は -1 になり、age2 に1回目の結果である「-1」が移る
        user.setAge(300); 

        // 本来、どのような順番で範囲外が送られてきても、getAge() は一貫して既定のエラー値「-1」を返すはずです。
        // しかし、連続して範囲外を入力したことによって内部状態に矛盾が起きないかをアサーションで検証します。
        assertThat(user.getAge()).isEqualTo(-1);
    }
}