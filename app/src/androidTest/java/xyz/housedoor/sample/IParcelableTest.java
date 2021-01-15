package xyz.housedoor.sample;


import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

// test環境では動作しません、androidTestで実行しましょう！！
// 参照：https://esmasui.hatenadiary.org/entry/20121010/1349888023

//@RunWith(AndroidTestRunner.class)
public class IParcelableTest {

//    private IParcelable mIParcelable = new IParcelable();

//   @Before
//    public void initMocks() {
//        MockitoAnnotations.openMocks(this);
//    }



    //##############################################################################
    //##
    //##    シンプル単体試験
    //##
    //##############################################################################
    @Test
    public void test1() {

        IParcelable mIParcelable = new IParcelable();

        mIParcelable.i = 1;
        mIParcelable.b = 2;
        mIParcelable.s = "ABC";


        final Parcel out = Parcel.obtain();
        mIParcelable.writeToParcel(out, 0);

    }


    /**
     * インスタンスが生成できることを確認する
     */
    @Test
    public void shouldTheInstanceCreated() {
        assertNotNull(new IParcelable());
    }

    /**
     * Parcelを通じてインスタンスの状態を復元できることを確認する
     */
    @Test
    public void shouldThatInstanceWillRestoreFromParcel() {

        // Given

        // インスタンスを生成する
        IParcelable underTest = new IParcelable();

        // インスタンスの状態を設定する
        underTest.b =1;
//        underTest.bl = new byte[3];
        underTest.i =1;
        underTest.s ="test";


        // When

        // インスタンスの状態をParcelに書き出す
        final Parcel out = Parcel.obtain();
        underTest.writeToParcel(out, 0);

        // Parcelのインデックスを初期位置に戻す
        out.setDataPosition(0);

        // Parcelに書きだしたインスタンスの状態を書き戻す
        IParcelable restored = IParcelable.CREATOR.createFromParcel(out);

        // Then
        restored.readFromParcel( out );

        assertNotNull(restored);

        byte aaa = restored.b;
    }
}
