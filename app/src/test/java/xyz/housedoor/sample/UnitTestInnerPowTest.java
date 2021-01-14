package xyz.housedoor.sample;



//**************************************
// インナークラス試験
// 参考ソース：https://ksino.hatenablog.com/entry/2013/12/03/000739
//            https://qiita.com/village/items/2f0d99b25eef0c8fd4ec
//**************************************

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.spy;
import static org.powermock.api.mockito.PowerMockito.doReturn;

@RunWith(PowerMockRunner.class)
@PrepareForTest( UnitTest.class )
public class UnitTestInnerPowTest {
    private UnitTest mUnitTest;

//    @Mock
    private UnitTestExt mockUnitTestExt;

    /*
    @InjectMocks
    private UnitTest mUnitTest = spy(new UnitTest());


    @Before
    public void setUp() throws Exception {
//        mUnitTest = spy( new UnitTest() );
    }
*/
    @Before
    public void setUp() throws Exception {
        mUnitTest = PowerMockito.spy( new UnitTest() );
//        mockUnitTestExt = PowerMockito.spy(new UnitTestExt());       // Mock化するクラス

    }


    //##############################################################################
    //##
    //## 使えません！！
    //##
    //##############################################################################

    /**
     *
     * @throws Exception
     * @note 親クラス       ：UnitTest
     * @note インアークラス ：Inner　　　　試験対象
     */
    @Test
    public void test1() throws Exception {
//        doReturn(10).when(mockUnitTestExt, "iTestExtPub", 1);  // ローカル関数のスタブ化

//        PowerMockito.doReturn(20).when(mockUnitTestExt).iTestExtPub(1); // 試験対象関数から呼び出している関数をMOCK化
//        PowerMockito.doReturn(50).when(mUnitTest).getMemValue();             // 試験対象関数から呼び出している関数をMOCK化

//        doReturn(10).when(mUnitTest, "getMemValue");  // ローカル関数のスタブ化
        PowerMockito.when(mUnitTest.getMemValue()).thenReturn(20);  // ローカル関数のスタブ化


        mUnitTest.setiSmpUtPri( 200 );
        int ret = mUnitTest.mInnerClassB.innerTestInt();

        assertEquals(ret,201 );
    }


    @Test
    public void test2() throws Exception {
        PowerMockito.doReturn(20).when(mUnitTest, "getMemValue");  // ローカル関数のスタブ化

        mUnitTest.setiSmpUtPri( 200 );
        int ret = mUnitTest.mInnerClassB.innerTestInt();

        assertEquals(ret,201 );
    }
}