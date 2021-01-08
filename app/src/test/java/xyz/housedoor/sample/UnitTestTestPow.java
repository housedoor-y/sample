package xyz.housedoor.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//**************************************
// 参考ソース：https://www.it-swarm-ja.tech/ja/java/mockito%E3%81%A7%E3%83%86%E3%82%B9%E3%83%88%E4%B8%AD%E3%81%AE%E3%82%AF%E3%83%A9%E3%82%B9%E3%81%AE%E3%83%97%E3%83%A9%E3%82%A4%E3%83%99%E3%83%BC%E3%83%88%E3%83%A1%E3%82%BD%E3%83%83%E3%83%89%E3%82%92%E3%82%B9%E3%82%BF%E3%83%96%E5%8C%96%E3%81%99%E3%82%8B%E6%96%B9%E6%B3%95/826912195/
//**************************************

@RunWith(PowerMockRunner.class)
@PrepareForTest( UnitTest.class )
public class UnitTestTestPow {

    private UnitTest mUnitTest;


    @Before
    public void setUp() throws Exception {
        mUnitTest = PowerMockito.spy( new UnitTest() );
    }


    //##############################################################################
    //##
    //##    ローカルプライベート関数テスト
    //##
    //##############################################################################
    @Test
    public void test1() throws Exception {
        PowerMockito.doReturn(10).when(mUnitTest, "vLoTestPubCallPri", 2);  // ローカル関数のスタブ化

        int ret = mUnitTest.vLoTestPub();
        assertEquals( ret, 10 );
        PowerMockito.verifyPrivate(mUnitTest).invoke("vLoTestPubCallPri", 2);           // 関数が呼ばれたことの確認
    }

    @Test
    public void test2() {
        int ret = 1;

        try {
            PowerMockito.doNothing().when(mUnitTest, "vLoTestPubCallPri2");  // ローカル関数のスタブ化
            mUnitTest.vLoTestPub2();
            PowerMockito.verifyPrivate(mUnitTest).invoke("vLoTestPubCallPri2");           // 関数が呼ばれたことの確認
        } catch ( Exception e ) {
            fail();
            ret = 0;
        }

        assertEquals( ret, 1 );
    }
}