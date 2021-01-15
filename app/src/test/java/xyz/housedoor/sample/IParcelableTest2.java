package xyz.housedoor.sample;


import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import junit.runner.BaseTestRunner;

import xyz.housedoor.sample.IParcelable.*;

// test環境では動作しません、androidTestで実行しましょう！！


//@RunWith(AndroidTestRunner.class)
//@PrepareForTest( IParcelable.class )
public class IParcelableTest2 {

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
        mIParcelable.readFromParcel(out );



    }
}
