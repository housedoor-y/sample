package xyz.housedoor.sample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest( UnitTestExt.class )
public class UnitTestTest {

    //    @Spy
    @InjectMocks                        // 本クラス内をMock化する
    private UnitTest mUnitTest = new UnitTest();

    @Mock                               // Mock化するクラス
            UnitTestExt mockUnitTestExt;


    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        mUnitTest = new UnitTest();
        mockUnitTestExt = spy(new UnitTestExt());       // Mock化するクラス
    }


    //##############################################################################
    //##
    //##    シンプル単体試験
    //##
    //##############################################################################
    @Test
    public void iTestPub() {
        int ret = mUnitTest.iTestPub( 2 );
        assertEquals( ret, 4 );
    }

    @Test
    public void vTestPub() {
        mUnitTest.vTestPub( 2 );
    }

    /**
     * 例外発生確認
     */
    @Test
    public void iTestPubException() throws Exception {
        IOException exc = null;

        try {
            mUnitTest.vTestPubException();
        } catch ( IOException e ) {
            exc = e;
        }

        assertEquals( exc.getMessage(),"IOException" );

    }

    //##############################################################################
    //##
    //##    Mockit使用単体試験
    //##
    //##############################################################################
    /**
     * Privateメソッド呼び出しサンプル
     * 戻り値あり
     * @note 参考URL:https://qiita.com/shotana/items/142bcd03ef6f9f25bf2a
     */
    @Test
    public void iTestPri() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        // UnitTest:試験クラス型
        // iTestPri：試験ソッド名（「int.class」引数に合わせ指定
        Method method = UnitTest.class.getDeclaredMethod("iTestPri", int.class);     // メソッド取得
        method.setAccessible(true);                                                        // アクセス許可
        int ret = (int)method.invoke( mUnitTest,2);                             // メソッド呼び出し
        assertEquals(ret,5 );
    }


    /**
     * Privateメソッド呼び出しサンプル
     * 戻り値なし
     * クラスPrivate変数設定(確認)
     */
    @Test
    public void vTestPri()  throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        // UnitTest:試験クラス型
        // iTestPri：試験ソッド名（「int.class,String.class」引数に合わせ指定
        Method method = UnitTest.class.getDeclaredMethod("vTestPri", int.class, String.class);     // メソッド取得
        method.setAccessible(true);                                         // アクセス許可
        method.invoke( mUnitTest,3, "vTestPri");                // メソッド呼び出し

        // ## Private変数内参照
        // mUnitTest：試験クラスインスタンス
        // sTest　　：取得変数名
        Field field = mUnitTest.getClass().getDeclaredField("sTest");   // 変数のポインタ取得
        field.setAccessible(true);                                          // アクセス許可
        String value = (String)field.get(mUnitTest);                        // 変数値取得
        assertEquals("vTestPri", value );
    }


    /**
     * Publicメソッド呼び出し：Privateスタブ(引数、戻り値有り)
     */
    @Test
    public void iTestPriExtPri() throws Exception {

//        when( mockUnitTestExt.iTestExtPub(2)).thenReturn(3);                        // Mock版 その1
//        when( mockUnitTestExt, "iTestExtPub", 2).thenReturn(3); // Mock版 その2
        doReturn(3).when(mockUnitTestExt).iTestExtPub(2);              // Power-Moc版 その1

        int ret = mUnitTest.iTestPubExtPri( 2 );
        assertEquals( ret, 5 );
    }

    /**
     * スタブで例外発生試験
     */
    @Test
    public void vTestPubExtPubVoid() throws Exception {
        IOException exc = null;

        // スタブで例外発生なし
        doNothing().when(mockUnitTestExt).vTestExtPriException(anyBoolean());
        try {
            mUnitTest.vTestPubExtException(0);
        } catch ( IOException e ) {
            exc = e;
        }
        assertNull( exc );  // 例外が発生していなければNULL

        // スタブで例外発生あり
        doThrow(new IOException("ExtPri:IOException")).when(mockUnitTestExt).vTestExtPriException(anyBoolean());
        try {
            mUnitTest.vTestPubExtException(0);
        } catch ( IOException e ) {
            exc = e;
        }
        assertNotNull( exc );   // 例外が発生していればNULL以外
    }


}