package af.itt.sc.comon;

import java.util.Locale;

//import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/*import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;*/

/**
 * メッセージクラス
 *
 * @author chin
 */
/*@JsonPropertyOrder({ "name", "id" })*/
public class Message2 {

    private String code = null;
    private Message2.Type type = null;
    private String message = null;

    /**
     * メッセージコード、メッセージ引数からインスタンスを生成します。<br>
     * メッセージコード中の種別（S, I, W, E）を判別しメッセージ種別を設定します。
     *
     * @param messageCode メッセージコード
     * @param messageArgs メッセージ引数
     */
    public Message2(String messageCode, String... messageArgs) {
        this.code = messageCode;
        this.message = Utils.get(messageCode, messageArgs);
        this.type = Message2.Type.type(messageCode);
    }

    /**
     * メッセージ種別、メッセージコード、メッセージ引数からインスタンスを生成します。<br>
     * メッセージコードからメッセージ種別を判別できない場合に利用します。
     *
     * @param type メッセージ種別
     * @param messageCode メッセージコード
     * @param messageArgs メッセージ引数
     */
    public Message2(Message2.Type type, String messageCode, String... messageArgs) {
        this.code = messageCode;
        this.message = Utils.get(messageCode, messageArgs);
        this.type = type;
    }

    Message2(String code, String message, Message2.Type type) {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    /**
     * メッセージ種別、メッセージからインスタンスを生成します。
     *
     * @param type メッセージ種別
     * @param message メッセージ
     */
    public Message2(Message2.Type type, String message) {
        this.message = message;
        this.type = type;
    }

    //@JsonProperty("code")
    private String getCode() {
        return this.code;
    }

    //@JsonProperty("message")
    private String getShortMessage() {
        return message;
    }

    //@JsonIgnore
    public String getMessage() {
        return String.format("[%s]%s", this.code, this.message);
    }

    //@JsonProperty("type")
    public Message2.Type getType() {
        return type;
    }

    public void setType(Message2.Type type) {
        this.type = type;
    }

    public enum Type {

        SUCCESS("S", "success"), INFO("I", "info"), WARNING("W", "warning"), DANGER("E", "danger");

        private String type = null;
        //@JsonValue
        private String typeName = null;

        private Type(String type, String name) {
            this.type = type;
            this.typeName = name;
        }

        static Type type(String messageCode) {
            for (Type type : values()) {
                if (type.type.equals(StringUtils.mid(messageCode, 0, 1))) {
                    return type;
                }
            }
            return Type.INFO;
        }
    }

    @Component
    private static class Utils {
        static MessageSource messageSource;
        @Autowired
        private MessageSource wiredMessage;

//        @PostConstruct
        private void init() {
            messageSource = wiredMessage;
        }

        static String get(String code, String... arg) {
            return messageSource.getMessage(code, arg, Locale.getDefault());
        }

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}