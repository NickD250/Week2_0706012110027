package Model

import android.os.Parcel
import android.os.Parcelable

open class Animal (
    var NamaHewan: String,
    var UmurHewan: Int,
    var imageUri: String

): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    open fun interaction(): String {
        return "";
    }

    open fun feed(): String {
        return "";
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(NamaHewan)
        parcel.writeInt(UmurHewan)
        parcel.writeString(imageUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }

}