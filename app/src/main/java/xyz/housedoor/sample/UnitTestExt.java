package xyz.housedoor.sample;

import java.io.IOException;

/**
 * 単体試験プログラムのサンプル流
 */
public class UnitTestExt {

	private int testExtPri = -1;
	public  int testExtPub = -2;

	public int iTestExtPubVoid() {
		return 1;
	}

	public int iTestExtPub( int in ) {
		return in + 2;
	}

	public void vTestExtPub( int in ) {
	}

	public void vTestExtPriException(boolean exc) throws IOException {
		if( exc ) {
			throw new IOException("ExtPri:IOException");
		}
	}
}

