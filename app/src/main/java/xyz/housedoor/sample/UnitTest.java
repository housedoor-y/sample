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
	static final private int SMP_UT_PRI	= 1;
	static final public  int SMP_UT_PUB	= 2;


	private UnitTestExt	mUnitTestExtPri = new UnitTestExt();	// 外部クラス：プライベート
	public  UnitTestExt	mUnitTestExtPub = new UnitTestExt();	// 外部クラス：パブリック
/*
	private SmpUtInPri	mSmpUtPri = null;	// 内部クラス：プライベート
	public  SmpUtInPub	mSmpUtPub = null;	// 内部クラス：パブリック
*/
	public  int		iSmpUtPub = 2;
	private int		iSmpUtPri = 1;
	private String	sTest;


	public int iTestPub( int in ) {
		return iSmpUtPub + 2;
	}

	public void vTestPub( int in ) {
	}

	public void vTestPubException( ) throws IOException {
		throw new IOException("IOException");
	}

	public int iTestPri( int in ) {
		return iSmpUtPri + in + 2;
	}

	public void vTestPri( int in, String inStr ) {
		iSmpUtPri	= in;
		sTest 		= inStr;
	}


	public int iTestPubExtPri( int in ) {
		return mUnitTestExtPri.iTestExtPub( in ) + 2;
	}

	public void vTestPubExtPubVoid( int in ) {
		mUnitTestExtPri.vTestExtPub( 0 );
		for( int i = 0 ; i < in ; i++ ) {
			mUnitTestExtPri.vTestExtPub( i );
		}
	}

	public void vTestPubExtException( int in ) throws IOException {
		boolean sw;

		for( int i = 0 ; i <= in ; i++ ) {
			if( i == 0 ) {
				sw  = false;
			} else {
				sw  = true;
			}

			mUnitTestExtPri.vTestExtPriException( sw );
		}
	}

}

