package xyz.housedoor.sample;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class IParcelable implements Parcelable {
	public int	i;
	public String	s = new String();
	public byte	b;
//	public byte[]	barray;
//	public ZZZ	z;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt	(this.i	);
		dest.writeString(this.s	);
		dest.writeByte	(this.b	);
//		dest.writeParcelable(this.buzz, 0);
	}

	public void readFromParcel(Parcel in) {
		this.i	= in.readInt();
		this.s	= in.readString();
		this.b	= in.readByte();
//		in.readList( this.bl, List.class.getClassLoader());
	}

	public IParcelable() {
	}

	protected IParcelable(Parcel in) {
		this.i	= in.readInt();
		this.s	= in.readString();
		this.b	= in.readByte();
//		this.buzz = in.readParcelable(B.class.getClassLoader());
	}

	public static final Parcelable.Creator<IParcelable> CREATOR = new Parcelable.Creator<IParcelable>() {
		public IParcelable createFromParcel(Parcel source) {
			return new IParcelable(source);
		}

		public IParcelable[] newArray(int size) {
			return new IParcelable[size];
		}
	};
};

