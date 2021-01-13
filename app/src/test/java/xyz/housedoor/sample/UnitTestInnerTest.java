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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import xyz.housedoor.sample.UnitTestInner.ParamClass;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest( UnitTest.class )
public class UnitTestInnerTest {

    private UnitTest mUnitTest;


    @Before
    public void setUp() throws Exception {
//        mUnitTest = PowerMockito.spy( new UnitTest() );
    }


    //##############################################################################
    //##
    //##
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
        UnitTestInner mUnitTestInner = new UnitTestInner( 10 );

        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class innerClazz = loader.loadClass("xyz.housedoor.sample.UnitTestInner$InnerClass");  // パッケージ名.親クラス名$インナークラス名

        // 第１引数に外側クラスのClassオブジェクトを指定する
        Class[] constructorParameterTypes = new Class[]{ UnitTestInner.class, int.class };           // ( 親クラス名.class, 親クラス：引数型名)
        Constructor constructor = innerClazz.getDeclaredConstructor( constructorParameterTypes );
        constructor.setAccessible(true);

        int classParamInt = 100;
        Object target = constructor.newInstance( new UnitTestInner(), classParamInt);   // 引数つきの場合：newInstance( new 親クラス名(), str)

//        Class[] methodParametertypes = new Class[]{};
//        Method method = innerClazz.getDeclaredMethod("innerTestInt", methodParametertypes );             // getDeclaredMethod("<試験対象メソッド名>", 引数の型1, 引数の型2...);
        Method method = innerClazz.getDeclaredMethod("innerTestInt", int.class );             // getDeclaredMethod("<試験対象メソッド名>", 引数の型1, 引数の型2...);

        int paramInt = 10;
        int  ret = (int) method.invoke(target, paramInt );
    }

    /**
     *
     * @throws Exception
     * @note 親クラス       ：UnitTest
     * @note インアークラス ：Inner　　　　試験対象
     */
    @Test
    public void test2() throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class innerClazz = loader.loadClass("xyz.housedoor.sample.UnitTestInner$InnerClass");  // パッケージ名.親クラス名$インナークラス名

        // 第１引数に外側クラスのClassオブジェクトを指定する
        Class[] constructorParameterTypes = new Class[]{ UnitTestInner.class };           // ( 親クラス名.class, 親クラス：引数型名)
        Constructor constructor = innerClazz.getDeclaredConstructor( constructorParameterTypes );
        constructor.setAccessible(true);

        UnitTestInner.ParamClass classParamInt = new UnitTestInner.ParamClass();
        classParamInt.aaa = 10;
        classParamInt.bbb = 20;
        classParamInt.mmm = Message.obtain();

//        Object target = constructor.newInstance( new UnitTestInner(), classParamInt);   // 引数つきの場合：newInstance( new 親クラス名(), str)
//        Object target = constructor.newInstance( classParamInt);   // 引数つきの場合：newInstance( new 親クラス名(), str)
        Object target = constructor.newInstance( new UnitTestInner());   // 引数つきの場合：newInstance( new 親クラス名(), str)

        Method method = innerClazz.getDeclaredMethod("innerTestParam", Message.class );             // getDeclaredMethod("<試験対象メソッド名>", 引数の型1, 引数の型2...);

        UnitTestInner.ParamClass paramMsg = new UnitTestInner.ParamClass();
        int  ret = (int) method.invoke(target, paramMsg );
   }


}