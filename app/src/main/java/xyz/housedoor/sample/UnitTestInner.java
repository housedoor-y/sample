package xyz.housedoor.sample;

/**
 * 	参照サイト一覧
 *
 * 	■Privateなインナークラス内のPrivateメソッド呼び出し
 * 		https://ksino.hatenablog.com/entry/2013/12/03/000739
 *　■Privateなインナークラス内のPrivate変数へ設定
 * 		https://qiita.com/11295/items/773e7395889e05f605ae
 *
 *
 *  ■Mockito
 *		https://recruit.cct-inc.co.jp/tecblog/java/mockito-primer/
 *
 *
 *
 *   https://qiita.com/village/items/2f0d99b25eef0c8fd4ec
 */

import android.os.Message;

import java.io.IOException;

/**
 * 単体試験プログラムのサンプル流
 */
public class UnitTestInner {
	int mData;

	private UnitTestExt	mUnitTestExtPri = new UnitTestExt();	// 外部クラス：プライベート
	private InnerClass mInnerIf = new InnerClass();

	UnitTestInner() {
		mData = 0;
	}
	UnitTestInner( int in ) {
		InnerClass mInner = new InnerClass( in );
		mInner.innerTestInt( in );

		mData = in;
	}
	UnitTestInner( boolean in ) {
		if( in ) {
			mData = 100;
		}
	}

	/**
	 * クラスパラメータ
	 */
	static class ParamClass {
		public int		aaa;
		public int		bbb;
		public Message	mmm;

		ParamClass(){
		}
	}
	/**
	 * インタフェース定義
	 */
/*
	private interface  IUnitTest {
		int iTestPub( int in );
		void TestPub();
		void vTestPub( int in );
	}

	private class InnerIf implements IUnitTest {
		int aaa = 0;
		@Override
		public int iTestPub( int in ) {
			return 1;
		}
		@Override
		public void TestPub() {
			mUnitTestExtPri.iTestExtPub( 10 );
		}
		@Override
		public void vTestPub( int in ) {
			aaa = in;
		}
	}
*/

	private InnerClass mInner = new InnerClass();

	private class InnerClass {
		private int mInner;

		InnerClass() {
			mInner = 0;
		}

		InnerClass( int in ) {
			mInner = in;
		}

		public int innerTestInt( ) {
			return mInner + 1;
		}

		public int innerTestInt( int in ) {
			in = mUnitTestExtPri.iTestExtPub( in );
			return mInner + in +1;
		}
		public void innerTestVoid() {
			mInner += 1000;
		}

		public int innerTestParam( ParamClass msg ) {
//			public int innerTestParam( Message msg ) {
//			ParamClass mParamClass = new ParamClass();

//			return mParamClass.aaa + mParamClass.bbb;
			return 1;
		}

	};
}

