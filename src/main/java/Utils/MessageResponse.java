package Utils;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class MessageResponse {

    private Integer code;
    private String type;
    private String message;


    public MessageResponse() {
    }

    public MessageResponse(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

   
    public Integer getCode() {
        return code;
    }

   
    public void setCode(Integer code) {
        this.code = code;
    }

    public MessageResponse withCode(Integer code) {
        this.code = code;
        return this;
    }

  
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MessageResponse withType(String type) {
        this.type = type;
        return this;
    }

 
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}