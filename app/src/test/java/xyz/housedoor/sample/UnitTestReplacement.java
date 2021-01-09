package xyz.housedoor.sample;

// Junit4参考：https://www.slideshare.net/ichikaz3/junit4

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

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
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest( UnitTestExt.class )
public class UnitTestReplacement {

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
    //##    内部クラスの差し替え
    //##############################################################################
    /**
     * 内部クラスメンバ(Privateを除く)の差し替え
     */
    @Test
    public void TestReplacementExtMethod() {
        mUnitTest = new UnitTest() {
            @Override
            int vLoTestReplacementPub_Sub( int in ) {
                return in + 100;
            }
        };

        int ret = mUnitTest.vLoTestReplacementPub( 2 );
        assertEquals( ret, 102 );

    }

    /**
     * 内部で保持する、外部クラスのメンバー入れ替え
     */
    @Test
    public void TestReplacementExtMethod2() {
        int ret;

        mUnitTest.mUnitTestExtPub = new UnitTestExt() {     // クラス入れ替え
          @Override
          public int iTestExtPub( int in ) {
              return in + 100;
          }
        };


        ret = mUnitTest.vLoTestReplacementPubExt( 2 );
        assertEquals( ret, 102 );
    }

    /**
     * 内部クラスメンバ(Privateを除く)の差し替え
     * @note UT用のコンストラクタを作成し。内部の変数を書き換えます
     */
    @Test
    public void TestReplacementExtMethod3() {
        UnitTestExt mUnitTestPri = new UnitTestExt() {     // クラス入れ替え
            @Override
            public int iTestExtPubData( UnitTestData in ) {
                in.a *= 2000;
                in.b *= 200;
                in.c *= 20;
                return 20000;
            }
        };

        UnitTestExt mUnitTestPub = new UnitTestExt() {     // クラス入れ替え
            @Override
            public int iTestExtPubData( UnitTestData  in ) {
                in.a *= 1000;
                in.b *= 100;
                in.c *= 10;
                return 10000;
            }
        };
        int ret;

        mUnitTest = new UnitTest( mUnitTestPri, mUnitTestPub );     // UT用に差し替えのコンストラクタを作成する
        ret = mUnitTest.vLoTestReplacementPubExt( 2 );
        assertEquals( ret, 11232 );

        mUnitTest = new UnitTest( mUnitTestPri, mUnitTestPub );     // UT用に差し替えのコンストラクタを作成する
        ret = mUnitTest.vLoTestReplacementPriExt( 2 );
        assertEquals( ret, 22462 );

    }


}