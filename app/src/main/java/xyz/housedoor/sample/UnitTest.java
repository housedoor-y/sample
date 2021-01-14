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
 */

import java.io.IOException;

/**
 * 単体試験プログラムのサンプル流
 */
public class UnitTest {
	static final private int SMP_UT_PRI = 1;
	static final public int SMP_UT_PUB = 2;

	private String message = "This is example.";

	private UnitTestExt mUnitTestExtPri = new UnitTestExt();    // 外部クラス：プライベート
	public UnitTestExt mUnitTestExtPub = new UnitTestExt();    // 外部クラス：パブリック
	private UnitTestData mUnitTestData = new UnitTestData();    // 外部クラス：プライベート(データ)


/*
	private SmpUtInPri	mSmpUtPri = null;	// 内部クラス：プライベート
	public  SmpUtInPub	mSmpUtPub = null;	// 内部クラス：パブリック
*/

	IUnitTest innerA = new InnerA();


	public int iSmpUtPub = 2;
	private int iSmpUtPri = 1;
	private String sTest;

	/**
	 * コンストラクタ
	 */
	UnitTest() {
	}

	UnitTest(String in) {
	}

	/**
	 * コンストラクタ(UT用)
	 */
	UnitTest(UnitTestExt pri, UnitTestExt pub) {
		mUnitTestExtPri = pri;
		mUnitTestExtPub = pub;
	}


	public int iTestPub(int in) {
		return iSmpUtPub + 2;
	}

	public void vTestPub(int in) {
	}

	public void vTestPubException() throws IOException {
		throw new IOException("IOException");
	}

	public int iTestPri(int in) {
		return iSmpUtPri + in + 2;
	}

	public void vTestPri(int in, String inStr) {
		iSmpUtPri = in;
		sTest = inStr;
	}


	public int iTestPubExtPri(int in) {
		return mUnitTestExtPri.iTestExtPub(in) + 2;
	}

	public void vTestPubExtPubVoid(int in) {
		mUnitTestExtPri.vTestExtPub(0);
		for (int i = 0; i < in; i++) {
			mUnitTestExtPri.vTestExtPub(i);
		}
	}

	public void vTestPubExtException(int in) throws IOException {
		boolean sw;

		for (int i = 0; i <= in; i++) {
			if (i == 0) {
				sw = false;
			} else {
				sw = true;
			}

			mUnitTestExtPri.vTestExtPriException(sw);
		}
	}

	/**
	 * プライベートメソッドのMock化試験用(引数、戻り値あり)
	 */
	public int vLoTestPub() {
		int result;

		result = vLoTestPubCallPri(2);

		return result;
	}

	private int vLoTestPubCallPri(int in) {
		return in + 1;
	}

	/**
	 * プライベートメソッドのMock化試験用(引数、戻り値なし)
	 */
	public void vLoTestPub2() throws Exception {
		vLoTestPubCallPri2();
	}

	private void vLoTestPubCallPri2() throws Exception {
		throw new Exception("");
	}


	/**
	 * ローカルメンバ(Private以外)差し替え試験用
	 */
	public int vLoTestReplacementPub(int in) {
		int ret = vLoTestReplacementPub_Sub(in);
		return ret;
	}

	int vLoTestReplacementPub_Sub(int in) {
		return in + 2;
	}

	/**
	 * 外部メンバ(Private以外)差し替え試験用
	 */
	public int vLoTestReplacementPubExt(int in) {
		int ret = mUnitTestExtPub.iTestExtPubData(mUnitTestData);
		return in + ret + mUnitTestData.a + mUnitTestData.b + mUnitTestData.c;
	}

	public int vLoTestReplacementPriExt(int in) {
		int ret = mUnitTestExtPri.iTestExtPubData(mUnitTestData);
		return in + ret + mUnitTestData.a + mUnitTestData.b + mUnitTestData.c;
	}


	/**
	 * インタフェース定義
	 */
	public abstract class IUnitTest {
		abstract int iTestPub(int in);

		abstract void vTestPub(int in);
	}

	private class InnerA extends IUnitTest {
		int aaa = 0;

		@Override
		int iTestPub(int in) {
			return 1;
		}

		@Override
		public void vTestPub(int in) {
			aaa = in;
		}
	}


	void setiSmpUtPri( int in ) {
		iSmpUtPri = in;
	}

	public String getMessage() {
		return message;
	}

	public int getMemValue() {
		return mUnitTestExtPri.testExtPub;
	}



	private InnerClass mInner = new InnerClass();

	private class InnerClass {
		private int mInner;

		InnerClass() {
			mInner = 0;
		}

		InnerClass( int String ) {
//			mInner = in;
		}

		public int innerTestInt( ) {
			return mInner + 1;
		}

		public int innerTestInt( int in ) {
			return mInner + in +1;
		}
		public void innerTestVoid() {
			mInner += 1000;
		}
		public String innerTestString() {
			return "TEST";
		}

	};

	InnerClassB mInnerClassB = new InnerClassB();


	public class InnerClassB {
		private int mInner;

		InnerClassB() {
			mInner = 0;
		}


		public int innerTestInt( ) {
			int ret = mUnitTestExtPri.iTestExtPub( 1 );
			ret += getMemValue();
			return iSmpUtPri + 1 + ret;
		}

		public int innerTestInt( int in ) {
			return mInner + in +1;
		}
		public void innerTestVoid() {
			mInner += 1000;
		}
		public String innerTestString() {
			return "TEST";
		}

	};
}

