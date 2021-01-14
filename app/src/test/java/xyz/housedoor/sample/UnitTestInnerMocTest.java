package xyz.housedoor.sample;



//**************************************
// インナークラス試験
// 参考ソース：https://ksino.hatenablog.com/entry/2013/12/03/000739
//            https://qiita.com/village/items/2f0d99b25eef0c8fd4ec
//**************************************

import android.os.Message;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import xyz.housedoor.sample.UnitTestInner.ParamClass;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;

public class UnitTestInnerMocTest {


//    @Spy
    @InjectMocks                        // 本クラス内をMock化する
    private UnitTest mUnitTest;

    @Mock                               // Mock化するクラス
    UnitTestExt mockUnitTestExt;


    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }


    @Before
    public void setUp() throws Exception {
//        mockUnitTestExt = spy(new UnitTestExt());       // 一部のメソッドをMock化するクラス
//        mockUnitTestExt = mock(UnitTestExt.class);      // 全てのメソッドをMock化するクラス



    }

    //##############################################################################
    //##
    //##
    //##
    //##############################################################################

    /**
     *　インナークラスから呼び出す外部メソッドをスタブ化
     * @throws Exception
     * @note 親クラス       ：UnitTest
     * @note インアークラス ：Inner　　　　試験対象
     */
    @Test
    public void test1() throws Exception {
        doReturn(20).when(mockUnitTestExt).iTestExtPub(1); // 試験対象関数から呼び出している関数をMOCK化
//        doReturn(50).when(mUnitTest).getMemValue();             // 試験対象関数から呼び出している関数をMOCK化


        mUnitTest.setiSmpUtPri( 200 );
        int ret = mUnitTest.mInnerClassB.innerTestInt();

        assertEquals(ret,221 );
    }

}