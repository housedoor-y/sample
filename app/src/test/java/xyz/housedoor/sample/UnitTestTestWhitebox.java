package xyz.housedoor.sample;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
@PrepareForTest( UnitTest.class )
public class UnitTestTestWhitebox {

    private UnitTest mUnitTest;


    @Before
    public void setUp() throws Exception {
        mUnitTest = PowerMockito.spy( new UnitTest() );
    }


    /**
     * Whiteboxを使用したPrivate変数(String)領域の書き換え
     * 参考URL:https://blueskyarea.hatenablog.com/entry/2017/07/15/001828
     */
    @Test
    public void testWithoutWhiteBox() {
        UnitTest wb = new UnitTest();
        assertEquals("This is example.", wb.getMessage());

        Whitebox.setInternalState(wb, "message", "overwritten the message.");
        assertEquals("overwritten the message.", wb.getMessage());
    }

    /**
     * Whiteboxを使用したPrivateクラスの書き換え( Privateクラス置き換え)
     * 参考URL:https://blueskyarea.hatenablog.com/entry/2017/07/15/001828
     */
    @Test
    public void testWithoutWhiteBox2() {
        UnitTestExt mUnitTestExt = new UnitTestExt();

        mUnitTestExt.testExtPub = 3;

        UnitTest wb = new UnitTest();
        Whitebox.setInternalState(wb, "mUnitTestExtPri", mUnitTestExt );

        assertEquals(3, wb.getMemValue());
    }

    /**
     * Whiteboxを使用したPrivateクラスの書き換え( Privateクラス置き換え)    ★うまく動作せず調整中！
     * 参考URL:https://blueskyarea.hatenablog.com/entry/2017/07/15/001828
     */
    @Test
    public void testWithoutWhiteBox3() {
        class UnitTestExtStub extends UnitTestExt {
//            @Override
            public int iTestExtPub( int in ) {
                return in + 10;
            }
        }

        UnitTestExt mUnitTestExtStub = new UnitTestExtStub();

        UnitTest wb = new UnitTest();
        Whitebox.setInternalState(wb, "mUnitTestExtPri", mUnitTestExtStub );

        int ret = mUnitTest.iTestPubExtPri( 2 );
        assertEquals( ret, 6 );
    }




}