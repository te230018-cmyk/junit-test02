package com.example;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {

    // 1. 正常系：ユーザー管理コード
    @Test
    void 正常系_ユーザー管理コード登録参照() {
        User user = new User();
        user.setCode("U12345");
        assertThat(user.getCode()).isEqualTo("U12345");
    }

    // 2. 正常系：名前
    @Test
    void 正常系_名前登録参照() {
        User user = new User();
        user.setName("山田太郎");
        assertThat(user.getName()).isEqualTo("山田太郎");
    }

    // 3. 正常系：年齢
    @Test
    void 正常系_年齢登録参照() {
        User user = new User();
        user.setAge(25);
        assertThat(user.getAge()).isEqualTo(25);
    }

    // 4. 異常系：範囲外年齢登録
    @Test
    void 異常系_範囲外年齢登録() {
        User user = new User();
        user.setAge(-5); // 範囲外の年齢
        assertThat(user.getAge()).isEqualTo(-1); // 既定のエラー値(-1)になること
    }

    // 5. 異常系：（各自でそれらしい名前をつける）
    // 隠れたバグ（特定の順番で実行すると範囲外が返る現象）を暴くテスト
    @Test
    void 異常系_連続設定による年齢バリデーション回避のバグ検出テスト() {
        User user = new User();

        // 特定の順番でセットしてみる
        user.setAge(888); // 1番目：バグを誘発する引き金になる正常値（今回の仕様上の特殊な値）
        user.setAge(-10); // 2番目：本来なら -1 になるべき範囲外の値をセット

        // 本来は「-1」になるべきだが、特定の順序のせいで「-10」がすり抜けて設定されてしまう
        // これをアサーションで「-10」が返ってきてしまっている（＝バグを見つけた！）と検証します
        assertThat(user.getAge()).isEqualTo(-10); 
    }
}