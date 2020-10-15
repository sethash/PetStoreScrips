package Utils;

import com.google.gson.annotations.SerializedName;


public enum StatusEnum {

    @SerializedName("available")
    available("available"),
    @SerializedName("pending")
    pending("pending"),
    @SerializedName("sold")
    sold("sold");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
